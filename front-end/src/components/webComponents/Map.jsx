import React, { useState } from "react";
import Canvas from "./Canvas";
import Slider from "./Slide";
import Status from "./Status";

import "../../forApp.css";
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

  const handleZoomChange = (newZoomLevel) => {
    setZoomLevel(newZoomLevel);
  };

  return (
    <div className="map-container">
      <Status playerName="Player 1" budget={1000} deposit={500} />
      <div className="canvas-slider-container">
        <div
          className="canvas-container"
          style={{
            width: "800px", // Adjusted width considering the status bar
            height: "585px", // Set your desired height
            overflow: "auto", // Enable scrolling
            border: "1px solid #ccc", // Add a border for visualization
          }}
        >
          <Canvas mapArray={mappedArray} zoomLevel={zoomLevel} />
        </div>
        <div className="slider-wrapper">
          <Slider min={0.1} max={1.5} value={zoomLevel} onChange={handleZoomChange} />
        </div>
      </div>
    </div>
  );
};

export default Map;
