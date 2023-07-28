import { Footer } from "../Component/Footer"
import {NavBar} from "../Component/Nav"
import { Registor } from "../Component/Registration"

export const SignUpAndSigninPage = () => {

    const menuItems = [
        { text: "Home", href: "/" },
        { text: "Contact", href: "/contact" },
        { text: "About Us", href: "/aboutus" },
        { text: "Bootcamps", href: "/bootcamps" },
    ]

    return (
        <>
            <NavBar items={menuItems}></NavBar>
            <Registor></Registor>
            <Footer></Footer>
        </>
    )
}