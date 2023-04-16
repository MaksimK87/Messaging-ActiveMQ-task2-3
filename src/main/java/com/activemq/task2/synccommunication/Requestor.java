package com.activemq.task2.synccommunication;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Requestor {
    @Qualifier("queueJmsTemplate")
    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("${sync.request-queue}")
    private String requestQueue;
    @Value("${sync.reply-queue}")
    private String replyQueue;

    public String sendMessage(String messageText) {

        log.info("Sending request via request queue:" + requestQueue);
        jmsTemplate.convertAndSend(requestQueue, messageText);
        Object replyObj = jmsTemplate.receiveAndConvert(replyQueue);
        log.info("Received reply: " + replyObj);
        return "Received reply: " + replyObj;
    }

}
