package org.popcorntech.GroceryOrderSystem.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.popcorntech.GroceryOrderSystem.util.HibernateUtil;

import java.io.IOException;

@WebServlet("/placeBid")
public class BidServlet extends HttpServlet {
    @EJB


    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();


    }
}

