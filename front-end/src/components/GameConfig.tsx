import {useState} from "react";
import useWebSocket from "../customHook/useWebSocket.ts";
import {useDispatch} from "react-redux";
import {setConfig as sliceSetConfig} from "../store/Slices/configSlice.ts";
import {selectConfig} from "../store/Slices/configSlice.ts";
import {useAppSelector} from "../store/hooks.ts";
import {setJoin} from "../store/Slices/webSocketSlice.ts";

export default function GameConfig() {
    const initialConfig = useAppSelector(selectConfig);
    const [config, setConfig] = useState<string>(initialConfig);
    const dispatch = useDispatch()
    const {gameConfig} = useWebSocket()
    return (
        <>
            <div className="w-full max-w-xs">
                <form className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4"
                    onSubmitCapture={(e) => {
                        e.preventDefault()
                        console.log(config)
                        gameConfig(config)
                        dispatch(setJoin())
                    }}
                    >
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="username">
                            Game Configuration
                        </label>
                        <textarea
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                            id="username" placeholder="Username"
                            value={config}
                            onChange={(e) => setConfig(e.target.value)}
                            required
                                rows={11}
                                style={{ resize: "vertical" }} // Ensures the textarea is resizable vertically
                        />
                    </div>
                    <div className="flex items-center justify-center">
                        <button
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                            type="submit"
                        >
                        Submit
                        </button>
                     </div>
                </form>
            </div>
        </>
    )
}