package com.delivery.servlets;

import com.delivery.entity.User;
import com.delivery.service.UserService;
import com.delivery.service.impl.UserServiceImpl;
import com.delivery.service.validator.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        UserService userService = new UserServiceImpl();
        User user = User.builder()
                .withEmail(request.getParameter("email"))
                .withPassword(request.getParameter("password"))
                .withFirstName(request.getParameter("name"))
                .withSecondName(request.getParameter("surname"))
                .withPhone(request.getParameter("phone"))
               .withId(1111)
                .build();
        UserValidator userValidator = new UserValidator();
        try{
            userValidator.validate(user);
            userService.register(user);
            request.getRequestDispatcher("login.jsp").include(request, response);

        }catch (Exception e){
            out.println(e);
        }

    }


}
