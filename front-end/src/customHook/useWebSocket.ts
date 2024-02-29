import Stomp from "stompjs";
// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-expect-error
import SockJS from "sockjs-client/dist/sockjs";
import {useAppDispatch, useAppSelector} from "../store/hooks.ts";
import {setIsConnected, appendMessage,setStompClient} from "../store/Slices/webSocketSlice.ts";
import {selectWebSocket} from "../store/Slices/webSocketSlice.ts";

function useWebSocket(){
    const dispatch = useAppDispatch()
    const webSocket = useAppSelector(selectWebSocket)

    function connect(username : string){
        try {
            const socket: WebSocket = new SockJS(`http://localhost:8080/ws`);
            const stompClient: Stomp.Client = Stomp.over(socket);
            // eslint-disable-next-line @typescript-eslint/ban-ts-comment
            // @ts-expect-error
            stompClient.connect({}, () =>onConnected(stompClient,username), onError);
        } catch (e) {
            console.log(e);
        }
    }

    function gameConfig(config : string){
        if (webSocket.stompClient && webSocket.stompClient.connected) {
            const stringWrapper = {
                text: config
            };
            webSocket.stompClient.send("/app/game.new", {}, JSON.stringify(stringWrapper));
        }
    }

    function addPlayer(username : string){
        if (webSocket.stompClient && webSocket.stompClient.connected) {
            const stringWrapper = {
            text: username
        };
            webSocket.stompClient.send("/app/game.addPlayer", {}, JSON.stringify(stringWrapper));
        }
    }

    function devisePlan(player: number, plan: string) {
        if (webSocket.stompClient && webSocket.stompClient.connected) {
            const wrapper = {
                n: player,
                text: plan
            };
            webSocket.stompClient.send("/app/game.devise", {}, JSON.stringify(wrapper));
        }
    }

    function revisePlan(plan: string) {
        if (webSocket.stompClient && webSocket.stompClient.connected) {
            const wrapper = {
                text: plan
            };
            webSocket.stompClient.send("/app/game.revise", {}, JSON.stringify(wrapper));
        }
    }

    function executePlan() {
        if (webSocket.stompClient && webSocket.stompClient.connected) {
            webSocket.stompClient.send("/app/game.execute", {}, JSON.stringify({}));
        }
    }

    function nextTurn() {
        if (webSocket.stompClient && webSocket.stompClient.connected) {
            webSocket.stompClient.send("/app/game.nextTurn", {}, JSON.stringify({}));
        }
    }

    function sendMessage(username : string){
        if (webSocket.stompClient && webSocket.stompClient.connected) {
            const chatMessage = {
                sender: username,
                content: username,
                timestamp: new Date().toLocaleTimeString(),
                type: 'CHAT'
            };
            webSocket.stompClient.send("/app/game.addPlayer", {}, JSON.stringify(chatMessage));
        }
    }

    const onConnected = (stompClient : Stomp.Client, username : string) => {
        stompClient.subscribe('/topic/public', onMessageReceived);
        stompClient.subscribe('/topic/region', onRegionMutate);
        const chatMessage = {
            text: username,
        };
        //stompClient.send("/app/game.new", {}, JSON.stringify(chatMessage));
        dispatch(setIsConnected(true))
        dispatch(setStompClient(stompClient))
        addPlayer(username)
    }
    const onMessageReceived = (payload : Stomp.Message) => {
        dispatch(appendMessage(JSON.parse(payload.body)))
    }
    const onRegionMutate = (payload : Stomp.Message) => {
        console.log(JSON.parse(payload.body))
    }
//     const count = (count : String) => {
//         stompClient.subscribe('/topic/public', onMessageReceived);
//         stompClient.send("/app/chat.getCount", {}, JSON.stringify({count : getCount}));
//         dispatch(setCount(count))
//     }

    return {gameConfig,addPlayer,devisePlan,revisePlan,executePlan,nextTurn,connect,sendMessage}
}

export default useWebSocket;

const onError = (err: Stomp.Message) => {
    console.log(err);
}