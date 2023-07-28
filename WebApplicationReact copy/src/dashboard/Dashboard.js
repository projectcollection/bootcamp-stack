import { Fragment, useState } from "react";
import Footer from "../molecules/Footer"
import { Profile } from "../Component/Profile"
import LoadingSpinner from "../Component/Spinner";
import TopMenu from "../molecules/TopMenu";
import Sidebar from "../molecules/Sidebar";
import { loggedInUser } from "../atom/globalState";
import { useRecoilState } from "recoil";
import { getCurrentUser } from "../Utils/ApiUtil";
import DashboardOverview from "./TotalGrowthBarChart";


const Dashboard = (props) => {

  const [currentUser, setLoggedInUser] = useRecoilState(loggedInUser);
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);


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



  if (!menuItems) return <div> <LoadingSpinner></LoadingSpinner> </div>
  return (
    <>
      <Fragment>
        <TopMenu></TopMenu>
      </Fragment>
      <Sidebar></Sidebar>
      <div className="mt-5">
        <h1 className="mt-5">DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD</h1>
        <div className="mt-5">
          <DashboardOverview className="mt-5"></DashboardOverview>
        </div>
      </div>
      <Fragment>
        <Footer></Footer>
      </Fragment>
    </>
  )
}
export default Dashboard