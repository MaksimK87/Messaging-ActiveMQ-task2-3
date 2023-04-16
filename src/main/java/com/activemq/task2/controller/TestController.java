package com.activemq.task2.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.activemq.task2.jms.producer.CompositeProducer;
import com.activemq.task2.jms.producer.VirtualTopicProducer;
import com.activemq.task2.synccommunication.Requestor;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final CompositeProducer compositeProducer;
    private final VirtualTopicProducer virtualTopicProducer;
    private final Requestor requestor;


    @PostMapping("/composite-queue")
    public void compositeQueueTest(@RequestParam String message) {
        compositeProducer.sendMessage(message);
    }

    @PostMapping("/virtual-topic")
    public void virtualTopicTest(@RequestParam String message) {
        virtualTopicProducer.sendMessage(message);
    }

    @PostMapping("/requestor")
    public String requestorTest(@RequestParam String message) {
        return requestor.sendMessage(message);
    }

 }
