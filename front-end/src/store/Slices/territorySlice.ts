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
            state.row = action.payload.row;
            state.col = action.payload.col;
            state.currow = action.payload.currow;
            state.curcol = action.payload.curcol;
        },
    },
});

export const { setTerritory } = territorySlice.actions;
export default territorySlice.reducer;
export const selectTerritory = (state: RootState) => state.territory;