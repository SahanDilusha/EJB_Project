package com.bid.bidsystem.listener;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.*;
import com.bid.bidsystem.websocket.BidUpdateWebSocket;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/BidQueue")
        }
)
public class BidListenerAdmin implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                String bidUpdate = ((TextMessage) message).getText();
                BidUpdateWebSocket.broadcast(bidUpdate);
                System.out.println("Received bid: " + bidUpdate);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
