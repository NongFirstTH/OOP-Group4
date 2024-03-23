import React, { useState, useEffect } from "react";
import Canvas from "./Canvas";
import "./styles/App.css";
import { useAppSelector } from "../../store/hooks.ts";
import { selectTerritory } from "../../store/Slices/territorySlice.ts";
import { selectWebSocket } from "../../store/Slices/webSocketSlice.ts";

const Map = () => {
  const webSocketState = useAppSelector(selectWebSocket);
  const territoryState = useAppSelector(selectTerritory);
  const [zoomLevel, setZoomLevel] = useState(1);

  const mappedArray = territoryState.territory.map((innerArray, i) => {
    return innerArray.map((element, j) => {
      return { i, j, element };
    });
  });

  const handleZoomIn = () => {
    setZoomLevel((prevZoomLevel) => prevZoomLevel + 0.1);
  };

  const handleZoomOut = () => {
    setZoomLevel((prevZoomLevel) => Math.max(0.1, prevZoomLevel - 0.1));
  };

  return (
  <>
      <div className="zoom-buttons">
        <button onClick={handleZoomIn}>Zoom In</button>
        <button onClick={handleZoomOut}>Zoom Out</button>
      </div>
    <div
      style={{
        width: "800px", // Set your desired width
        height: "600px", // Set your desired height
        overflow: "auto", // Enable scrolling
        border: "1px solid #ccc", // Add a border for visualization
      }}
    >
      <Canvas mapArray={mappedArray} zoomLevel={zoomLevel} />
    </div>
    </>
  );
};

export default Map;