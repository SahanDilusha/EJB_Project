package org.popcorntech.bidsystem.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.popcorntech.bidsystem.beans.BidServiceBean;
import org.popcorntech.bidsystem.beans.BidStatusServiceBean;
import org.popcorntech.bidsystem.beans.ProductsServiceBean;
import org.popcorntech.bidsystem.beans.UserServiceBean;
import org.popcorntech.bidsystem.entities.Bid;
import org.popcorntech.bidsystem.entities.BidStatus;
import org.popcorntech.bidsystem.entities.Product;
import org.popcorntech.bidsystem.entities.User;

import java.io.IOException;

@WebServlet("/addBid")
public class NewBidController extends HttpServlet {

    @EJB
    ProductsServiceBean productsServiceBean;
    @EJB
    BidStatusServiceBean bidStatusServiceBean;
    @EJB
    UserServiceBean userServiceBean;
    @EJB
    BidServiceBean bidServiceBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();

        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("status", false);

        try {

            User user = userServiceBean.getById(26);
            BidStatus bidStatus = bidStatusServiceBean.getProductCategory(3);
            Product product = productsServiceBean.getById(1);

            Bid bid = bidServiceBean.addBid(user, product, bidStatus, 1000.00);

            jsonObject.addProperty("status", true);

            jsonObject.addProperty("message", bid.getId());

        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.addProperty("message", "Error registering!" + e.getLocalizedMessage());
        }

        resp.setContentType("application/json");
        resp.getWriter().write(jsonObject.toString());

    }

}
