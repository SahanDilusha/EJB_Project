package com.popcorntech.bidsystem.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.popcorntech.bidsystem.beans.UserServiceBean;


import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet("/register")
public class UserRegisterController extends HttpServlet {

    @EJB
    private UserServiceBean userServiceBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();

        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("status", false);

        try {
            JsonObject data= gson.fromJson(req.getReader(), JsonObject.class);

            if (data == null) {
                jsonObject.addProperty("message", "Invalid data");
            }else {

                String fullName = data.get("fullName").getAsString();
                String password = data.get("password").getAsString();
                String email = data.get("email").getAsString();

                if (fullName.isEmpty()) {
                    jsonObject.addProperty("message", "Invalid full name");
                } else if (fullName.length() > 60) {
                    jsonObject.addProperty("message", "Invalid full name");
                }  else if (email.isEmpty()) {
                    jsonObject.addProperty("message", "Invalid email");
                } else if (email.length() > 100) {
                    jsonObject.addProperty("message", "Invalid email");
                } else if (!Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$").matcher(email).find()) {
                    jsonObject.addProperty("message", "Invalid email");
                } else if (password.isEmpty()) {
                    jsonObject.addProperty("message", "Invalid password");
                } else if (Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,50}$").matcher(password).find()) {
                    jsonObject.addProperty("message", "Invalid password");
                }else {
                    userServiceBean.registerUser(fullName, email, password);
                    jsonObject.addProperty("status", true);
                }

            }

        }catch (Exception e) {
            e.printStackTrace();
            jsonObject.addProperty("message", "Error registering!"+e.getLocalizedMessage());
        }

        resp.setContentType("application/json");
        resp.getWriter().write(jsonObject.toString());

    }
}
