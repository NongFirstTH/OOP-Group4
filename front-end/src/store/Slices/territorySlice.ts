import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import {RootState} from "../store.ts";

interface TerritoryState {
    territory: number[];
}

const initialState: TerritoryState = {
    territory: [],
};

export const territorySlice = createSlice({
    name: 'territory',
    initialState,
    reducers: {
        setTerritory: (state, action: PayloadAction<number[]>) => {
            state.territory = action.payload;
            console.log(action.payload);
        },
    },
});

export const { setTerritory } = territorySlice.actions;
export default territorySlice.reducer;
export const selectTerritory = (state: RootState) => state.territory;