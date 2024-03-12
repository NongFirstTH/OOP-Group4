import React, { useState, useEffect } from "react";
import Canvas from "./Canvas";
import "./styles/App.css";
import { useAppSelector} from "../../store/hooks.ts";
import { selectTerritory } from "../../store/Slices/territorySlice.ts";


const Map = () => {

  const territoryState = useAppSelector(selectTerritory);

  useEffect(() => {
      // console.log(territoryState);
      // console.log(territoryState.territory[0].length);
  }, [territoryState]);

  const [currow,setCurrow] = useState(4)
  const [curcol,setCurcol] = useState(9)
  const mappedArray = territoryState.territory.map((innerArray, i) => {
    return innerArray.map((element, j) => {
      return {i,j,element}; 
    });
  });
  return (
    <div>
      <Canvas
        width={10000}
        height={10000}
        mapArray = {mappedArray}
        currow = {currow}
        curcol = {curcol}
      />
    </div>
  );
};

export default Map;
