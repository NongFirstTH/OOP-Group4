import {useAppSelector} from "../store/hooks.ts";
import {selectWebSocket} from "../store/Slices/webSocketSlice.ts";

export default function ChatBox() {
    const webSocketState = useAppSelector(selectWebSocket)
    return (
        <>
                <div className="overflow-y-auto max-h-96">
                    {
                        webSocketState.messages?.map((message, index) => {
                            return <div>{message.sender}</div>
                        })
                    }
                </div>
        </>
    )
}