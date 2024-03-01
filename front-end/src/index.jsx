import React, { useState } from "react";
import "./forApp.css";
import Init from "./Init.jsx";
import AddPlayer from "./AddPlayer.jsx";
import Start from "./components/webComponents/Start.tsx";
import Map from "./components/webComponents/Map.jsx";
import ChatBox from "./components/ChatBox.tsx";
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
     {webSocketState.gameState=='INIT' && <Init /> }
     {webSocketState.gameState=='ADD' && <AddPlayer /> }
     {webSocketState.gameState=='GAME' && <Map /> }
     <ChatBox />
   </div>
 );
};

export default Index;