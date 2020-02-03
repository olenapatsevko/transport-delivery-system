package com.delivery.servlets;

import com.delivery.dao.DataBaseConnector;
import com.delivery.dao.impl.entity.UserDaoImpl;
import com.delivery.entity.User;
import com.delivery.exeption.LoginException;
import com.delivery.exeption.SqlRuntimeException;
import com.delivery.service.UserService;
import com.delivery.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("profile.jsp").include(request, response);


        String email =request.getParameter("email");
        String password=request.getParameter("password");

        UserService userService = new UserServiceImpl();
        try {
            User user = userService.login(email, password);
            out.println(user.toString());
            HttpSession session=request.getSession();
            session.setAttribute("name",email);

        }catch (Exception e){
            out.println(e);
            request.getRequestDispatcher("registration.jsp").include(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
