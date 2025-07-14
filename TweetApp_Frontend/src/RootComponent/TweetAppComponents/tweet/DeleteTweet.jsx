import React from 'react';
import { FaTrashAlt } from 'react-icons/fa';
import tweetService from '../../tweetAppService/tweet.service';
const DeleteTweet = ({ tweet }) => {
    function handleDeleteTweet(username, tweetId) {
        tweetService.deleteTweet(username, tweetId).then((res) => {
            console.log(res.data);
            window.location.reload(false);
        });
    }
    return (
        <a
            type="submit"
            href="#"
            onClick={() => {
                handleDeleteTweet(tweet.username, tweet.tweetId);
            }}
        >
            <FaTrashAlt size={28} />
        </a>
    );
};
export default DeleteTweet;
