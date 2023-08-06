import React from "react";
import { useRecoilState } from "recoil";
import { loggedInUser } from "../atom/globalState";
import { LogoutOutlined } from "@ant-design/icons";
import { getCurrentUser } from "../Utils/ApiUtil";
import Footer from "../molecules/Footer";
import { HeadSection } from "../Component/HeadSection";
import { NavBar } from "../Component/Nav";
import 'bootstrap/dist/css/bootstrap.min.css';
import { SignUpSection } from "../Component/signUpSection";
import { ClassTypeSection } from "../Component/ClassTypeSection";
import { SocialMediaSection } from "../Component/SocialMediaSection";
import { FundamentalsDetails } from "../Component/FundamentalsDetails";
import { LearnReactDetails } from "../Component/LearnReactDetails";
import { Question } from "../Component/Question";
import { Instructor } from "../Component/Instructor";
import { ContactInfo } from "../Component/ContactInfo";
import { useState, useEffect } from "react";
import LoadingSpinner from "../Component/Spinner";
import { Modal} from 'react-bootstrap';
import { Registor } from "../Component/Registration";
import '../style/bootcampstyles.css';
import AuthServices from "../Services/AuthServices";
import { redirect, useNavigate } from "react-router-dom";

export const AppContext = React.createContext();
export const Home = (props) => {
   


   ///// End Recoil 

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    const [user, setUser] = useState(JSON.parse(localStorage.getItem("user")));

    console.log(user);

    const menuItems = [
        {text: "Home", href: "/"},
        {text: "Contact", href: "/contact"},
        {text: "About Us", href: "/about"},
        {text: "Bootcamps", href: "/bootcamps"},
    ]

    const items = [
        { header: "React Js", body: "React Js  A common task for a web server can be to open a file on the server and return the content to the client. Here is how PHP or ASP handles a file request: Sends the task to the computer's file system. Waits while the file system opens and reads the file. Returns the content to the client. Ready to handle the next request. Here is how Node.js handles a file request: Sends the task to the computer's file system. Ready to handle the next request.When the file system has opened and read the file, the server returns the content to the client. Node.js eliminates  The waiting, and simply continues with the next request. Node.js runs single-threaded, non-blocking, asynchronous programming, which is very memory efficient." },
        { header: "Java", body: " Java A common task for a web server can be to open a file on the server and return the content to the client. Here is how PHP or ASP handles a file request: Sends the task to the computer's file system. Waits while the file system opens and reads the file. Returns the content to the client. Ready to handle the next request. Here is how Node.js handles a file request: Sends the task to the computer's file system. Ready to handle the next request.When the file system has opened and read the file, the server returns the content to the client. Node.js eliminates  The waiting, and simply continues with the next request. Node.js runs single-threaded, non-blocking, asynchronous programming, which is very memory efficient." },
        { header: "Vanilla Js", body: " Vanilla   A common task for a web server can be to open a file on the server and return the content to the client. Here is how PHP or ASP handles a file request: Sends the task to the computer's file system. Waits while the file system opens and reads the file. Returns the content to the client. Ready to handle the next request. Here is how Node.js handles a file request: Sends the task to the computer's file system. Ready to handle the next request.When the file system has opened and read the file, the server returns the content to the client. Node.js eliminates  The waiting, and simply continues with the next request. Node.js runs single-threaded, non-blocking, asynchronous programming, which is very memory efficient." },
        { header: "Node Js", body: "Nonde Js A common task for a web server can be to open a file on the server and return the content to the client. Here is how PHP or ASP handles a file request: Sends the task to the computer's file system. Waits while the file system opens and reads the file. Returns the content to the client. Ready to handle the next request. Here is how Node.js handles a file request: Sends the task to the computer's file system. Ready to handle the next request.When the file system has opened and read the file, the server returns the content to the client. Node.js eliminates  The waiting, and simply continues with the next request. Node.js runs single-threaded, non-blocking, asynchronous programming, which is very memory efficient." },
        { header: "Express", body: " Express JS Java A common task for a web server can be to open a file on the server and return the content to the client. Here is how PHP or ASP handles a file request: Sends the task to the computer's file system. Waits while the file system opens and reads the file. Returns the content to the client. Ready to handle the next request. Here is how Node.js handles a file request: Sends the task to the computer's file system. Ready to handle the next request.When the file system has opened and read the file, the server returns the content to the client. Node.js eliminates  The waiting, and simply continues with the next request. Node.js runs single-threaded, non-blocking, asynchronous programming, which is very memory efficient." }
    ]

    const itemEvent = (e) => {
        alert(e.target.id + "Was clicked");
    }

    if (!menuItems) return <div> <LoadingSpinner></LoadingSpinner> </div>

    return (
        <>
        <div>
            <NavBar user={user} handleClose={handleClose} handleShow={handleShow} items={menuItems}></NavBar>
            <HeadSection></HeadSection>                
            <SignUpSection></SignUpSection>
            <ClassTypeSection></ClassTypeSection>
            <SocialMediaSection></SocialMediaSection>
            <FundamentalsDetails></FundamentalsDetails>
            <LearnReactDetails></LearnReactDetails>
            <Question items={items} itemEvent={itemEvent}></Question>
            <Instructor></Instructor>
            <ContactInfo></ContactInfo>
            <Footer></Footer>
            </div>

            <Modal
                show={show}
                onHide={handleClose}
                backdrop="static"
                keyboard={false}
            >
                <Modal.Header closeButton>
                </Modal.Header>
                <Modal.Body>
                <Registor handleClose={handleClose}></Registor>
                </Modal.Body>
            </Modal>
        </>
    )
}