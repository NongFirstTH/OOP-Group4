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
            rows={25}
            disabled={isDisable}
            style={{ width: '400px', fontSize: '28px' }}
        />
    );
}

export default Plan;