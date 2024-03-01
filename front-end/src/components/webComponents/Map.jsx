import React from "react";
import Canvas from "./Canvas";
import "./styles/App.css";
import { useAppSelector } from "../../store/hooks.ts";
import { selectTerritory } from "../../store/Slices/territorySlice.ts";


const Map = () => {
  const territoryState = useAppSelector(selectTerritory);
  console.log(territoryState);
    return (
      <div>
        <Canvas width={1000} height={1000} rows = {13} cols = {15} currow = {9} curcol = {7} deposit = {10}/>
      </div>
      );
    }

export default Map;