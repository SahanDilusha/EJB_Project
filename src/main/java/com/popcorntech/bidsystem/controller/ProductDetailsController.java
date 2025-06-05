package com.popcorntech.bidsystem.controller;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.popcorntech.bidsystem.beans.ProductsServiceBean;
import com.popcorntech.bidsystem.entities.Product;
import java.io.IOException;

@WebServlet("/product-details")
public class ProductDetailsController extends HttpServlet {

    @EJB
    ProductsServiceBean productsServiceBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String productIdStr = req.getParameter("id");

        if (productIdStr != null) {
            try {
                Product product = productsServiceBean.getById(Integer.parseInt(productIdStr));

                if (product != null) {
                    req.setAttribute("product", product);
                    RequestDispatcher rd = req.getRequestDispatcher("single_product");
                    rd.forward(req, resp);
                    return;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }




    }

}
