import React, { Fragment, useEffect, useState } from "react";
import Card from 'react-bootstrap/Card';
import { Button, message } from "antd"; // UI Library -- BootSTrap
import {
  getUsers,
  countNewMessages,
  findChatMessages,
  findChatMessage,
  findBootcamps
} from "../Utils/ApiUtil";
import { useRecoilValue, useRecoilState } from "recoil";  // stateStore -- Simple State 
import {
  loggedInUser,
  chatActiveContact,
  chatMessages,
} from "../atom/globalState";
import ScrollToBottom from "react-scroll-to-bottom";

import TopMenu from "../molecules/TopMenu";
import Sidebar from "../molecules/Sidebar";
import Footer from "../molecules/Footer";
import SidebarAH from "../molecules/AutohideSidebar";
import LoadingSpinner from "../Component/Spinner";
import "./Bootcamps.css"
import { Col, Row } from "react-bootstrap";
const MyBootcamps = (props) => {
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
      <Fragment>
        <TopMenu></TopMenu>
      </Fragment>
      <SidebarAH></SidebarAH>
      <Fragment>

        <div style={{ marginTop: "5px" }}></div>
        <div style={{ marginTop: "5px" }}></div>
        <div style={{ marginTop: "5px" }}></div>
        <div style={{ marginTop: "5px" }}></div>
        <div className="container page">

          {bootcamps ? <h1 style={{ marginTop: "5px" }}>
            <h3>Welcome {currentUser.firstName} you are working in below bootcamps</h3>
            <Row>
              {bootcamps.map(item => {
                return (<Col> <BootcampItem item={item}></BootcampItem></Col>)
              })}
            </Row>

          </h1> : <LoadingSpinner></LoadingSpinner>}
        </div>
      </Fragment>
      <Footer></Footer>
    </>
  );
};

export default MyBootcamps;



function BootcampItem(props) {
  return (
    <Card style={{ width: '18rem' }}>
      <Card.Body>
        <Card.Title>{props.item.name}</Card.Title>
        <Card.Subtitle className="mb-2 text-muted">{props.item.description}</Card.Subtitle>
        <Card.Text>
        <div dangerouslySetInnerHTML={{ __html:  props.item.longHtml }} />
        
        </Card.Text>
        <Card.Link href="#">Get Into Bootcamp</Card.Link>
        
      </Card.Body>
    </Card>
  );
}

