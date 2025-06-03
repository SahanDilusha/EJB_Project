package org.popcorntech.GroceryOrderSystem.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.popcorntech.GroceryOrderSystem.beans.UserServiceBean;
import org.popcorntech.GroceryOrderSystem.entities.User;
import org.popcorntech.GroceryOrderSystem.models.Validation;

import java.io.IOException;

@WebServlet("/userLogin")
public class UserLoginServlet extends HttpServlet {

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

                String password = data.get("password").isJsonNull()?null:data.get("password").getAsString();
                String email = data.get("email").isJsonNull()?null:data.get("email").getAsString();

               if (email.isEmpty()) {
                    jsonObject.addProperty("message", "Invalid email");
                } else if (email.length() > 100) {
                    jsonObject.addProperty("message", "Invalid email");
                } else if (!Validation.getInstance().validateEmail(email)) {
                    jsonObject.addProperty("message", "Invalid email");
                } else if (password.isEmpty()) {
                    jsonObject.addProperty("message", "Invalid password");
                } else if (!Validation.getInstance().validatePassword(password)) {
                    jsonObject.addProperty("message", "Invalid password");
                }else {

                    User user = userServiceBean.loginUser(email);

                    if (user != null) {

                        if (!Validation.getInstance().checkPassword(password, user.getPassword())) {
                            jsonObject.addProperty("message", "Invalid email or password!");
                        }else {

                            req.getSession().setAttribute("user", user);

                            jsonObject.addProperty("status", true);
                        }

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
