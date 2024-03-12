import React, { useState } from "react";
import Canvas from "./Canvas";
import "./styles/App.css";
import { useAppSelector } from "../../store/hooks.ts";
import { selectTerritory } from "../../store/Slices/territorySlice.ts";
import Player from "./Player.jsx";


const Map = () => {
  // const territoryState = useAppSelector(selectTerritory);
  // console.log(territoryState);
  const [currentRow, setCurrentRow] = useState(1);
  const [currentCol, setCurrentCol] = useState(3);
  // Example usage:
  const player1 = new Player("Player 1", 11, 13);

  // Set regions in the matrix
  player1.setRegion(0, 0, "Player 1");
  player1.setRegion(0, 1, "Player 1");
  player1.setRegion(2, 1, "Player 1");
  player1.setRegion(2, 2, "Player 1");

  // Get a region from the matrix
  // const regionA = player1.getRegion(0, 0);
  // console.log(`Region at (0, 0): ${regionA}`);

  // // Access the entire matrix
  // console.log(player1.regionMatrix);
  // console.log(player1.getRows());
  // console.log(player1.getName());
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
        player ={player1}
      />
    </div>
  );
};

export default Map;
