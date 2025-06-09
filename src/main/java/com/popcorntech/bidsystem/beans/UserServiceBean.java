package com.popcorntech.bidsystem.beans;

import jakarta.ejb.Stateless;
import org.hibernate.Session;
import com.popcorntech.bidsystem.entities.User;
import com.popcorntech.bidsystem.util.HibernateUtil;

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
            session.close();
            return newUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
    }

    public User loginUser(String email,String password) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            User user = session.createQuery(
                            "FROM User u WHERE u.email = :email AND u.password= :password", User.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .uniqueResult();

            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
    }

    public User getById(int id) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            User user = session.get(User.class, id);

            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
    }


}
