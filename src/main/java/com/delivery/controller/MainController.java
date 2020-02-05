package com.delivery.controller;

import com.delivery.controller.command.Action;
import com.delivery.controller.command.directions.Home;
import com.delivery.controller.command.directions.LogMe;
import com.delivery.controller.command.profile.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.delivery.controller.command.TextConstants.CommandPaths.*;
import static com.delivery.controller.command.TextConstants.Routes.EMPTY_STRING;
import static com.delivery.controller.command.TextConstants.Routes.REDIRECT;

public class MainController extends HttpServlet {

    private Map<String, Action> actions = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        actions.put(LOG_ME,
                new LogMe());
        actions.put(HOME,
                new Home());
        actions.put("login", new Login());
        actions.put("register", new Register());
        actions.put("logout", new Logout());
        actions.put("deleteProfile", new DeleteProfile());
        actions.put("personalCabinet", new PersonalCabinet());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(APPLICATION_PATH_REGEX, EMPTY_STRING);

        Action command = actions.getOrDefault(path, (req, resp) -> DEFAULT_PATH);
        String page = command.execute(request, response);

        if (page.contains(REDIRECT)) {
            response.sendRedirect(page.replace(REDIRECT, EMPTY_STRING));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
