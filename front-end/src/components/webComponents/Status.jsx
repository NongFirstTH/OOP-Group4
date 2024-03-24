import "../../forApp.css";
import React from 'react';

function Status({ playerName, budget, deposit }) {
    const itemWidth = 100 / 2;
    return (
        <div className="status app-container" style={{ whiteSpace: "nowrap", width: "100%" }}>
            <div className="status-item" style={{ backgroundColor: "#1B1A55", display: "inline-block", width: `${itemWidth}%`, marginRight: "0" }}>
                <p style={{ fontSize: "19px", fontWeight: "bold" }}>Name: {playerName}</p>
            </div>
            <div className="status-item" style={{ backgroundColor: "#535C91", display: "inline-block", width: `${itemWidth}%`, marginRight: "0" }}>
                <p style={{ fontSize: "19px", fontWeight: "bold" }}>Budget: {budget} $</p>
            </div>
        </div>
    );
}

export default Status;
