package org.popcorntech.GroceryOrderSystem.beans;

import jakarta.ejb.Stateless;
import org.hibernate.Session;
import org.popcorntech.GroceryOrderSystem.entities.User;
import org.popcorntech.GroceryOrderSystem.util.HibernateUtil;

@Stateless
public class UserServiceBean {

    public User registerUser(String fullName, String email, String password) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setFull_name(fullName);

            session.save(newUser);
            session.beginTransaction().commit();

            return newUser;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

    public User loginUser(String email) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            User user = session.createQuery(
                            "FROM User WHERE email = :email", User.class)
                    .setParameter("email", email)
                    .uniqueResult();

            return user;

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
