import React, { createRef, useEffect } from "react";
import "./styles/App.css";

export default class Canvas extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      hexSize: 40,
      hexOrigin: { x: 0, y: 0 },
      rows: this.props.mapArray.length,
      cols: 15,
      currentHex: { currow: this.props.currow, curcol: this.props.curcol },
      playerName: 'A',
    };
    this.canvasRef = createRef();
  }
  componentDidMount = () => {
    const canvasHex = this.canvasRef.current;
    // this.drawHex(canvasHex, { x: 50, y: 50 });
    // console.log(this.props.mapArray);
    this.drawHexes(canvasHex);
  };

  calculateCanvasSize() {
    const { hexWidth, hexHeight, vertDist, horizDist } =
      this.getHexParameters();
    const { rows, cols } = this.state;
    const width = cols * horizDist + hexWidth / 2 + 10;
    const height = rows * vertDist + hexHeight + 20;
    return { width, height };
  }

  evenq_to_axial(hex) {
    const q = hex.col;
    const r = hex.row - (q - (q & 1)) / 2;
    return this.Hex(q, r);
  }

  drawHexes(canvas) {
    const { hexWidth, hexHeight, vertDist, horizDist } =
      this.getHexParameters();
    const { width, height } = this.props;
    const hexOrigin = {
      x: hexWidth / 2 + horizDist,
      y: vertDist + 10,
    };
    this.setState({ hexOrigin });
    // for (let r = 1; r <= this.state.rows; r++) {
    //   for (let q = 1; q <= this.state.cols; q++) {
      this.props.mapArray.map((innerArray, q) => {
        innerArray.map((element,r) => {
        const evenQCoord = { col: q+1, row: r+1};
        const axialCoord = this.evenq_to_axial(evenQCoord);
        let center = this.hexToPixel(axialCoord);
        if (
          center.x > hexWidth / 2 &&
          center.x < width - hexWidth &&
          center.y > hexHeight / 2 &&
          center.y < height - hexHeight
          ) {
            // console.log(this.props.mapArray[r - 1][q - 1].element);
          console.log(this.state.playerName);
          this.drawHex(
            canvas,
            center,
            this.Hex(q+1, r+1),
            element
          );
          if (element.player !== null)
            this.drawHexCoordinates(
              canvas,
              center,
              this.Hex(q+1, r+1),
              element
            );
        }
      })
    })
  }

  drawHex(canvasID, center, hex, map) {
    for (let i = 0; i <= 5; i++) {
      let start = this.getHexCornorCoord(center, i);
      let end = this.getHexCornorCoord(center, i + 1);
       if (
        // this.state.currentHex &&
        // map.element.player !== null &&
        map.element.player === this.state.playerName
      ) {
        this.drawLine(
          canvasID,
          { x: start.x, y: start.y },
          { x: end.x, y: end.y },
          "black",
          1
        );
        this.fillHex(canvasID, center, "#1D8348");
      } 
      else if (
        map.element.player !== null
      ) {
        this.fillHex(canvasID, center, "#2ECC71 ");
      }
       else {
        this.drawLine(
          canvasID,
          { x: start.x, y: start.y },
          { x: end.x, y: end.y },
          "blue",
          2
        );
        this.fillHex(canvasID, center, "white");
      }
      if(hex.r == this.state.currentHex.currow && hex.q == this.state.currentHex.curcol){
        this.fillHex(canvasID, center, "red");
      }
    }
  }

  
  fillHex(canvasID, center, fillColor) {
    const ctx = canvasID.getContext("2d");
    ctx.beginPath();
    for (let i = 0; i <= 5; i++) {
      let corner = this.getHexCornorCoord(center, i);
      if (i === 0) {
        ctx.moveTo(corner.x, corner.y);
      } else {
        ctx.lineTo(corner.x, corner.y);
      }
    }
    ctx.closePath();

    ctx.fillStyle = fillColor;
    ctx.fill();
  }

  setCurrentHex(q, r) {
    this.setState({ currentHex: { q, r } });
  }

  getHexCornorCoord(center, i) {
    let angle_deg = 60 * i;
    let angle_rad = (Math.PI / 180) * angle_deg;
    let x = center.x + this.state.hexSize * Math.cos(angle_rad);
    let y = center.y + this.state.hexSize * Math.sin(angle_rad);
    return this.Point(x, y);
  }

  getHexParameters() {
    let hexWidth = this.state.hexSize * 2;
    let hexHeight = Math.sqrt(3) * this.state.hexSize;
    let vertDist = hexHeight;
    let horizDist = (3 / 2) * this.state.hexSize;
    return { hexWidth, hexHeight, vertDist, horizDist };
  }

  hexToPixel(hex) {
    let hexOrigin = this.state.hexOrigin;
    let x = this.state.hexSize * ((3 / 2) * hex.q) + hexOrigin.x;
    let y =
      this.state.hexSize * ((Math.sqrt(3) / 2) * hex.q + Math.sqrt(3) * hex.r) +
      hexOrigin.y;
    return this.Point(x, y);
  }

  Point(x, y) {
    return { x: x, y: y };
  }

  Hex(q, r) {
    return { q: q, r: r };
  }

  drawLine(canvasID, start, end, color, line) {
    const ctx = canvasID.getContext("2d");
    ctx.beginPath();
    ctx.moveTo(start.x, start.y);
    ctx.strokeStyle = color;
    ctx.lineWidth = line;
    ctx.lineTo(end.x, end.y);
    ctx.stroke();
    ctx.closePath();
  }

  drawHexCoordinates(canvasID, center, h, map) {
    const ctx = canvasID.getContext("2d");
    ctx.fillStyle = "black";
    ctx.fillText(map.element.deposit, center.x - 5, center.y + 20);
    ctx.fillText(h.r, center.x - 10, center.y);
    ctx.fillText(h.q, center.x + 7, center.y);
  }

  useEffect = () => {
    this.setState({
      currentHex: { currow: this.props.currow, curcol: this.props.curcol },
    });
  };

  render() {
    const { width, height } = this.calculateCanvasSize();
    return (
      <div>
        <canvas ref={this.canvasRef} width={width} height={height}></canvas>
      </div>
    );
  }
}
