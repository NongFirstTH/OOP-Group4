import React, { useState, useEffect } from "react";
import Canvas from "./Canvas";
import "./styles/App.css";
import { useAppSelector } from "../../store/hooks.ts";
import { selectTerritory } from "../../store/Slices/territorySlice.ts";
import { selectConfig } from "../../store/Slices/configSlice.ts";
import { selectWebSocket } from "../../store/Slices/webSocketSlice.ts";

const Map = () => {
  const webSocketState = useAppSelector(selectWebSocket);
  const territoryState = useAppSelector(selectTerritory);
  const configState = useAppSelector(selectConfig);
  const [initTime, setInitTime] = useState(
    // configState.init_plan_min * 60 + configState.init_plan_sec
    30
  );
  const [reviseTime, setReviseTime] = useState(
    // configState.plan_rev_min * 60 + configState.plan_rev_sec
    10
  );

  useEffect(() => {
    let intervalId;
    function countDown(setGameTime, initialTime) {
      intervalId = setInterval(() => {
        setGameTime((prevTime) => {
          if (prevTime === 0) {
            clearInterval(intervalId); // Stop the interval when time reaches 0
            return 0;
          }
          return prevTime - 1;
        });
      }, 1000);
    }


    if (webSocketState.gameState === "DEVISE") {
      countDown(setInitTime, initTime); // Start DEVISE countdown
      return () => clearInterval(intervalId); // Clear interval on component unmount
    }else{
      setReviseTime(10)
      countDown(setReviseTime, reviseTime); // Start REVISE countdown
      return () => clearInterval(intervalId); // Clear interval on component unmount
    }
  }, [webSocketState]);

  const mappedArray = territoryState.territory.map((innerArray, i) => {
    return innerArray.map((element, j) => {
      return { i, j, element };
    });
  });

  return (
    <div>
      {webSocketState.gameState === "DEVISE" ? (
        <h1 style={{ color: "white" }}>INIT : {initTime} sec</h1>
      ) : (
        <h2 style={{ color: "white" }}>REVISE : {reviseTime} sec</h2>
      )}

      <Canvas mapArray={mappedArray} />
    </div>
  );
};

export default Map;
