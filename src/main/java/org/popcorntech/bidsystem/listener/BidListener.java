package org.popcorntech.bidsystem.listener;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.*;
import org.popcorntech.bidsystem.websocket.BidUpdateWebSocket;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/BidQueue")
        }
)
public class BidListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                String bidUpdate = ((TextMessage) message).getText();
                BidUpdateWebSocket.broadcast(bidUpdate);
                System.out.println("JMS Consumer received bid: " + bidUpdate);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
