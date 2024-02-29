import { configureStore } from '@reduxjs/toolkit'
import webSocketReducer from './Slices/webSocketSlice'
import configReducer from './Slices/configSlice'
export const store = configureStore({
    reducer: {
        webSocket : webSocketReducer,
        config : configReducer
    },
    middleware: (getDefaultMiddleware) => getDefaultMiddleware({
        serializableCheck: false,
    }),
})

export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch