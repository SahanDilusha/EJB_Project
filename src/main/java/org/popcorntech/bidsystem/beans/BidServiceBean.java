package org.popcorntech.bidsystem.beans;

import jakarta.ejb.Stateless;
import org.hibernate.Session;
import org.popcorntech.bidsystem.entities.*;
import org.popcorntech.bidsystem.util.HibernateUtil;

@Stateless
public class BidServiceBean {

    public Bid addBid(User user, Product product,BidStatus bidStatus,double price) {

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
