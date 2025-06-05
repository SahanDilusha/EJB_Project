package com.popcorntech.bidsystem.beans;

import com.popcorntech.bidsystem.entities.Bid;
import com.popcorntech.bidsystem.entities.BidStatus;
import com.popcorntech.bidsystem.entities.Product;
import com.popcorntech.bidsystem.entities.User;
import jakarta.ejb.Stateless;
import org.hibernate.Session;
import com.popcorntech.bidsystem.util.HibernateUtil;

@Stateless
public class BidServiceBean {

    public Bid addBid(User user, Product product, BidStatus bidStatus, double price) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            Bid bid = new Bid();
            bid.setUser(user);
            bid.setProduct(product);
            bid.setBidStatus(bidStatus);
            bid.setPrice(price);

            int id = (int) session.save(bid);
            session.beginTransaction().commit();

            bid.setId(id);

            return bid;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }


}
