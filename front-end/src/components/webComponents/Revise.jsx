import React, { useState } from 'react';

function Revise({onSubmitPlan}) {
    return (
        <div style={{ marginTop: '10px' }}> {/* Added space */}
            <button type="button" onClick={onSubmitPlan}>Execute Plan</button>
        </div>
    );
}

export default Revise;
