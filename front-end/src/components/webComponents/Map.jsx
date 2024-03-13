import React, { useState, useEffect } from "react";
import Canvas from "./Canvas";
import Player from "./Player";
import "./styles/App.css";
import { useAppSelector} from "../../store/hooks.ts";
import { selectTerritory } from "../../store/Slices/territorySlice.ts";
import { selectConfig } from "../../store/Slices/configSlice.ts";


const Map = () => {

  const territoryState = useAppSelector(selectTerritory);
  // const configState = useAppSelector(selectConfig);
  // const [initTime,setInitTime] = useState(configState.init_plan_min*60 + configState.init_plan_sec)

  // useEffect(() => {
  //     const interval = setInterval(() => {
  //       setInitTime(prevTime => {
  //         if (prevTime === 0) {
  //           clearInterval(interval); // Stop the interval if initTime reaches 0
  //           return 0; // Return 0 to prevent negative values
  //         }
  //         return prevTime - 1;
  //       });
  //     }, 1000);
  //     // Clean up function to clear the interval when component unmounts or when initTime changes
  //     return () => clearInterval(interval);
  //   }, [configState]);

  useEffect(() => {
      // console.log(territoryState);
      // console.log(territoryState.territory[0].length);
  }, [territoryState]);

  // const [player1] = useState(new Player("b","red", 20, 15));
  // player1.setRegion(0,0,"b")
  // player1.setRegion(0,1,"b")
  // player1.setRegion(0,2,"b")
  // player1.setRegion(1,0,"b")

  // const [player2] = useState(new Player("c","blue", 20, 15));
  // player2.setRegion(4,3,"c")
  // player2.setRegion(4,2,"c")
  // player2.setRegion(5,2,"c")
  // player2.setRegion(6,3,"c")
  // player2.setRegion(5,4,"c")
  // player2.setRegion(4,4,"c")
  // player2.setRegion(5,3,"c")

  const mappedArray = territoryState.territory.map((innerArray, i) => {
    return innerArray.map((element, j) => {
      return {i,j,element}; 
    });
  });
  
  return (
    <div>
       {console.log(mappedArray)}
        {/* <h1 style={{ color: "white" }}>{initTime}</h1> */}
      <Canvas
        mapArray = {mappedArray}
        // player = {{p1:player1,p2:player2}}
        />
    </div>
  );
};

export default Map;
