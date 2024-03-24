import React, { useState, useEffect } from 'react';
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

    const onTimeOut = () => {
        onExecute(); // Call onExecute when the timer runs out
    };

    const togglePlanVisibility = () => {
        setIsPlanVisible(!isPlanVisible);
    };

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
                                            <label htmlFor="plan" className="form-label">Plan:</label>
                                            <Plan plan={plan} setPlan={setPlan} isDisable={!isRevise} />
                                        </div>
                                    </form>
                                    {isTurn && (
                                        <>
                                            {!isRevise && <Turn onExecute={onExecute} onRevise={onRevise}/>}
                                            {isRevise && <Revise onSubmitPlan={onSubmitPlan}/>}
                                        </>
                                    )}
                                </>
                            )}
                            <div style={{ marginTop: '10px' }}> {/* Added space */}
                                <button class="gray-button"onClick={togglePlanVisibility}>{isPlanVisible ? "Hide Plan" : "Show Plan"}</button>
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
