import React, { useState, useEffect } from "react";
import Canvas from "./Canvas";
import Player from "./Player";
import "./styles/App.css";
import { useAppSelector} from "../../store/hooks.ts";
import { selectTerritory } from "../../store/Slices/territorySlice.ts";


const Map = () => {

  const territoryState = useAppSelector(selectTerritory);

  useEffect(() => {
      // console.log(territoryState);
      // console.log(territoryState.territory[0].length);
  }, [territoryState]);

  const [player1] = useState(new Player("b","red", 20, 15));
  player1.setRegion(0,0,"b")
  player1.setRegion(0,1,"b")
  player1.setRegion(0,2,"b")
  player1.setRegion(1,0,"b")

  const [player2] = useState(new Player("c","blue", 20, 15));
  player2.setRegion(4,3,"c")
  player2.setRegion(4,2,"c")
  player2.setRegion(5,2,"c")
  player2.setRegion(6,3,"c")
  player2.setRegion(5,4,"c")
  player2.setRegion(4,4,"c")
  player2.setRegion(5,3,"c")

  const mappedArray = territoryState.territory.map((innerArray, i) => {
    return innerArray.map((element, j) => {
      return {i,j,element}; 
    });
  });
  
  return (
    <div>
      <Canvas
        mapArray = {mappedArray}
        player = {{p1:player1,p2:player2}}
        />
    </div>
  );
};

export default Map;
