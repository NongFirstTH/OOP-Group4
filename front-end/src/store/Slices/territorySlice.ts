import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { RootState } from "../store.ts";

interface Territory {
    player: string|null;
    deposit: number;
}

interface TerritoryState {
    territory: Territory[][];
}

const initialState: TerritoryState = {
    territory: [],
};

export const territorySlice = createSlice({
    name: 'territory',
    initialState,
    reducers: {
        setTerritory: (state, action: PayloadAction<Territory[][]>) => {
            state.territory = action.payload;
        },
    },
});

export const { setTerritory } = territorySlice.actions;
export default territorySlice.reducer;
export const selectTerritory = (state: RootState) => state.territory;