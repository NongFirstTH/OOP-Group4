import React, { useState, useEffect } from 'react';
import '../../forApp.css';
import { useDispatch } from "react-redux";
import { selectConfig } from "../../store/Slices/configSlice.ts";
import { useAppSelector } from "../../store/hooks.ts";
import useWebSocket from "../../customHook/useWebSocket.ts";
import {
    selectPlan,
    setPlan as sliceSetPlan,
    setOK
} from "../../store/Slices/planSlice.ts";
import Plan from './Plan.jsx';
import Timer from './Timer.jsx';
import DevisePlan from './DevisePlan.jsx';
import Turn from './Turn.jsx';
import Revise from './Revise.jsx';
import Map from './Map.jsx';

function Game({ isDevise, isTurn, turn }) {
    const dispatch = useDispatch();
    const configState = useAppSelector(selectConfig);
    const planState = useAppSelector(selectPlan);
    const [plan, setPlan] = useState(planState.plan || '');
    const { executePlan, setState, revisePlan } = useWebSocket();
    const [isRevise, setIsRevise] = useState(false);
    const [isPlanVisible, setIsPlanVisible] = useState(true);

    useEffect(() => {
        setPlan(planState.plan);
    }, [planState.plan]);

    const onRevise = () => {
        setIsRevise(true);
    };

    const onExecute = () => {
        executePlan();
        setIsRevise(false);
    };

    const onSubmitPlan = () => {
        revisePlan(plan);
        dispatch(sliceSetPlan(plan));
        setIsRevise(false);
    };

    const onBack = () => {
        setIsRevise(false);
        setPlan(planState.prev);
        dispatch(sliceSetPlan(planState.prev));
        dispatch(setOK(null));
    };

    const onTimeOut = () => {
        onExecute(); // Call onExecute when the timer runs out
    };

    const togglePlanVisibility = () => {
        setIsPlanVisible(!isPlanVisible);
    };

    const showRevise = isRevise?true:planState.state;

    return (
        <div style={{ display: 'flex' }}>
            <div style={{
                justifyContent: 'center',
                alignItems: 'center',
                Height: '100%'
            }}>
                <div className="app-container">
                    {isDevise ? (<DevisePlan/>) : (
                        <>
                            {isTurn ? (
                              <Timer initialTime={configState.plan_rev_sec} onTimeOut={onTimeOut} />
                            ) : (
                              <div style={{ height: '20px' }}>Player {turn}'s Turn!</div>
                            )}
                            {isPlanVisible && (
                                <>
                                    <form>
                                        <div className="form-group">
                                            <label htmlFor="plan" className="form-label" style={{ fontSize: "25px", fontWeight: "bold" }}>Plan:</label>
                                            <Plan plan={plan} setPlan={setPlan} isDisable={!showRevise} state={planState.state} />
                                        </div>
                                    </form>
                                    {isTurn && (
                                        <>
                                            {!showRevise && <Turn onExecute={onExecute} onRevise={onRevise}/>}
                                            {showRevise && <Revise onSubmitPlan={onSubmitPlan} onBack={onBack}/>}
                                        </>
                                    )}
                                </>
                            )}
                            <div style={{ marginTop: '10px' }}> {/* Added space */}
                                <button className="gray-button" onClick={togglePlanVisibility}>{isPlanVisible ? "Hide Plan" : "Show Plan"}</button>
                            </div>
                        </>
                    )}
                </div>
            </div>
            <Map />
        </div>
    );
}

export default Game;
