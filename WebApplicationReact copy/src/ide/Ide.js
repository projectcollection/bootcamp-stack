import { Fragment, useEffect, useState } from "react";
import Footer from "../molecules/Footer"
import TopMenu from "../molecules/TopMenu";
import Sidebar from "../molecules/Sidebar";
import Playground from "./Playground";
import { loggedInUser } from "../atom/globalState";
import { useRecoilState } from "recoil";
import { getCurrentUser } from "../Utils/ApiUtil";

import "./Ide"

const Ide = (props) => {
    const product0Api = process.env.REACT_APP_JUDGE0_API_URL;
    const [currentUser, setLoggedInUser] = useRecoilState(loggedInUser);
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    const [user, setUser] = useState(JSON.parse(localStorage.getItem("user")));

    const logout = () => {
        localStorage.removeItem("accessToken");
        localStorage.removeItem("user");
        props.history.push("/login");
    };

    const run = async (sourceCode, langId) => {
        const res = await fetch(`${product0Api}/submissions/?base64_encoded=false&wait=false`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                'X-RapidAPI-Key': `${process.env.REACT_APP_JUDGE0_RAPID_API_KEY}`
            },
            body: JSON.stringify({
                source_code: sourceCode,
                language_id: langId,
                expected_output: "test"
            })
        });

        const data = await res.json();

        console.log(data);

        localStorage.setItem("submissionToken", data.token);
    }

    const save = async (sourceCode, langId) => {
        const token = localStorage.getItem("submissionToken");

        if (token) {
            const res = await fetch(`${product0Api}/submissions/${token}?fields=*`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    'X-RapidAPI-Key': `${process.env.REACT_APP_JUDGE0_RAPID_API_KEY}`
                },
            });

            const data = await res.json();

            //TODO: send data to titus backend
            //{
            // user: userObj,
            // token: string,
            // data: judge0Response
            //}
            console.log({ user: user.user, token, data });
        }
    }

    return (
        <div>
            <Fragment>
                <TopMenu></TopMenu>
            </Fragment>
            <Sidebar></Sidebar>
            <div
                style={{
                    marginTop: "88px",
                    marginLeft: "85px",
                    height: "84vh"
                }}>
                <Playground
                    api={product0Api}
                    onRun={run}
                    onSave={save}
                />
            </div>
            <Fragment>
                <Footer></Footer>
            </Fragment>
        </div>
    )

}


export default Ide
