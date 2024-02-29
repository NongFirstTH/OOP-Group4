import {useState} from "react";
import useWebSocket from "../customHook/useWebSocket.ts";
import {useDispatch} from "react-redux";
import {setPlan as sliceSetPlan} from "../store/Slices/planSlice.ts";

export default function RevisePlan() {
    const [plan, setPlan] = useState<string>("")
    const dispatch = useDispatch()
    const {revisePlan} = useWebSocket()
    return (
    <>
        <div className="w-full max-w-xs">
            <form className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4"
                  onSubmitCapture={(e) => {
            e.preventDefault()
            dispatch(sliceSetPlan(plan))
            revisePlan(plan)
            }}
            >
            <div className="mb-4">
                <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="username">
                    plan
                </label>
                <textarea
                        className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        id="username" placeholder="Username"
                        value={plan}
                onChange={(e) => setPlan(e.target.value)}
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
                    submit
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