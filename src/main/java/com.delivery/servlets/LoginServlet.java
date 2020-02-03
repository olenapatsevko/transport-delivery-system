package com.delivery.servlets;

import com.delivery.entity.User;
import com.delivery.exeption.LoginException;
import com.delivery.service.impl.UserServiceImpl;
import com.delivery.service.validator.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        request.getRequestDispatcher("profile.jsp").include(request, response);


        String email =request.getParameter("email");
        String password=request.getParameter("password");


        UserServiceImpl userService = new UserServiceImpl();
        try {
            User user = userService.login(email, password);
            HttpSession session=request.getSession();
            session.setAttribute("name",email);

        }catch (LoginException e){
            request.getRequestDispatcher("login.jsp").include(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
