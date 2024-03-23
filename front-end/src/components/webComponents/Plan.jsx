import React, { useState } from 'react';
import '../../forApp.css'
function Plan({ plan, setPlan, isDisable }) {
    const [isHidden, setIsHidden] = useState(false);

    const toggleHidden = () => {
        setIsHidden(!isHidden);
    };

    return (
        <div>
            <div>
            {!isHidden && (
                    <textarea
                        type="text"
                        id="plan"
                        name="plan"
                        className="form-input"
                        value={plan}
                        onChange={(e) => setPlan(e.target.value)}
                        required
                        rows={15}
                        disabled={isDisable}
                        style={{ width: '400px', fontSize: '28px' }}
                    />
                )}
            </div>  
                <div className='centered-button'>
                    <button onClick={toggleHidden}>{isHidden ? 'Show' : 'Hide'}</button>
                </div>
        </div>
    );
}

export default Plan;
