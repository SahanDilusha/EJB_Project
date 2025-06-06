package com.popcorntech.bidsystem.beans;

import jakarta.ejb.Stateless;
import org.hibernate.Session;
import com.popcorntech.bidsystem.entities.BidStatus;
import com.popcorntech.bidsystem.util.HibernateUtil;

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
            return bidStatus;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public BidStatus getById(int id) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            return session.get(BidStatus.class, id);

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
