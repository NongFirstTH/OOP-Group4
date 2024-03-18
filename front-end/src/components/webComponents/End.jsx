import useWebSocket from "../../customHook/useWebSocket.ts";

function End() {
    const { setState } = useWebSocket();

    const onRestart= () => {
        setState('INIT');
    };

    return (
        <div style={{
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            minHeight: '100vh'
        }}>
            <button type="submit" onClick={onRestart}>Restart</button>
        </div>
    );
}

export default End;