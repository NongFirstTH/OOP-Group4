import React, { useState } from "react";
import "./forApp.css";
import App from "./App.jsx";

const Index = () => {
  const [startGame, setStartGame] = useState(false);

  const onStartGame = () => {
    setStartGame(true);
  };

  const handleBack = () => {
    setStartGame(false);
  };

  return (
    <div>
      {startGame ? (
        <App onBack={handleBack} />
      ) : (
        <div className="app-container flex flex-col items-center justify-center h-screen">
          <img src="/img/upbeat_logo.png" alt="Game" className="mb-8" />{" "}
          {/* Added photo */}
          <h1 className="text-4xl font-bold mb-4">Welcome to UPBEAT</h1>
          <p className="text-lg text-gray-700 mb-8">
            Are you ready to embark on an exciting journey as the newly elected
            mayor of a budding city?{" "}
          </p>
          <button
            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
            onClick={onStartGame}
          >
            Start Game
          </button>
        </div>
      )}
    </div>
  );
};

export default Index;
