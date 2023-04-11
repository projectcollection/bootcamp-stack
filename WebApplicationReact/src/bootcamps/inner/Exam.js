
import React, { Fragment, useEffect, useState } from "react";
import Card from 'react-bootstrap/Card';
import { Button, message } from "antd"; // UI Library -- BootSTrap
import {
    getUsers,
    countNewMessages,
    findChatMessages,
    findChatMessage,
    findBootcamps
} from "../../Utils/ApiUtil";
import { useRecoilValue, useRecoilState } from "recoil";  // stateStore -- Simple State 
import {
    loggedInUser,
    loggedInUserBootcamps,
    chatActiveContact,
    chatMessages,
} from "../../atom/globalState";
import ScrollToBottom from "react-scroll-to-bottom";

import TopMenu from "../../molecules/TopMenu";
import Sidebar from "../../molecules/Sidebar";
import Footer from "../../molecules/Footer";
import SidebarAH from "../../molecules/AutohideSidebar";
import LoadingSpinner from "../../Component/Spinner";
import "../Bootcamps.css"
import { Col, Row } from "react-bootstrap";
import { Route, Routes, useParams } from "react-router-dom";
import Sidenav from "../Sidenav";
const Exam = (props) => {


    const BootcampsState = useRecoilValue(loggedInUserBootcamps);
    const id = useParams();
    const currentUser = useRecoilValue(loggedInUser);
    const [bootcamps, setBootcamps] = useState(null)
    useEffect(() => {
        if (localStorage.getItem("accessToken") === null) {
            props.history.push("/");
        }
    }, []);

    useEffect(() => {
        if (currentUser === undefined) return;
        findBootcamps({ id: currentUser.id }).then((bootcamps) =>
            setBootcamps(bootcamps)
        );
    }, []);
    console.log("THE CURRENT USER" + JSON.stringify(currentUser))
    // if (!bootcamps) return <div> <LoadingSpinner></LoadingSpinner> </div>


    return (
        <>

            <div className="container page">

                {bootcamps ? <h1 style={{ marginTop: "5px" }}>
                    <h3>Welcome {currentUser.firstName} you are working in below bootcamps</h3>
                    <Row>
                        
                    </Row>

                </h1> : <LoadingSpinner></LoadingSpinner>}
            </div>
            <Footer></Footer>
        </>
    );
};

export default Exam;

