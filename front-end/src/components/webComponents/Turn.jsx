import React, { useState } from 'react';

function Turn({onRevise, onExecute}) {
    return (
        <>
            <button type="button" onClick={onRevise}>Revise Plan</button>
            <button type="button" onClick={onExecute}>Execute Plan</button>
        </>
    );
}

export default Turn;
