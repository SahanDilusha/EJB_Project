package com.popcorntech.bidsystem.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.popcorntech.bidsystem.sessions.UserSessionBean;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.popcorntech.bidsystem.beans.UserServiceBean;
import com.popcorntech.bidsystem.entities.User;
import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet("/userLogin")
public class UserLoginController extends HttpServlet {

    @EJB
    private UserServiceBean userServiceBean;

    @EJB
    private UserSessionBean userSessionBean;

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
                String password = data.get("password").isJsonNull()?null:data.get("password").getAsString();
                String email = data.get("email").isJsonNull()?null:data.get("email").getAsString();

               if (email.isEmpty()) {
                    jsonObject.addProperty("message", "Invalid email");
                } else if (email.length() > 100) {
                    jsonObject.addProperty("message", "Invalid email");
                } else if (!Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$").matcher(email).find()) {
                    jsonObject.addProperty("message", "Invalid email");
                } else if (password.isEmpty()) {
                    jsonObject.addProperty("message", "Invalid password");
                } else if (!Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$").matcher(email).find()) {
                    jsonObject.addProperty("message", "Invalid password");
                }else {
                    User user = userServiceBean.loginUser(email,password);
                    if (user != null) {

                            req.getSession().setAttribute("user", user);
                            userSessionBean.setUser(user);
                            jsonObject.addProperty("status", true);
                    }else {
                        jsonObject.addProperty("message", "Invalid email or password!");
                    }
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
