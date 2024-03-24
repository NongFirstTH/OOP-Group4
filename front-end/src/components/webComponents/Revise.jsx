import React, { useState } from 'react';
import "../../forApp.css"
function Revise({onSubmitPlan, onBack}) {
    return (
        <>
            <button class="blue-button" type="button" onClick={onBack}>Back</button>
            <button class="yellow-button" type="button" onClick={onSubmitPlan}>Execute Plan</button>
        </>
    );
}

export default Revise;
