import { useAppSelector } from "../store/hooks.ts";
import { selectWebSocket } from "../store/Slices/webSocketSlice.ts";

export default function ChatBox() {
    const webSocketState = useAppSelector(selectWebSocket);
    return (
        <>
            <div style={{ color: 'white' }}> message from back-end </div>
            <div className="overflow-y-auto max-h-96">
                {
                    webSocketState.messages?.map((message, index) => {
                        return <div style={{ color: 'white' }}>{message.sender}</div>;
                    })
                }
            </div>
        </>
    );
}