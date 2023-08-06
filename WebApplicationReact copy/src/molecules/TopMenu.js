import React, { useEffect } from 'react';


import { LogoutOutlined } from "@ant-design/icons";
import { getCurrentUser } from "../Utils/ApiUtil";

import { Link, useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCoffee } from '@fortawesome/free-solid-svg-icons'
import { FaSignInAlt, FaFreeCodeCamp, FaHeart, FaRocketchat, FaSignOutAlt } from "react-icons/fa";
import './style.css';
import Image from 'react-bootstrap/Image'
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import image from 'antd/lib/image';
import {
  loggedInUser,
  chatActiveContact,
  chatMessages,
} from "../atom/globalState";

import { useRecoilValue, useRecoilState } from "recoil";  // stateStore -- Simple State 
import { OverlayTrigger, Tooltip } from 'react-bootstrap';

function TopMenu() {

  ///// The Recoil code 
  const navigate = useNavigate();
  const [currentUser, setLoggedInUser] = useRecoilState(loggedInUser);
  useEffect(() => {
    if (localStorage.getItem("accessToken") === null) {
      // props.history.push("/login");
      navigate("/")
    }
    loadCurrentUser();
  }, []);

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
    navigate("/")
  };

  // enb coiler  
  const [messages, setMessages] = useRecoilState(chatMessages);
  const [activeContact, setActiveContact] = useRecoilState(chatActiveContact);

  return (

    <Navbar fixed='top' expand="lg" bg="dark" variant="dark" >
      <Container fluid>
        <Navbar.Brand href="/"><img src='http://snva.com/assets/img/img-logo/snvalogo.svg'></img></Navbar.Brand>
        <Navbar.Toggle aria-controls="navbarScroll" />
        <Navbar.Collapse id="navbarScroll">
          <Nav
            className="me-auto my-2 my-lg-0"
            style={{ maxHeight: '100px' }}
          >
            <h3 style={{ color: "white", textTransform: 'capitalize' }}>Welcome, {currentUser?.firstName} {currentUser?.lastName} !</h3>
          </Nav>

          <div style={{ maxHeight: '100px' }}>
            <Link className="nav-linkss" to="/dashboard">
              <FaSignInAlt style={{ marginRight: "20px", color: 'white', fontSize: '50px' }} title="Coding Bootcamp" />
            </Link>
            <Link className="nav-linkss" to="/ide">
              <FaFreeCodeCamp style={{ marginRight: "20px", color: 'orange', fontSize: '50px' }} title="Coding Bootcamp" />
            </Link>
            <Link className="nav-linkss" to="/chat">
              <FaRocketchat style={{ marginRight: "20px", color: 'green', fontSize: '50px' }} title="Messages" />
            </Link>
            <span> <FaSignOutAlt style={{ marginRight: "20px", color: 'white', fontSize: '50px' }} title="Logout" /></span>

            <Link  to="/profile">
                <Image style={{ borderRadius: "40px" }} height={70} src={currentUser?.profilePicture}></Image>
            </Link>
          </div>

        </Navbar.Collapse>
      </Container>
    </Navbar>

  );
}

export default TopMenu;
