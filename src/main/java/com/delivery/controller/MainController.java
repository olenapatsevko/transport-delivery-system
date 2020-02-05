package com.delivery.controller;

import com.delivery.controller.command.Action;
import com.delivery.controller.command.profile.*;
import org.apache.logging.log4j.core.appender.rolling.action.DeleteAction;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainController extends HttpServlet {

    private Map<String, Action> actionMap = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {

        actionMap.put("login", new Login());
        actionMap.put("register", new Register());
        actionMap.put("logout", new Logout());
        actionMap.put("deleteProfile", new DeleteProfile());
        actionMap.put("personalCabinet", new PersonalCabinet());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { processRequest(request, response); }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {processRequest(request, response); }


    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {
    String actionKey = request.getParameter("action");
    Action action = actionMap.get(actionKey);
    String view = action.execute(request, response);
    }
}
