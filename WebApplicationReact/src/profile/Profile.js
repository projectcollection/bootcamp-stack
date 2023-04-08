import { Fragment, useState } from "react";
import { Modal } from "react-bootstrap"
import HeaderBar from "../Component/DashNav/HeaderBar";
import SideNavBar from "../Component/DashNav/Sidebar";
import Footer from "../molecules/Footer"
import { NavBar } from "../Component/Nav"
import { Profile } from "../Component/Profile"
import { Registor } from "../Component/Registration";
import LoadingSpinner from "../Component/Spinner";
import TopMenu from "../molecules/TopMenu";
import Sidebar from "../molecules/Sidebar";
import { loggedInUser } from "../atom/globalState";
import { useRecoilState } from "recoil";
import { getCurrentUser } from "../Utils/ApiUtil";


const ProfilePage = (props) => {

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
    

    const menuItems = [
        { text: "Home", href: "/" },
        { text: "Contact", href: "/contact" },
        { text: "About Us", href: "/about" },
        { text: "Bootcamps", href: "/bootcamps" },
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
            <Fragment>
            <TopMenu></TopMenu>
            </Fragment>
            <Sidebar></Sidebar>
            <Profile className="profileBody"></Profile>
            <Fragment>
            <Footer></Footer>
            </Fragment>
            
        </>
    )

}


export default ProfilePage