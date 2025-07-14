import React, { useEffect, useRef } from "react";
import { useState } from "react";
import {
  Modal,
  Button,
  ModalBody,
  ModalFooter,
  ModalHeader,
  Form,
  FormGroup,
  Label,
  Input,
} from "reactstrap";
import { BiEdit } from "react-icons/bi";
import tweetService from "../../tweetAppService/tweet.service";

const UpdateTweet = ({ tweet }) => {
  const [modal, setModal] = useState(false);
  const [tweetMsg, setTweetMsg] = useState(tweet.tweet);
  const tweetMsgRef = useRef(null);
  const toggle = () => setModal(!modal);
  const [remaining, setRemaining] = useState(144);
  useEffect(() => {
    setRemaining(144 - tweetMsg.length);
  }, [tweetMsg]);

  const updateTweet = () => {
    tweetService
      .updateTweet(tweet.username, tweet.tweetId, tweetMsg)
      .then((res) => {
        console.log(res.data);
        window.location.reload(false);
      });
  };

  return (
    <>
      <BiEdit onClick={toggle} size={28} />
      <Modal isOpen={modal} toggle={toggle}>
        <ModalHeader toggle={toggle}>Edit your tweet</ModalHeader>
        <ModalBody>
          <Form>
            <FormGroup className="row text-center">
              <Label className="col-3 fw-bold" for="tweet">Update your tweet</Label>
              <textarea
              className="col-9"
                id="tweet"
                name="text"
                ref={tweetMsgRef}
                value={tweetMsg}
                maxLength="144"
                required
                onChange={() => setTweetMsg(tweetMsgRef.current.value)}
              />
            </FormGroup>
            <div className="text-center"> {remaining} characters left</div>
            <FormGroup>
              <Label className="fw-bold" for="tag">TweetTag</Label>
              <Input
                id="tag"
                name="tweetTag"
                placeholder={tweet.tweetTag}
                type="text"
                disabled
              />
            </FormGroup>
          </Form>
        </ModalBody>
        <ModalFooter>
          <Button color="primary" onClick={updateTweet}>
            Update
          </Button>
        </ModalFooter>
      </Modal>
    </>
  );
};
export default UpdateTweet;
