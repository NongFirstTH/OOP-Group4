import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import {RootState} from "../store.ts";

interface planState {
    plan: string;
}

const initialState: planState = {
    plan: '',
};

export const planSlice = createSlice({
    name: 'plan',
    initialState,
    reducers: {
        setPlan: (state, action: PayloadAction<string>) => {
            state.plan = action.payload;
        },
    },
});

export const {setPlan} = planSlice.actions;
export default planSlice.reducer;
export const selectPlan = (state: RootState) => state.plan;