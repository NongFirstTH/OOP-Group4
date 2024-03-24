import React, { useState } from 'react';
import '../../forApp.css';
import { useDispatch } from "react-redux";
import { selectConfig } from "../../store/Slices/configSlice.ts";
import { useAppSelector } from "../../store/hooks.ts";
import useWebSocket from "../../customHook/useWebSocket.ts";
import {
    selectPlan,
    setPlan as sliceSetPlan,
} from "../../store/Slices/planSlice.ts";
import Plan from './Plan.jsx';
import Timer from './Timer.jsx';

function DevisePlan() {
    const dispatch = useDispatch();
    const configState = useAppSelector(selectConfig);
    const planState = useAppSelector(selectPlan);
    const [plan, setPlan] = useState(planState.plan || '');
    const { devisePlan } = useWebSocket();
    const [isPlanVisible, setIsPlanVisible] = useState(true);
    const [alertMessage, setAlertMessage] = useState('');

    const onSubmit = () => {
        if (!planState.state) {
            setAlertMessage('Plan is not valid. Please revise and try again.');
            return;
        }
        dispatch(sliceSetPlan(plan));
        devisePlan(plan);
    };

    const onTimeOut = () => {
        devisePlan('{}');
    };

    const togglePlanVisibility = () => {
        setIsPlanVisible(!isPlanVisible);
    };

    return (
        <>
            <Timer initialTime={configState.init_plan_sec} onTimeOut={onTimeOut} />
            <form>
                {isPlanVisible && (
                    <div className="form-group">
                        <label htmlFor="plan">Plan:</label>
                        <Plan plan={plan} setPlan={setPlan} isDisable={!planState.state} state={planState.state}/>
                    </div>
                )}
            </form>
            {isPlanVisible && (
                <div style={{ marginTop: '10px' }}> {/* Added space */}
                    <button className="green-button" type="submit" onClick={onSubmit} disabled={!planState.state}>Submit Plan</button>
                </div>
            )}
            <div style={{ marginTop: '10px' }}> {/* Added space */}
                <button className="gray-button" onClick={togglePlanVisibility}>{isPlanVisible ? "Hide Plan" : "Show Plan"}</button>
            </div>
        </>
    );
}

export default DevisePlan;