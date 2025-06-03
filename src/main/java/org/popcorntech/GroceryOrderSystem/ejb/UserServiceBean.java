package org.popcorntech.GroceryOrderSystem.ejb;

import org.hibernate.Session;
import org.popcorntech.GroceryOrderSystem.entities.User;
import org.popcorntech.GroceryOrderSystem.util.HibernateUtil;

public class UserServiceBean {

    public User registerUser(String email, String password) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            User newUser = new User();

            newUser.setEmail(email);
            session.beginTransaction().commit();

            return newUser;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

}
