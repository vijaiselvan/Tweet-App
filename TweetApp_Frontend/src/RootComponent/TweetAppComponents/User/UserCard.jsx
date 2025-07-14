import React, { useState } from "react";
import avatar from "../../../profile-picture.jpg";
import { FcLike, FcComments } from "react-icons/fc";
import { Card } from "react-bootstrap";
import '../common.css';
const UserCard = ({ user }) => {
  return (
    <div>
      <Card id="user-card" style={{ padding: "20px", marginTop: "10px" }}>
        <div key={user.username}>
        <img src={avatar} height="100" width="100" />
          <div className="row">
            <span className="col-12 justify-centre user">{user.username}</span>
          </div>
          <div className="email">
            <span>{user.emailId}</span>
          </div>
          <div className="contact">
            <span>{user.contactNumber}</span>
          </div>
        </div>
        <br />
      </Card>
    </div>
  );
};
export default UserCard;
