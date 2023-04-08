import { Fragment, useEffect, useState } from "react";
import Footer from "../molecules/Footer"
import TopMenu from "../molecules/TopMenu";
import Sidebar from "../molecules/Sidebar";
import { loggedInUser } from "../atom/globalState";
import { useRecoilState } from "recoil";
import { getCurrentUser } from "../Utils/ApiUtil";

import "./Ide"

const Ide = (props) => {
    const [frameHeight , setFrameHeight] = useState()
    const [currentUser, setLoggedInUser] = useRecoilState(loggedInUser);
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    const [user, setUser] = useState(JSON.parse(localStorage.getItem("user")));
    console.log(user);
    const loadCurrentUser = () => {
        getCurrentUser()
          .then((response) => {
            setLoggedInUser(response);
          })
          .catch((error) => {
            console.log(error);
          });
      };

      const logout = () => {
        localStorage.removeItem("accessToken");
        localStorage.removeItem("user");
        props.history.push("/login");
      };
    
      useEffect(() => {

        const frame = document.getElementById('myFrame');
        console.log("height" , frame.contentWindow.document.body.scrollHeight + "px")
               
        setTimeout(() => {
          setFrameHeight(frame.contentWindow.document.body.scrollHeight + "px")
         },100)
       
       
        },[])

    return (
        <div>
            <Fragment>
            <TopMenu></TopMenu>
            </Fragment>
            <Sidebar></Sidebar>
            <div>
              <iframe id="myFrame" style={{marginTop:"88px", marginLeft:"85px"}}
              width="100%"  height="700px"
              frameBorder="0"
              scrolling="no" src="../code-index.html"></iframe>
            </div>
            <Fragment>
            <Footer></Footer>
            </Fragment>
            
        </div>
    )

}


export default Ide