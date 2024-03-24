import React from 'react';

function Plan({ plan, setPlan, isDisable }) {
    return (
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
            style={{width:"400px", height:"500px",minWidth: '400px',fontSize: '28px', minHeight:"300px"}}
        />
    );
}

export default Plan;