import { configureStore } from '@reduxjs/toolkit'
import webSocketReducer from './Slices/webSocketSlice'
import configReducer from './Slices/configSlice'
import territoryReducer from './Slices/territorySlice';
import usernameReducer from './Slices/usernameSlice';

export const store = configureStore({
    reducer: {
        webSocket : webSocketReducer,
        config : configReducer,
        territory: territoryReducer,
        username: usernameReducer,
    },
    middleware: (getDefaultMiddleware) => getDefaultMiddleware({
        serializableCheck: false,
    }),
})

export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch