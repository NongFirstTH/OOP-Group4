import React from 'react';
import './forApp.css';
import {useDispatch} from "react-redux";
import {setGameState} from "./store/Slices/webSocketSlice.ts";

function Init() {
    const dispatch = useDispatch();

    const onSubmit = () => {
        dispatch(setGameState('ADD'));
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
          <label htmlFor="m" className="form-label">M:</label>
          <input type="number" id="m" name="m" className="form-input" placeholder='rows' required/>
        </div>
        <div className="form-group">
          <label htmlFor="n" >N:</label>
          <input type="number" id="n" name="n" className="form-input" placeholder='cols' required/>
        </div>
      </form>

      <form>
      <div className="form-group">
          <label htmlFor="init_plan_min">Initial Plan Min:</label>
          <input
            type="number"
            id="init_plan_min"
            name="init_plan_min"
            className="form-input"
            placeholder='Initial Plan Minute'
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="init_plan_sec">Initial Plan Sec:</label>
          <input
            type="number"
            id="init_plan_sec"
            name="init_plan_sec"
            className="form-input"
            placeholder='Initial Plan Second'
            required
          />
        </div>
      </form>
            
      <form>
      <div className="form-group">
          <label htmlFor="init_budget">Init Budget:</label>
          <input
            type="number"
            id="init_budget"
            name="init_budget"
            className="form-input"
            placeholder='Initial Plan Minute'
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="init_center_dep">Init Center Dep:</label>
          <input
            type="number"
            id="init_center_dep"
            name="init_center_dep"
            className="form-input"
            placeholder='Init Center Deposit'
            required
          />
        </div>
      </form>
      

      <form>
      <div className="form-group">
          <label htmlFor="plan_rev_min">Plan Rev Min:</label>
          <input
            type="number"
            id="plan_rev_min"
            name="plan_rev_min"
            className="form-input"
            placeholder='Plan revisions Minute'
            required
          />
        </div>
        
        <div className="form-group">
          <label htmlFor="plan_rev_sec">Plan Rev Sec:</label>
          <input
            type="number"
            id="plan_rev_sec"
            name="plan_rev_sec"
            className="form-input"
            placeholder='Plan revisions Second'
            required
          />
        </div>
      </form>

      <form>
      <div className="form-group">
          <label htmlFor="rev_cost">Rev Cost:</label>
          <input
            type="number"
            id="rev_cost"
            name="rev_cost"
            className="form-input"
            placeholder='Plan revisions Second'
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="max_dep">Max Dep:</label>
          <input
            type="number"
            id="max_dep"
            name="max_dep"
            className="form-input"
            placeholder='Max Deposit'
            required
          />
        </div>
      </form>

      <form>
      <div className="form-group">
          <label htmlFor="interest_pct">Interest Percentage:</label>
          <input
            type="number"
            id="interest_pct"
            name="interest_pct"
            className="form-input"
            style={{width: '850px'}}
            placeholder='Interest Percentage'
            required
          />
        </div> 
      </form>
      <button type="submit" >Submit</button>
    </div>
    </div>
  );
}

export default Init;
       