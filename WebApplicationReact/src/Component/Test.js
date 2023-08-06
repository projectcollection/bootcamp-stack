import { useState } from "react"
import LoadingSpinner from "./Spinner";
import { useEffect } from "react";
import { faL } from "@fortawesome/free-solid-svg-icons";


const TestSpinner = ()=>{
    const [data,setData]= useState();
    const [spinnerState,setSpinnerState] = useState(false)
    useEffect(()=>
    {
        setSpinnerState(true)
        const options = {method: 'GET'};

        fetch('https://jsonplaceholder.typicode.com/posts', options)
          .then(response => response.json())
          .then(response => {
            setData(response)
                console.log(response)
            setSpinnerState(false)
          })
          .catch(err => console.error(err));
    },[])
    return(
        <>
                {
                    spinnerState ?  <LoadingSpinner></LoadingSpinner> : <>Daata was retrived</>
                }
                </>
        )
}

export default TestSpinner