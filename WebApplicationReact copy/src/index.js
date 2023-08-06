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
import UserList from "./recruitment/pages/UserList";
import RecruiterHome from "./recruitment/pages/Home";
import User from "./recruitment/pages/User";
import Topbar from "./recruitment/components/Topbar";
import Sidebar from "./recruitment/components/Sidebar";
import App from "./App";


const { RecoilPersist, updateState } = recoilPersist([], {
  key: "recoil-persist",
  storage: sessionStorage,
});


const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(

  <RecoilRoot initializeState={updateState}>
    <RecoilPersist />
    <React.StrictMode>
      <App></App>
      
    </React.StrictMode>
  </RecoilRoot>,
);
