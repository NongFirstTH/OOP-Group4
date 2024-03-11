import React, {useState} from 'react';
import '../../forApp.css';
import {useDispatch} from "react-redux";
import {setGameState} from "../../store/Slices/webSocketSlice.ts";
import {  useAppSelector } from "../../store/hooks.ts";
import useWebSocket from "../../customHook/useWebSocket.ts";
import {
    selectPlan,
    setPlan as sliceSetPlan,
} from "../../store/Slices/planSlice.ts";

function DevisePlan() {
    const dispatch = useDispatch();
    const planState = useAppSelector(selectPlan);
    const [plan, setPlan] = useState(planState || '');
    const {devisePlan} = useWebSocket();

    const onSubmit = () => {
//         dispatch(setGameState('GAME'));
        dispatch(sliceSetPlan(plan));
        devisePlan(1, plan);
    };

    return (
        <div style={{
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            minHeight: '100vh'
        }}>
            <div className="app-container">
                <form>
                    <div className="form-group">
                        <label htmlFor="plan" className="form-label">plan:</label>
                        <textarea
                            type="text"
                            id="plan"
                            name="plan"
                            className="form-input"
                            value={plan}
                            onChange={(e) => setPlan(e.target.value)}
                            required
                            rows={30}
                            style={{ width: '400px' , fontSize: '28px' }}
                        />
                    </div>
                </form>
                <button type="submit" onClick={onSubmit}>submit plan</button>
            </div>
        </div>
    );
}

export default DevisePlan;