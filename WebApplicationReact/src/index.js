import React from "react";
import ReactDOM from "react-dom/client";
import Chat from "./chat/Chat"
import './index.css';
import recoilPersist from "recoil-persist";
import { RecoilRoot } from "recoil";
import { createBrowserRouter, RouterProvider, Route, Link } from "react-router-dom"
import { Bootcamps } from './pages/Bootcamps';
import { Home } from "./pages/HomePage";
import BootcampDetails from "./pages/BootcampDetail";
import { SignUpAndSigninPage } from "./pages/RegistrationPage";
import { FetchData } from "./pages/FetchData";
import MyBootcamps from "./bootcamps/Bootcamps"
import AuthServices from "./Services/AuthServices";
// import Dashboard from "./pages/Dashboard";
import Dashboard from "./dashboard/Dashboard";

import ProfilePage from "./profile/Profile";
import ResumeUpload from "./resume/Upload";

import Ide from "./ide/Ide";

import TestSpinner from "./Component/Test";


const { RecoilPersist, updateState } = recoilPersist([], {
  key: "recoil-persist",
  storage: sessionStorage,
});
const router = createBrowserRouter([

  {
    path: "/bootcamps/:bootcampId",
    element: (
        <>
         console.log("bootcampId",bootcampId);
          <BootcampDetails ></BootcampDetails>
          <Link to="/bootcamps/:bootcampId"></Link>
        </>
    )
  },

  {
    path: "/datanode",
    element: (
        <>
          <TestSpinner ></TestSpinner>
          <Link to="/datanode"></Link>
        </>
    )
  },


  {
    path: "/",
    element: (
        <>
          <Home></Home>
          <Link to="/"></Link>
        </>
    )
  },

  {
    path: "/ide",
    element: (
        <>
          <Ide></Ide>
          <Link to="/"></Link>
        </>
    )
  },

  {
    path: "/my-bcamps",
    element: (
        <>
          <MyBootcamps></MyBootcamps>
          <Link to="/bootcamps"></Link>
        </>
    )
  },
  {
    path: "/bootcamps",
    element: (
        <>
          <Bootcamps></Bootcamps>
          <Link to="/bootcamps"></Link>
        </>
    )
  }
  ,
  {
    path: "/contactus",
    element: (
        <>
          <Bootcamps></Bootcamps>
          <Link to="/bootcamps"></Link>
        </>
    )
  }
  ,
  {
    path: "/aboutus",
    element: (
        <>
          <Bootcamps></Bootcamps>
          <Link to="/bootcamps"></Link>
        </>
    )
  }
  ,
  {
    path: "/login",
    element: (
        <>
          <SignUpAndSigninPage></SignUpAndSigninPage>
          <Link to="/login"></Link>
        </>
    )
  },

  {

    path: "/chat",
    element: (
        <>
          <Chat ></Chat>
          <Link to="/chat"></Link>
        </>
    )

  }

  // <Route exact path="/chat" render={(props) => <Chat {...props} />} />
,
  {
    path: "/dashboard",
    element: (
        <>
          <Dashboard profile={AuthServices.getCurrentUser()}></Dashboard>
          <Link to="/dashboard"></Link>
        </>
    )
  },
  {
    path: "/profile",
    element: (
        <>
          <ProfilePage profile={AuthServices.getCurrentUser()}></ProfilePage>
          <Link to="/profile"></Link>
        </>
    )
  },
  {
    path: "/resume-upload",
    element: (
        <>
          <ResumeUpload profile={AuthServices.getCurrentUser()}></ResumeUpload>
          <Link to="/resume-upload"></Link>
        </>
    )
  }

])

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(

  <RecoilRoot initializeState={updateState}>
    <RecoilPersist />
    <React.StrictMode>
      <RouterProvider router={router}></RouterProvider>
      {/* <FetchData></FetchData> */}
    </React.StrictMode>
  </RecoilRoot>,
);
