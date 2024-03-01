import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import {RootState} from "../store.ts";

interface configState {
    m: number;
    n: number;
    init_plan_min: number;
    init_plan_sec: number;
    init_budget: number;
    init_center_dep: number;
    plan_rev_min: number;
    plan_rev_sec: number;
    rev_cost: number;
    max_dep: number;
    interest_pct: number;
}

const initialState: configState = {
    m: 20,
    n: 15,
    init_plan_min: 5,
    init_plan_sec: 0,
    init_budget: 10000,
    init_center_dep: 100,
    plan_rev_min: 30,
    plan_rev_sec: 0,
    rev_cost: 100,
    max_dep: 1000000,
    interest_pct: 5
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