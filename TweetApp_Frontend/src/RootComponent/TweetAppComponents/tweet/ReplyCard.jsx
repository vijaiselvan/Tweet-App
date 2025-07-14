import React, { useState } from 'react';
import avatar from '../../../profile-picture.png';
import { Card } from 'react-bootstrap';
import authService from '../../tweetAppService/authService';
import './tweet.css';
const ReplyCard = ({ reply, tweet }) => {
    return (
        <div>
            <Card style={{ padding: '10px', marginRight: '10px' }}>
                <div key={tweet.username} className="row">
                    <div className="col-3">
                        <img
                            src={avatar}
                            width="20"
                            height="20"
                            alt="Profile avatar"
                        />
                        <div>{authService.currentUser()}</div>
                    </div>
                    <div className="col-9">
                        <span className="reply">{reply}</span>
                    </div>
                </div>
            </Card>
        </div>
    );
};
export default ReplyCard;
