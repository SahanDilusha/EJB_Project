package org.popcorntech.bidsystem.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
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
import org.popcorntech.bidsystem.service.BidNotificationService;

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
    private BidNotificationService bidService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();

        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("status", false);

        try {

            JsonObject bidJson = gson.fromJson(req.getReader(), JsonObject.class);

            User user = userServiceBean.getById(26);
            BidStatus bidStatus = bidStatusServiceBean.getProductCategory(3);
            Product product = productsServiceBean.getById(bidJson.get("prodctId").getAsInt());

            Bid bid = bidServiceBean.addBid(user, product, bidStatus, bidJson.get("price").getAsDouble());

            jsonObject.addProperty("status", true);

            jsonObject.addProperty("message", bid.getId());

            bidService.notify("Bid on Item " + product.getName() + " with amount $" + BigDecimal.valueOf(bid.getPrice()).setScale(2));

        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.addProperty("message", "Error registering!" + e.getLocalizedMessage());
        }

        resp.setContentType("application/json");
        resp.getWriter().write(jsonObject.toString());

    }

}
