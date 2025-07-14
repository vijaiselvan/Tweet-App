import React, { useContext, useEffect, useRef, useState } from 'react';
import tweetService from '../../tweetAppService/tweet.service';
import { toast } from 'react-toastify';
import avatar from '../../../profile-picture.jpg';
import UpdateTweet from './UpdateTweet';
import authService from '../../tweetAppService/authService';
const TweetReply = ({ tweet }) => {
    const tweetMessageRef = useRef(null);
    const [tweetReply, setTweetReply] = useState('');
    const [remaining, setRemaining] = useState(144);

    useEffect(() => {
        setRemaining(144 - tweetReply.length);
    }, [tweetReply]);

    const handleFormSubmit = (event) => {
        event.preventDefault();
        if (tweetReply.length === 0) {
            toast('Reply cannot be empty!', {
                icon: '⚠️',
            });
            return;
        }

        tweetService
            .replyTweet(tweet.username, tweet.tweetId, tweetReply)
            .then((res) => {
                console.log(res);
            });
    };

    const handleTweetMessageChange = () => {
        setTweetReply(tweetMessageRef.current.value);
    };

    return (
        <div>
            <form className="row" onSubmit={handleFormSubmit}>
                <div className="col-3">
                    <div className="justify-centre">
                        <img
                            src={avatar}
                            width="35"
                            height="35"
                            alt="Profile avatar"
                        />
                        <span style={{ fontSize: '12px' }} className="fw-bold">
                            {' '}
                            {authService.currentUser()}{' '}
                        </span>
                    </div>
                </div>
                <div className="col-9">
                    <textarea
                        rows="2"
                        name="tweetMessage"
                        ref={tweetMessageRef}
                        value={tweetReply}
                        placeholder="Tweet your reply"
                        onChange={handleTweetMessageChange}
                        maxLength="144"
                    />
                </div>
                <div>
                    <span>{remaining} characters left</span>
                    <button className="btn btn-info btn-sm" type="submit">
                        Reply
                    </button>
                </div>
            </form>
        </div>
    );
};

export default TweetReply;
