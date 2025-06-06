package com.popcorntech.bidsystem.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.popcorntech.bidsystem.beans.BidStatusServiceBean;
import com.popcorntech.bidsystem.beans.ProductCategoryServiceBean;
import com.popcorntech.bidsystem.beans.ProductsServiceBean;
import com.popcorntech.bidsystem.entities.BidStatus;
import com.popcorntech.bidsystem.entities.ProductCategory;
import java.io.IOException;

@WebServlet("/addProduct")
@MultipartConfig
public class AddProductController extends HttpServlet {

    @EJB
    ProductsServiceBean productsServiceBean;
    @EJB
    ProductCategoryServiceBean productCategoryServiceBean;
    @EJB
    BidStatusServiceBean bidStatusServiceBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();

        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("status", false);

        try {

            String name = req.getParameter("name");
            String description = req.getParameter("description").isEmpty() ? "" : req.getParameter("description");
            int category = req.getParameter("category").isEmpty() ? 0 : Integer.parseInt(req.getParameter("category"));
            double price = req.getParameter("price").isEmpty() ? 0.00 : Double.parseDouble(req.getParameter("price"));
            int quantity = req.getParameter("quantity").isEmpty() ? 0 : Integer.parseInt(req.getParameter("quantity"));

            if (name.isEmpty()){
                jsonObject.addProperty("message", "Name is required");
            } else if (description.isEmpty()) {
                jsonObject.addProperty("message", "Description is required");
            } else if (category <= 0) {
                jsonObject.addProperty("message", "Category is required");
            } else if (price <= 0) {
                jsonObject.addProperty("message", "Price is required");
            } else if (quantity <= 0) {
                jsonObject.addProperty("message", "Quantity is required");
            }else {
                ProductCategory productCategory = productCategoryServiceBean.getProductCategory(category);

                BidStatus bidStatus = bidStatusServiceBean.getById(1);

                productsServiceBean.registerProduct(name,description,price,productCategory,quantity,bidStatus);

                jsonObject.addProperty("status", true);
            }

        }catch (Exception e) {
            e.printStackTrace();
            jsonObject.addProperty("message", "Error registering!"+e.getLocalizedMessage());
        }

        resp.setContentType("application/json");
        resp.getWriter().write(jsonObject.toString());

    }

}
