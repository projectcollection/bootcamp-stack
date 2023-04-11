import HomeIcon from '@mui/icons-material/Home';
import styles from "./sidenav.module.css"
import React, { Fragment, useEffect, useState } from "react";
import Card from 'react-bootstrap/Card';
import Exam from "./inner/Exam"
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
  loggedInUserBootcamps,
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
import { Route, Routes, useParams } from "react-router-dom";
import Sidenav from "./Sidenav";
const BootcampDetailsC = (props) => {
  const bootcampsState = useRecoilValue(loggedInUserBootcamps);
  const id = useParams();
  const currentUser = useRecoilValue(loggedInUser);
  const [bootcamps, setBootcamps] = useState(null)
 var navData=[]
 
//  {
//     id: 0,
//     icon: <HomeIcon/>,
//     text: "Home",
//     link: "/"
// }
const buildNavs=(data)=>{
  console.log("DATA"+JSON.stringify( data))
  navData.push( {
    id: 3,
    icon: <HomeIcon/>,
    text: "Online Tests",
    link: "/olt-security"
  })
  if(data.sessions!=null){
    navData.push( {
      id: 1,
      icon: <HomeIcon/>,
      text: "Sessions",
      link: "/aaaaa"
  })
  }
  if(data.technologyStack){navData.push( {
    id: 2,
    icon: <HomeIcon/>,
    text: "Tech Stack",
    link: "/dddddddddd"
})}

  if(data.users){navData.push( {
    id: 3,
    icon: <HomeIcon/>,
    text: "Users",
    link: "/cccccccccccccccc"
})}
  
}

  const bootcampSelected = bootcampsState.map((x) => {
    if (x.id === id.id){
      buildNavs(x)
      return x;
    }
    else
      return null;
  })

  return (
    <>
      <TopMenu></TopMenu>
      <Sidenav id={id.id} navData={navData} selectedBootcamp={ bootcampSelected} ></Sidenav>
      <div>
        {
          bootcampsState.map(x => {
            x.id === id ? <></> :
              <></>
          })
        }
      </div>
      <Footer></Footer>
    </>
  );
}

export default BootcampDetailsC;



function BootcampItem(props) {

  return (
    <Card style={{ width: '18rem' }}>
      <Card.Body>
        <Card.Title>{props.item.name}</Card.Title>
        <Card.Subtitle className="mb-2 text-muted">{props.item.description}</Card.Subtitle>
        <Card.Text>
          <div dangerouslySetInnerHTML={{ __html: props.item.longHtml }} />

        </Card.Text>
        <Card.Link href="#">Get Into Bootcamp</Card.Link>

      </Card.Body>
    </Card>
  );
}

