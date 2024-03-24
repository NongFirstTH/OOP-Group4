import React, { useState } from "react";
import "./forApp.css";
import Init from "./Init.jsx";
import AddPlayer from "./AddPlayer.jsx";
import Start from "./components/webComponents/Start.tsx";
import Map from "./components/webComponents/Map.jsx";
import DevisePlan from "./components/webComponents/DevisePlan.jsx";
import Game from "./components/webComponents/Game.jsx";
import End from "./components/webComponents/End.jsx";
import NotIn from "./components/webComponents/NotIn.jsx";
import { useAppSelector } from "./store/hooks.ts";
import { selectWebSocket } from "./store/Slices/webSocketSlice.ts";
import { selectConfig } from "./store/Slices/configSlice.ts";
import { selectUsername } from "./store/Slices/usernameSlice.ts";

const Index = () => {
  const webSocketState = useAppSelector(selectWebSocket);
  const configState = useAppSelector(selectConfig);
  const usernameState = useAppSelector(selectUsername);

  if (!webSocketState.isStart) {
    return <Start />;
  } else {
    switch (webSocketState.gameState) {
      case 'START':
        return <Start />;
      case 'INIT':
        return configState.init ? <AddPlayer /> : <Init />;
      case 'ADD':
        return <AddPlayer />;
      case 'END' :
        return <End />;
      default:
        return (
            usernameState.username ? (
                <Game isDevise={webSocketState.gameState === 'DEVISE'} isTurn={webSocketState.turn === usernameState.username} turn={webSocketState.turn} />
            ) : (
                <NotIn/>
            )
        );
    }
  }
};

export default Index;