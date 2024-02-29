import {useState} from "react";
import useWebSocket from "../customHook/useWebSocket.ts";
import {useDispatch} from "react-redux";
import {setHost, setJoin} from "../store/Slices/webSocketSlice.ts";

export default function EnterUsername() {
    const dispatch = useDispatch()
    return (
    <>
        <div className="w-full max-w-xs">
            <form className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4"
                  onSubmitCapture={(e) => {
                  e.preventDefault()
                  dispatch(setHost())
            }}
            >
            <div className="flex items-center justify-center">
                <button
                        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                        type="submit"
                >
                    Host
                </button>
            </div>
        </form>

        <form className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4"
              onSubmitCapture={(e) => {
              e.preventDefault()
              dispatch(setJoin())
        }}
        >
        <div className="flex items-center justify-center">
            <button
                    className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                    type="submit"
            >
                Join
            </button>
        </div>
    </form>
    </div>
</>
        )
        }