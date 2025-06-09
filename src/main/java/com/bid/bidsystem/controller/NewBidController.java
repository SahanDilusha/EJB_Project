package com.bid.bidsystem.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.bid.bidsystem.beans.BidServiceBean;
import com.bid.bidsystem.beans.BidStatusServiceBean;
import com.bid.bidsystem.beans.ProductsServiceBean;
import com.bid.bidsystem.beans.UserServiceBean;
import com.bid.bidsystem.entities.Bid;
import com.bid.bidsystem.entities.BidStatus;
import com.bid.bidsystem.entities.Product;
import com.bid.bidsystem.entities.User;
import com.bid.bidsystem.service.AdminBidNotificationService;

import java.io.IOException;
import java.math.BigDecimal;

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

    @Inject
    private AdminBidNotificationService bidService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();

        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("status", false);

        try {

            JsonObject bidJson = gson.fromJson(req.getReader(), JsonObject.class);

//            User user = userServiceBean.getById(26);
            User user = (User) req.getSession().getAttribute("user");

            BidStatus bidStatus = bidStatusServiceBean.getById(3);
            Product product = productsServiceBean.getById(bidJson.get("prodctId").getAsInt());

            Bid bid = bidServiceBean.addBid(user, product, bidStatus, bidJson.get("price").getAsDouble());

            jsonObject.addProperty("status", true);

            bidService.notify("Bid on Item " + product.getName() + " with amount $" + BigDecimal.valueOf(bid.getPrice()).setScale(2));

        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.addProperty("message", "Error registering!" + e.getLocalizedMessage());
        }

        resp.setContentType("application/json");
        resp.getWriter().write(jsonObject.toString());

    }

}
