// import ReactDOM from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import React, { useState, useEffect } from "react";
import "./forApp.css";
import Map from "./components/webComponents/Map.jsx";
import {useDispatch} from "react-redux";
import useWebSocket from "./customHook/useWebSocket.ts";
import {setUsername as sliceSetUsername} from "./store/Slices/usernameSlice.ts";
import {setGameState} from "./store/Slices/webSocketSlice.ts";

// import {selectTerritory} from "./store/Slices/territorySlice.ts";

import {  useAppSelector } from "./store/hooks.ts";

function AddPlayer() {

//   const territoryState = useAppSelector(selectTerritory);

  const [players, setPlayers] = useState([]);
  const [player, setPlayer] = useState("");
  const dispatch = useDispatch();
  const {addPlayer,getTerritory} = useWebSocket();

//       useEffect(() => {
//         console.log(territoryState);
//       }, [player]);

//   const territoryState = useAppSelector((state) => state.territory.territory);

  const onClickStart = () => {
//     getTerritory();
//     console.log(territoryState);
    dispatch(setGameState('DEVISE'));
  };

    const onBack = () => {
  //     getTerritory();
  //     console.log(territoryState);
      dispatch(setGameState('INIT'));
    };

  const handleCharacterCreation = (event) => {
    event.preventDefault();

    const name = event.target.elements.name.value;

    if (!name) {
      alert("Please enter a name for your character.");
      return;
    }

    const existingPlayer = players.find((player) => player.name === name);

    if (existingPlayer) {
      alert("This name is already taken. Please choose a different name.");
    } else {
      const newPlayer = { id: Date.now(), name };
      setPlayer(newPlayer.name);
      setPlayers([...players, newPlayer]);
        dispatch(sliceSetUsername(newPlayer.name))
        addPlayer(newPlayer.name)
        getTerritory()
    }
  };

  return (
    <div>
        <div className="app-container h-screen bg-[#070F2B] flex flex-col justify-between">
      <div className="centered-image">
      <img src="/img/newlogo.png" alt="Game" className="mb-8" />
      </div>
      <h1>Create City Crew</h1>
      <div className="container flex flex-cols">
      <div className="creation-section">
      <form onSubmit={handleCharacterCreation}>
      <label htmlFor="name">Character Name: </label>
      <input
      type="text"
      id="name"
      name="name"
      placeholder="Enter your name"
      />
      <button type="submit">Create Character</button>
          </form>
        </div>
        <div className="players-section">
        <h2>Players:</h2>
        {players.length > 0 ? (
          <ul>
          {players.map((player) => (
            <li key={player.id}>{player.name}</li>
            ))}
            </ul>
            ) : (
              <p>No players yet. Be the first to join!</p>
              )}
              </div>
              </div>
              <div className="button-container fixed bottom-0 flex justify-between w-full">
              <div className="button">
              <button onClick={onBack}>Back</button>
              <button onClick={onClickStart}>Start</button>
               </div>
              </div>
          </div>
      )
    </div>
  );
}

export default AddPlayer;
