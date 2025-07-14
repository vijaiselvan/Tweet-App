import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { Card } from "reactstrap";
import tweetService from "../../tweetAppService/tweet.service";
import UserCard from "./UserCard";
import NavBar from "../page/NavBar";
import "../common.css";
function SearchUser() {
  const [searchUser, setSearchUser] = useState("");
  const [users, setUsers] = useState([]);

  useEffect(() => {
    if (searchUser !== "") {
      const searchUsers = () => {
        tweetService
          .getUserByUsername(searchUser)
          .then((res) => setUsers(res.data));
      };
      searchUsers();
    } else {
      setUsers([]);
    }
  }, [searchUser]);
  return (
    <div>
      <div>
        <NavBar />
      </div>
      <div>
        <span style={{color:"white"}}>Search Users by username </span>
        <Link to="/tweet">Home</Link>
      </div>
      <br />
      <div style={{ display: "flex", justifyContent: "center" }}>
        <div className="form-outline">
          <input
            type="text"
            id="search"
            className="form-control"
            placeholder="Enter username"
            onChange={(e) => {
              setSearchUser(e.target.value);
            }}
            value={searchUser}
          />
          <label className="form-label" htmlFor="search">
            Search
          </label>
          {/* <button type="button" className="btn btn-primary">
            <i className="fas fa-search"></i>
          </button> */}
        </div>
      </div>

      <div style={{ display: "flex", justifyContent: "center" }}>
        <Card style={{ padding: "20px", marginTop: "1px", width: "350px" }}>
          {users.length > 0 ? (
            <div>Users containing {searchUser}</div>
          ) : (
            <div>There are no user containing {searchUser}</div>
          )}
          {users.length > 0 &&
            users.map((user) => <UserCard user={user} key={user.id} />)}
        </Card>
      </div>
    </div>
  );
}
export default SearchUser;
