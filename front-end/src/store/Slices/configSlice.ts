import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import {RootState} from "../store.ts";

interface configState {
    config: string;
}

const initialState: configState = {
    config: "m=20\nn=15\ninit_plan_min=5\ninit_plan_sec=0\ninit_budget=10000\ninit_center_dep=100\nplan_rev_min=30\nplan_rev_sec=0\nrev_cost=100\nmax_dep=1000000\ninterest_pct=5",
};

export const gameConfigSlice = createSlice({
    name: 'config',
    initialState,
    reducers: {
        setConfig: (state, action: PayloadAction<string>) => {
            state.config = action.payload;
        },
    },
});

export const {setConfig} = gameConfigSlice.actions;
export default gameConfigSlice.reducer;
export const selectConfig = (state: RootState) => state.config.config;