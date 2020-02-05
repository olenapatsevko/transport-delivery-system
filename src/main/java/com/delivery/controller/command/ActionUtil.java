package com.delivery.controller.command;

import com.delivery.model.entity.Role;
import com.delivery.model.service.UserService;
import com.delivery.model.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;

import static com.delivery.controller.command.TextConstants.Parameters.*;
import static com.delivery.controller.command.TextConstants.Routes.INVALID_SESSION_ERROR;



public class ActionUtil {

    private static UserService userService;


    static {
        userService = new UserServiceImpl();

    }


    /**
     * This is the executing certain command method
     * which provides the concrete logic for each
     * class that implements it.
     *
     * @param request HttpServletRequest.
     * @param email   String.
     */
    public static boolean checkUserIsLogged(HttpServletRequest request, String email) {

        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession()
                .getServletContext()
                .getAttribute(LOGGED_USERS);


        if (loggedUsers.stream().anyMatch(email::equals)) {
            return true;
        }

        loggedUsers.add(email);
        request.getSession()
                .getServletContext()
                .setAttribute(LOGGED_USERS, loggedUsers);
        return false;
    }


    public static void logUser(HttpServletRequest request, String email, String password, Role role) {
        request.getSession().setAttribute(PASSWORD, password);
        request.getSession().setAttribute(EMAIL, email);
        request.getSession().setAttribute(ROLE, role);
    }

    public static void logoutUser(HttpServletRequest request, String email) {

        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>)
                request.getSession().getServletContext().getAttribute(LOGGED_USERS);

        loggedUsers.remove(email);
        request.getSession().getServletContext().setAttribute(LOGGED_USERS, loggedUsers);

        final HttpSession session = request.getSession();
        session.removeAttribute(EMAIL);
        session.removeAttribute(PASSWORD);
        session.removeAttribute(ROLE);
    }


    /**
     * This method is invoked in commands which need to disallow using cache.
     * It solves the issue when user could logged out and than return
     * to its personal cabinet by clicking "back" button in browser.
     *
     * @param request  HttpServletRequest.
     * @param response HttpServletResponse.
     */
    //to prevent user coming back to cached pages after logout
    public static void disallowBackToCached(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final HttpSession session = request.getSession();
        final String path = request.getServletContext().getContextPath();

        //to prevent user coming back to cached pages after logout
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        if (session.getAttribute(EMAIL) == null || session.getAttribute(PASSWORD) == null
                || session.getAttribute(ROLE) == null) {
            response.sendRedirect(path + INVALID_SESSION_ERROR);
        }
    }


}


