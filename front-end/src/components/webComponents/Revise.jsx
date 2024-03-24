import React, { useState } from 'react';
import "../../forApp.css"
function Revise({onSubmitPlan}) {
    return (
        <div style={{ marginTop: '10px' }}> {/* Added space */}
            <button class="yellow-button" type="button" onClick={onSubmitPlan}>Execute Plan</button>
        </div>
    );
}

export default Revise;
