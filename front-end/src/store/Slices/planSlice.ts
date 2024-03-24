import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import {RootState} from "../store.ts";

interface planState {
    plan: string;
    prev: string;
    state: string;
}

const initialState: planState = {
    plan: '',
    prev: '',
    state: ' ',
};

export const planSlice = createSlice({
    name: 'plan',
    initialState,
    reducers: {
        setPlan: (state, action: PayloadAction<string>) => {
            state.plan = action.payload;
        },
        setOK: (state, action: PayloadAction<string>) => {
            state.state = action.payload;
            if(action.payload==null) state.prev=state.plan;
        },
    },
});

export const {setPlan, setOK} = planSlice.actions;
export default planSlice.reducer;
export const selectPlan = (state: RootState) => state.plan;