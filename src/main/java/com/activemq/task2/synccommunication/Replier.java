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

@Slf4j
@Component
public class Replier {

    @Qualifier("queueJmsTemplate")
    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("${sync.request-queue}")
    private String requestQueue;
    @Value("${sync.reply-queue}")
    private String replyQueue;
    private static int counter = 1;

    public void sendMessage(String messageText) {

        jmsTemplate.convertAndSend(replyQueue, messageText);
        log.info("Received reply: " + messageText);
    }

    @JmsListener(destination = "${sync.request-queue}", containerFactory = "queueListenerFactory")
    public void receiveRequest(Message message) throws JMSException {

        TextMessage textMessage = (TextMessage) message;
        String messageData = textMessage.getText();
        log.info("Received message: \"" + messageData + "\" from requestQueue: " + requestQueue + " by replier");
        messageData = messageData.concat("-changed: " + counter++);
        sendMessage(messageData);
    }

}
