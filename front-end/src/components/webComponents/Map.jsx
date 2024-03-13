import React, { useState, useEffect } from "react";
import Canvas from "./Canvas";
import CanvasTemp from "./CanvasTemp";
import "./styles/App.css";
import { useAppSelector} from "../../store/hooks.ts";
import { selectTerritory } from "../../store/Slices/territorySlice.ts";


const Map = () => {

  const territoryState = useAppSelector(selectTerritory);

  const [currow,setCurrow] = useState(8)
  const [curcol,setCurcol] = useState(9)
  useEffect(() => {
      // console.log(territoryState);
      // console.log(territoryState.territory[0].length);
  }, [territoryState]);


  const mappedArray = territoryState.territory.map((innerArray, i) => {
    return innerArray.map((element, j) => {
      return {i,j,element}; 
    });
  });
  
  return (
    <div>
      <Canvas
        mapArray = {mappedArray}
        currow = {currow}
        curcol = {curcol}
      />
    </div>
  );
};

export default Map;
