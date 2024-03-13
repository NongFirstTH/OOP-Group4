import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { RootState } from "../store.ts";

interface Territory {
    player: string|null;
    deposit: number;
}

interface TerritoryState {
    territory: Territory[][];
    row: number;
    col: number;
    currow: number;
    curcol: number;
}

const initialState: TerritoryState = {
    territory: [],
    row: 0,
    col: 0,
    currow: 0,
    curcol: 0,
};

export const territorySlice = createSlice({
    name: 'territory',
    initialState,
    reducers: {
        setTerritory: (state, action: PayloadAction<TerritoryState>) => {
            state.territory = action.payload.territory;
        },
        setRow: (state, action: PayloadAction<TerritoryState>) => {
        state.row = action.payload.row;
        },
        setCol: (state, action: PayloadAction<TerritoryState>) => {
        state.col = action.payload.col;
        },
        setCurrow: (state, action: PayloadAction<TerritoryState>) => {
        state.currow = action.payload.currow;
        },
        setCurcol: (state, action: PayloadAction<TerritoryState>) => {
        state.curcol = action.payload.curcol
        },
    },
});

export const { setTerritory, setRow, setCol, setCurrow, setCurcol } = territorySlice.actions;
export default territorySlice.reducer;
export const selectTerritory = (state: RootState) => state.territory;