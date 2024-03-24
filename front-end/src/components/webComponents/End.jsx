import useWebSocket from "../../customHook/useWebSocket.ts";
import { selectWebSocket } from "../../store/Slices/webSocketSlice.ts";
import { useAppSelector } from "../../store/hooks.ts";

function End() {
    const { restart } = useWebSocket();
    const webSocketState = useAppSelector(selectWebSocket);
    const onRestart= () => {
        restart();
    };

    return (
        <div className="app-container"
         style={{
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
        }}>
            <img src="../../img/congratulation.png" alt="Game" className="mb-8" />{" "}
            <h1>
                The winner is {webSocketState.winner} !!!!
            </h1>
            <button class="blue-button" type="submit" onClick={onRestart}>Restart</button>
        </div>
    );
}

export default End;