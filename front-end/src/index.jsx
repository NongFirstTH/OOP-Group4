import React, { useState } from "react";
import "./forApp.css";
import Init from "./Init.jsx";
import AddPlayer from "./AddPlayer.jsx";
import Start from "./components/webComponents/Start.tsx";
import Map from "./components/webComponents/Map.jsx";
import DevisePlan from "./components/webComponents/DevisePlan.jsx";
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


//  return (
//    <div>
     {console.log(webSocketState.gameState)}
    switch (webSocketState.gameState) {
      case 'START':
        return <Start />;
      case 'INIT':
        return <Init />;
      case 'ADD':
        return <AddPlayer />;
      case 'DEVISE':
        return  <div style={{ display: 'flex' }}>
                 <div style={{ flex: 1 }}>
                   <DevisePlan />
                 </div>
                 <div style={{ flex: 1 }}>
                   <Map />
                 </div>
               </div>;
      case 'GAME':
        return <Map />;
      default:
        return null; // or some default component
    }
//    </div>
//  );
};

export default Index;