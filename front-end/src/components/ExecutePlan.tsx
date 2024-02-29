import {useState} from "react";
import useWebSocket from "../customHook/useWebSocket.ts";
import {useDispatch} from "react-redux";

export default function ExecutePlan() {
    const dispatch = useDispatch()
    const {executePlan} = useWebSocket()
    return (
    <>
        <div className="w-full max-w-xs">
            <form className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4"
                  onSubmitCapture={(e) => {
                e.preventDefault()
                executePlan()
            }}
            >
            <div className="flex items-center justify-center">
                <button
                        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                        type="submit"
                >
                    execute plan
                </button>
            </div>
        </form>
        <p className="text-center text-gray-500 text-xs">
            &copy;2020 Acme Corp. All rights reserved.
        </p>
    </div>
</>
        )
        }