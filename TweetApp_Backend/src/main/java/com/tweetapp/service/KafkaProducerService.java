//package com.tweetapp.service;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaProducerService {
//	private static final Logger logger = 
//            LoggerFactory.getLogger(KafkaProducerService.class);
//
//	private static final String TOPIC_NAME = "TweetMessage";
//     
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
// 
//    public void sendMessage(String message) 
//    {
//        logger.info(String.format("Message sent -> %s", message));
//        this.kafkaTemplate.send(TOPIC_NAME, message);
//    }
//
//}
