import React, { useState } from "react";
import "./forApp.css";
import AddPlayer from "./AddPlayer.jsx";
import Start from "./components/webComponents/Start.tsx";
import Map from "./components/webComponents/Map.jsx";
import { useAppSelector } from "./store/hooks.ts";
import { selectWebSocket } from "./store/Slices/webSocketSlice.ts";


const Index = () => {


 const webSocketState = useAppSelector(selectWebSocket);


//   const [startGame, setStartGame] = useState(false);


//   const onStartGame = () => {
//     setStartGame(true);
//   };
//
//   const handleBack = () => {
//     setStartGame(false);
//   };


 return (
   <div>
     {console.log(webSocketState.gameState)}
     {webSocketState.gameState=='START' && <Start /> }
     {webSocketState.gameState=='ADD' && <AddPlayer /> }
     {webSocketState.gameState=='GAME' && <Map /> }
   </div>
 );
};

export default Index;