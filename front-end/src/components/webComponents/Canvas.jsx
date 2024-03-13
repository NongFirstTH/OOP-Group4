import React, { createRef, useEffect, useState } from "react";
import "./styles/App.css";
import { useAppSelector } from "../../store/hooks";
import { selectConfig } from "../../store/Slices/configSlice";
import { selectTerritory } from "../../store/Slices/territorySlice.ts";

export default function Canvas(props) {
    const territoryState = useAppSelector(selectTerritory);

    const [hexSize, setHexSize] = useState(40);
    const configState = useAppSelector(selectConfig);
    const [rows, setRows] = useState(configState.m);
    const [cols, setCols] = useState(configState.n);
    const [playerName, setPlayerName] = useState("a");
    const canvasRef = createRef();
    const { width, height } = calculateCanvasSize();

  useEffect(() => {
      if (props.mapArray) {
        const canvasHex = canvasRef.current;
          drawHexes(canvasHex);
    }
  }, [props.mapArray,territoryState]);

  function calculateCanvasSize() {
    const { hexWidth, hexHeight, vertDist, horizDist } = getHexParameters();
    const newWidth = cols * horizDist + hexWidth / 2 + 10;
    const newHeight = rows * vertDist + hexHeight + 20;
    return { width: newWidth, height: newHeight };
  };

  const evenq_to_axial = (hex) => {
    const q = hex.col;
    const r = hex.row - (q - (q & 1)) / 2;
    return Hex(q, r);
  };

  const drawHexes = (canvas) => {
    props.mapArray.map((innerArray, r) => {
      innerArray.map((element, q) => {
        const evenQCoord = { col: q + 1, row: r + 1 };
        const axialCoord = evenq_to_axial(evenQCoord);
        let center = hexToPixel(axialCoord);

        drawHex(canvas, center, Hex(q + 1, r + 1), element);
        // if (element.element.player !== null)
          drawHexCoordinates(canvas, center, Hex(q + 1, r + 1), element);
      });
    });

  };

  const drawHex = (canvasID, center, hex, map) => {
    for (let i = 0; i <= 5; i++) {
      let start = getHexCornorCoord(center, i);
      let end = getHexCornorCoord(center, i + 1);
      checkPlayer(start,end,canvasID, center, hex, map)
      checkCityCenter(canvasID, center, map)
  };

  function checkCityCenter(canvasID, center,map){
    if (map.i === territoryState.row-1 && map.j === territoryState.col-1) {
        fillHex(canvasID, center, "#1D8348");
      }
  }

  function checkPlayer(start,end,canvasID, center, hex, map){
    if (map.i === territoryState.currow-1 && map.j === territoryState.curcol-1 && map.element.player === playerName) {
        // drawLine(
        //   canvasID,
        //   { x: start.x, y: start.y },
        //   { x: end.x, y: end.y },
        //   "black",
        //   9
        // );
        fillHex(canvasID, center, "#59CC8A");
      } 
    //   else if (map.element.player !== null) {
      else if (props.player.p1.getRegion(map.i,map.j)!== null) {
      fillHex(canvasID, center, props.player.p1.getColor());
      }else if(props.player.p2.getRegion(map.i,map.j)!== null) fillHex(canvasID, center, props.player.p2.getColor());
     
      else {
        drawLine(
          canvasID,
          { x: start.x, y: start.y },
          { x: end.x, y: end.y },
          "#07192E",
          2
        );
        fillHex(canvasID, center, "#DEFFFC");
      }
    }
  }

  const fillHex = (canvasID, center, fillColor) => {
    const ctx = canvasID.getContext("2d");
    ctx.beginPath();
    for (let i = 0; i <= 5; i++) {
      let corner = getHexCornorCoord(center, i);
      if (i === 0){
        ctx.moveTo(corner.x, corner.y);
      }else {
        ctx.lineTo(corner.x, corner.y);
        }
    }
    ctx.closePath();
    ctx.fillStyle = fillColor;
    ctx.fill();
  };

  const getHexCornorCoord = (center, i) => {
    let angle_deg = 60 * i;
    let angle_rad = (Math.PI / 180) * angle_deg;
    let x = center.x + hexSize * Math.cos(angle_rad);
    let y = center.y + hexSize * Math.sin(angle_rad);
    return Point(x, y);
  };

  function getHexParameters(){
    let hexWidth = hexSize * 2;
    let hexHeight = Math.sqrt(3) * hexSize;
    let vertDist = hexHeight;
    let horizDist = (3 / 2) * hexSize;
    return { hexWidth, hexHeight, vertDist, horizDist };
  };

  const hexToPixel = (hex) => {
    let x = hexSize * ((3 / 2) * hex.q);
    let y = hexSize * ((Math.sqrt(3) / 2) * hex.q + Math.sqrt(3) * hex.r);
    return Point(x, y);
  };

  const Point = (x, y) => {
    return { x: x, y: y };
  };

  const Hex = (q, r) => {
    return { q: q, r: r };
  };

  const drawLine = (canvasID, start, end, color, line) => {
    const ctx = canvasID.getContext("2d");
    ctx.beginPath();
    ctx.moveTo(start.x, start.y);
    ctx.strokeStyle = color;
    ctx.lineWidth = line;
    ctx.lineTo(end.x, end.y);
    ctx.stroke();
    ctx.closePath();
  };

  const drawHexCoordinates = (canvasID, center, h, map) => {
    const ctx = canvasID.getContext("2d");
    ctx.fillStyle = "black";
    ctx.fillText(map.element.deposit, center.x - 5, center.y + 20);
    ctx.fillText(h.r, center.x - 10, center.y);
    ctx.fillText(h.q, center.x + 7, center.y);
  };

    return (
    <div>
      <canvas ref={canvasRef} width={width} height={height}></canvas>
    </div>
  );
}
