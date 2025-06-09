package com.bid.bidsystem.beans;

import jakarta.ejb.Stateless;
import org.hibernate.Session;
import com.bid.bidsystem.entities.BidStatus;
import com.bid.bidsystem.util.HibernateUtil;

@Stateless
public class BidStatusServiceBean {

    public BidStatus registerBidStatus(String name) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            BidStatus bidStatus = new BidStatus();
            bidStatus.setName(name);
            int id = (int) session.save(bidStatus);
            session.beginTransaction().commit();
            bidStatus.setId(id);
            session.close();
            return bidStatus;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public BidStatus getById(int id) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.close();
            return session.get(BidStatus.class, id);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
