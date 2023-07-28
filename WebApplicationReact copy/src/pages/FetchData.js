import axios from "axios";
import { useState } from "react";
import { Container } from "react-bootstrap";

export const FetchData = () => {
  const [password, setPassword] = useState("");
  const [firstname, setFirstname] = useState("");
  const [email, setEmail] = useState("");
  const [lastname, setLastname] = useState("");
  const [mobilenumber, setMobilenumber] = useState("");

  function registeration(event) {
    var data = JSON.stringify({
      email: "email",
      firstName: "firstname",
      lastName: "lastname",
      password: "password",
      mobileNumber: "mobilenumber",
    });

    var config = {
      method: "post",
      url: "http://localhost:8099/api/v1/user/signup",
      headers: {
        "Content-Type": "application/json",
      },
      data: data,
    };

    axios(config)
      .then(function (response) {
        console.log(JSON.stringify(response.data));
      })
      .catch(function (error) {
        console.log(error);
      });
      event.preventDefault();
  }

  return (
    <>
      <Container className="mt-2">
        <div className="mb-2">
          <label type="text" value="firstname">
            First Name:
          </label>
          <input
            type="text"
            value={firstname}
            onChange={(e) => setFirstname(e.target.value)}
          ></input>
        </div>
        <div className="mb-2">
          <label type="text" value="lastname">
            Last Name:
          </label>
          <input
            type="text"
            value={lastname}
            onChange={(e) => setLastname(e.target.value)}
          ></input>
        </div>
        <div className="mb-2">
          <label type="text" value="email">
            Email:
          </label>
          <input
            type="text"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          ></input>
        </div>
        <div className="mb-2">
          <label type="text" value="password">
            Password:
          </label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          ></input>
        </div>
        <div className="mb-2">
          <label type="text" value="mobilenumber">
            Mobile Number:
          </label>
          <input
            type="text"
            value={mobilenumber}
            onChange={(e) => setMobilenumber(e.target.value)}
          ></input>
        </div>
        <button
          className="btn btn-primary"
          type="submit"
          onClick={registeration}
        >
          Register
        </button>
      </Container>
    </>
  );

  // const baseURL = "https://dummyjson.com/users";
  // const [post, setPost] = useState(null);

  // useEffect(() => {
  //     axios.get(baseURL).then((response) => {
  //         setPost(response.data);
  //     });
  // }, []);

  // const eleList = post.map(ele => {
  //     <li className="list-group-item list-group-primary" key={ele.users}>
  //         <ul className="m-3 list-group border-primary">
  //             <li className="list-group-item">firstName: {ele.users.firstName}</li>
  //             <li className="list-group-item">lastName: {ele.users.lastName}</li>
  //             <li className="list-group-item">age: {ele.users.age}</li>
  //             <li className="list-group-item">email: {ele.users.email}</li>
  //             <li className="list-group-item">password: {ele.users.password}</li>
  //         </ul>
  //     </li>
  // })

  // if (!post) return null;
  // return (
  //     <>
  //         <div>
  //             <h1>

  //             </h1>
  //         </div>
  //     </>
  // )
};
