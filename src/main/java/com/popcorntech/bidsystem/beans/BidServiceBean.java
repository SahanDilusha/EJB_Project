package com.popcorntech.bidsystem.beans;

import com.popcorntech.bidsystem.entities.Bid;
import com.popcorntech.bidsystem.entities.BidStatus;
import com.popcorntech.bidsystem.entities.Product;
import com.popcorntech.bidsystem.entities.User;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.hibernate.Session;
import com.popcorntech.bidsystem.util.HibernateUtil;

@Stateless
public class BidServiceBean {

    @EJB
    private BidStatusServiceBean bidStatusServiceBean;

    public Bid addBid(User user, Product product, BidStatus bidStatus, double price) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Bid bid = new Bid();
            bid.setUser(user).setBidStatus(bidStatus).setProduct(product).setPrice(price);
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

    public Bid getById(int bidId) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Bid bid = (Bid) session.get(Bid.class, bidId);
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

    public Bid updateBidStatus(int bidId, int statusId) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Bid bid = (Bid) session.get(Bid.class, bidId);
            if (bid != null) {
                BidStatus bidStatus = bidStatusServiceBean.getById(statusId);
                if (bidStatus != null) {
                    bid.setBidStatus(bidStatus);
                    session.update(bid);
                    session.beginTransaction().commit();
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Bid getMaxBid(int productId) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery(
                            "FROM Bid b WHERE b.product.id = :productId AND b.price = (" +
                                    "SELECT MAX(b2.price) FROM Bid b2 WHERE b2.product.id = :productId)", Bid.class)
                    .setParameter("productId", productId)
                    .uniqueResult();
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
