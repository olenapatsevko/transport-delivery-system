package com.delivery.controller.command;

import com.delivery.controller.injector.ApplicationInjector;
import com.delivery.model.entity.Role;
import com.delivery.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.delivery.controller.command.TextConstants.Parameters.*;
import static com.delivery.controller.command.TextConstants.Routes.INVALID_SESSION_ERROR;

public class CommandUtil {



        public static void logUser(HttpServletRequest request, String email, String password, Role role){
            request.getSession().setAttribute(PASSWORD, password);
            request.getSession().setAttribute(EMAIL, email);
            request.getSession().setAttribute(ROLE, role.toString());
        }

        public static void logoutUser(HttpServletRequest request) {

            @SuppressWarnings("unchecked")

            final HttpSession session = request.getSession();
            session.removeAttribute(EMAIL);
            session.removeAttribute(PASSWORD);
            session.removeAttribute(ROLE);
        }

        //to prevent user coming back to cached pages after logout
        public static void disallowBackToCached(HttpServletRequest request, HttpServletResponse response)
                throws IOException {

            final HttpSession session = request.getSession();
            final String path = request.getServletContext().getContextPath();

            //to prevent user coming back to cached pages after logout
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            if (session.getAttribute(EMAIL) == null || session.getAttribute(PASSWORD) == null
                    || session.getAttribute(ROLE) == null) {
                response.sendRedirect(path +  INVALID_SESSION_ERROR);
            }
        }

    public static User getCurrentSessionUser(HttpServletRequest request){

        final HttpSession session = request.getSession();
        String email = session.getAttribute(EMAIL).toString();
        return ApplicationInjector.getUserDao().findByEmail(email).get();
    }





}
