package com.bid.bidsystem.service;

import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import jakarta.jms.TextMessage;

@Singleton
public class AdminBidNotificationService {

    @Resource(lookup = "jms/BidConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/BidQueue")
    private Queue queue;

    public void notify(String message) {
        try{
            JMSContext context = connectionFactory.createContext();
            TextMessage textMessage = context.createTextMessage(message);
            context.createProducer().send(queue, textMessage);
            System.out.println("Message sent");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
