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
    init: boolean;
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
    interest_pct: 5,
    init: false
};

export const gameConfigSlice = createSlice({
    name: 'config',
    initialState,
    reducers: {
        setM: (state, action: PayloadAction<number>) => {
            state.m = action.payload;
        },
        setN: (state, action: PayloadAction<number>) => {
            state.n = action.payload;
        },
        setInitPlanMin: (state, action: PayloadAction<number>) => {
            state.init_plan_min = action.payload;
        },
        setInitPlanSec: (state, action: PayloadAction<number>) => {
            state.init_plan_sec = action.payload;
        },
        setInitBudget: (state, action: PayloadAction<number>) => {
            state.init_budget = action.payload;
        },
        setInitCenterDep: (state, action: PayloadAction<number>) => {
            state.init_center_dep = action.payload;
        },
        setPlanRevMin: (state, action: PayloadAction<number>) => {
            state.plan_rev_min = action.payload;
        },
        setPlanRevSec: (state, action: PayloadAction<number>) => {
            state.plan_rev_sec = action.payload;
        },
        setRevCost: (state, action: PayloadAction<number>) => {
            state.rev_cost = action.payload;
        },
        setMaxDep: (state, action: PayloadAction<number>) => {
            state.max_dep = action.payload;
        },
        setInterestPct: (state, action: PayloadAction<number>) => {
            state.interest_pct = action.payload;
        },
        setInit: (state, action: PayloadAction<boolean>) => {
            state.init = action.payload;
        },
    },
});

export const {
    setM,
    setN,
    setInitPlanMin,
    setInitPlanSec,
    setInitBudget,
    setInitCenterDep,
    setPlanRevMin,
    setPlanRevSec,
    setRevCost,
    setMaxDep,
    setInterestPct,
    setInit
} = gameConfigSlice.actions;

export default gameConfigSlice.reducer;

export const selectConfig = (state: RootState) => state.config;