import HomeIcon from '@mui/icons-material/Home';
import styles from "./sidenav.module.css"
import { NavLink } from "react-router-dom";
import KeyboardDoubleArrowRightIcon from '@mui/icons-material/KeyboardDoubleArrowRight';
import KeyboardDoubleArrowLeftIcon from '@mui/icons-material/KeyboardDoubleArrowLeft';
import {
    loggedInUser,
    loggedInUserBootcamps,
    chatActiveContact,
    chatMessages,
} from "../atom/globalState";
import { useState } from "react";
import { useRecoilValue } from "recoil";

export default function Sidenav(props) {
    const loggedBootcamp = useRecoilValue(loggedInUserBootcamps);
    const [open, setopen] = useState(true)
    const navData = props.navData;
//     let navData = []
// console.log("navData"+navData)
//     if (props.navData) {
//         navData = [{
//             id: 0,
//             icon: <HomeIcon />,
//             text: "Home",
//             link: "/"
//         }
//         ]
//     }

    const toggleOpen = () => {
        setopen(!open)
    }
    return (
        <div className={open ? styles.sidenav : styles.sidenavClosed}>
            <button className={styles.menuBtn} onClick={toggleOpen}>
                {open ? <KeyboardDoubleArrowLeftIcon /> : <KeyboardDoubleArrowRightIcon />}
            </button>
            {navData.map(item => {
                return <NavLink key={item.id} className={styles.sideitem} to={item.link}>
                    {item.icon}
                    <span className={styles.linkText}>{item.text}</span>
                </NavLink>
            })}
        </div>
    )
}
