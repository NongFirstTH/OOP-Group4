import React, { useState } from "react";
import Canvas from "./Canvas";
import "./styles/App.css";
import { useAppSelector } from "../../store/hooks.ts";
import { selectTerritory } from "../../store/Slices/territorySlice.ts";


const Map = () => {
  // const territoryState = useAppSelector(selectTerritory);
  // console.log(territoryState);
  const [currentRow, setCurrentRow] = useState(11);
  const [currentCol, setCurrentCol] = useState(11);
  return (
    <div>
      <Canvas
        width={10000}
        height={10000}
        rows={11}
        cols={13}
        currow={currentRow}
        curcol={currentCol}
        deposit={10}
      />
    </div>
  );
};

export default Map;
