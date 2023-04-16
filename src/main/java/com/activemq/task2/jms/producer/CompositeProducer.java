package com.activemq.task2.jms.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CompositeProducer {

    @Autowired
    @Qualifier("queueJmsTemplate")
    private JmsTemplate jmsTemplate;
    @Value("${composite-queue}")
    private String compositeQueue;

    public void sendMessage(String messageText) {

        log.info("Sending message " + messageText + " to queue - " + compositeQueue);
        jmsTemplate.convertAndSend(compositeQueue, messageText);
    }


}
