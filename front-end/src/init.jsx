import React from 'react';
import './forApp.css';

function SimpleForm() {
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
          <label htmlFor="n" >N:</label>
          <input type="number" id="n" name="n" className="form-input" placeholder='e.g. 123456' required/>
        </div>
        <div className="form-group">
          <label htmlFor="m" className="form-label">M:</label>
          <input type="number" id="m" name="m" className="form-input" placeholder='e.g. 123456' required/>
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
            placeholder='e.g. 123456'
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
            placeholder='e.g. 123456'
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
            placeholder='e.g. 123456'
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
            placeholder='e.g. 123456'
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
            placeholder='e.g. 123456'
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
            placeholder='e.g. 123456'
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
            placeholder='e.g. 123456'
            required
          />
        </div> 
      </form>
      <button type="submit" >Submit</button>
    </div>
    </div>
  );
}

export default SimpleForm;
       