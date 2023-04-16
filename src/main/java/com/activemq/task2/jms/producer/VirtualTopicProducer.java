package com.activemq.task2.jms.producer;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VirtualTopicProducer {
    @Autowired
    @Qualifier("queueJmsTemplate")
    private JmsTemplate jmsTemplate;
    @Value("${virtual-topic}")
    private String virtualTopic;

    public void sendMessage(String messageText) {

        log.info("Sending message " + messageText + " to virtual topic - " + virtualTopic);
        jmsTemplate.convertAndSend(new ActiveMQTopic(virtualTopic), messageText);
    }
}
