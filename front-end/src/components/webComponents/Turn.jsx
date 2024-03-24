import React, { useState } from 'react';
import "../../forApp.css"
function Turn({onRevise, onExecute}) {
    return (
        <>
            <button class="blue-button"type="button" onClick={onRevise}>Revise Plan</button>
            <button class="red-button"type="button" onClick={onExecute}>Execute Plan</button>
        </>
    );
}

export default Turn;
