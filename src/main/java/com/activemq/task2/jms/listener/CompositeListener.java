package com.activemq.task2.jms.listener;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CompositeListener {

    @Value("${composite-queue}")
    private String compositeQueue;

    @Value("${forward-queue}")
    private String forwardQueue;

    @Value("${forward-topic}")
    private String forwardTopic;

    @Value("${virtual-topic}")
    private String virtualTopic;

    @Value("${virtual-topic-consumer1}")
    private String virtualConsumer1;

    @Value("${virtual-topic-consumer2}")
    private String virtualConsumer2;


    @JmsListener(destination = "${composite-queue}", containerFactory = "queueListenerFactory")
    public void receiveMessageFromCompositeQueue(Message message) throws JMSException {

        TextMessage textMessage = (TextMessage) message;
        String messageData = textMessage.getText();
        log.info("Received message: \"" + messageData + "\" from composite queue: " + compositeQueue);
    }

    @JmsListener(destination = "${forward-queue}", containerFactory = "queueListenerFactory")
    public void receiveMessageFromForwardQueue(Message message) throws JMSException {

        TextMessage textMessage = (TextMessage) message;
        String messageData = textMessage.getText();
        log.info("Received message: \"" + messageData + "\" from forward queue: " + forwardQueue);
    }

    @JmsListener(destination = "${forward-topic}", containerFactory = "queueListenerFactory")
    public void receiveMessageFromForwardTopic(Message message) throws JMSException {

        TextMessage textMessage = (TextMessage) message;
        String messageData = textMessage.getText();
        log.info("Received message: \"" + messageData + "\" from forward topic: " + forwardTopic);
    }

    @JmsListener(destination = "${virtual-topic-consumer1}", containerFactory = "queueListenerFactory")
    public void receiveMessageFromVirtualTopic1(Message message) throws JMSException {

        TextMessage textMessage = (TextMessage) message;
        String messageData = textMessage.getText();
        log.info("Received message by consumer1: \"" + messageData + "\" from virtual topic: " + virtualTopic);
    }

    @JmsListener(destination = "virtual-topic-consumer2", containerFactory = "queueListenerFactory")
    public void receiveMessageFromVirtualTopic2(Message message) throws JMSException {

        TextMessage textMessage = (TextMessage) message;
        String messageData = textMessage.getText();
        log.info("Received message by consumer2: \"" + messageData + "\" from virtual topic: " + virtualTopic);
    }
}
