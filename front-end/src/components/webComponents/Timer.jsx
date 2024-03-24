import React, { useState, useEffect } from 'react';

function Timer({ initialTime, onTimeOut }) {
  const [time, setTime] = useState(initialTime);
  const [timeOutCalled, setTimeOutCalled] = useState(false);

  useEffect(() => {
    if (time > 0) {
      const timerId = setInterval(() => {
        setTime((prevTime) => prevTime - 1);
      }, 1000);

      return () => clearInterval(timerId);
    } else {
      if (!timeOutCalled) {
        onTimeOut();
        setTimeOutCalled(true);
      }
    }
  }, [time, onTimeOut, timeOutCalled]);

  return (
    <div className="timer-container" style={{ 
      display: "flex",
      alignItems: "center",
      justifyContent: "center",
      
    }}>
      <div style={{ 
        fontSize: "25px", 
        backgroundColor: "#891652", 
        padding: "5px 20px", 
        borderRadius: "20px" 
      }}>
        {time} sec
      </div>
    </div>
  );
}

export default Timer;
