
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";
import RecruiterHome from './recruitment/pages/Home';
import UserList from "./recruitment/pages/UserList";
import User from "./recruitment/pages/User";
import ProductList from "./recruitment/pages/ProductList";
import Product from "./recruitment/pages/Product";
import NewProduct from "./recruitment/pages/NewProduct";
import Topbar from "./recruitment/components/Topbar";
import Sidebar from "./recruitment/components/Sidebar";

function App() {



  return (
    <Router>
    <Topbar />
    <div className="container">
      <Sidebar />
      <Routes>
        <Route path="/" element={<RecruiterHome />} />
        <Route path="/users" element={<UserList />} />
        <Route path="/user/:userId" element={<User />} />
        <Route path="/products" element={<ProductList />} />
        <Route path="/product/:productId" element={<Product />} />
        <Route path="/newproduct" element={<NewProduct />} />
      </Routes>
    </div>
  </Router>
  );
}


export default App;
