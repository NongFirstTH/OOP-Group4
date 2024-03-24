import React from 'react';
import '../../forApp.css';

function Plan({ plan, setPlan, isDisable, state }) {
    return (
        <>
            <textarea
                type="text"
                id="plan"
                name="plan"
                className="form-input"
                value={plan}
                onChange={(e) => setPlan(e.target.value)}
                required
                rows={12}
                disabled={isDisable}
                style={{width:"400px", height:"490px",minWidth: '400px',fontSize: '20px', minHeight:"300px"}}
            />
            <div style={{ marginTop: '10px', minHeight: '2px' }}>
                {state && state.trim() !== '' && <div className={`alert active`}>{state}</div>}
            </div>
        </>
    );
}

export default Plan;