import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import {RootState} from "../store.ts";
import Stomp from "stompjs";

export enum messageType {
    CHAT= 'CHAT',
    JOIN = 'JOIN',
    LEAVE = 'LEAVE'
}
interface webSocketMessage {
    sender: string;
    content: string;
    timestamp: string;
    type: messageType;
}
interface webSocketState {
    isConnected: boolean;
    stompClient: Stomp.Client | undefined;
    messages: webSocketMessage[] | undefined;
    count: string;
    isHost: boolean;
    isJoin: boolean;
}

const initialState: webSocketState = {
    isConnected: false,
    stompClient: undefined,
    messages: [],
    count: 0,
    isHost: false,
    isJoin: false
};

export const webSocketSlice = createSlice({
    name: 'webSocket',
    initialState,
    reducers: {
        setIsConnected: (state, action : PayloadAction<boolean>) => {
            state.isConnected = action.payload;
        },
        appendMessage: (state, action : PayloadAction<webSocketMessage>) => {
            state.messages?.push(action.payload);
            if (action.payload.type=='JOIN'||action.payload.type=='LEAVE') state.count = action.payload.count;
        },
        setStompClient: (state, action : PayloadAction<Stomp.Client>) => {
            state.stompClient = action.payload;
        },
        setHost: (state) => {
            state.isHost = true;
        },
        setJoin: (state) => {
            state.isJoin = true;
        }
    },
});

export const {setIsConnected, appendMessage,setStompClient,setHost,setJoin} = webSocketSlice.actions;
export default webSocketSlice.reducer;
export const selectWebSocket = (state: RootState) => state.webSocket;