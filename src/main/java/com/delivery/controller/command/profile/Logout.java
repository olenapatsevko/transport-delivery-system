package com.delivery.controller.command.profile;

import com.delivery.controller.command.Action;
import com.delivery.controller.command.CommandUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.delivery.controller.command.TextConstants.Routes.REDIRECT;

public class Logout implements Action {

    private static final Logger logger = LogManager.getLogger(Logout.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final String email = (String)request.getSession().getAttribute("email");
        CommandUtil.logoutUser(request, email);
        logger.info("User [" + email + "] " + "logged out." );

        String path = request.getServletContext().getContextPath();
        return REDIRECT + path + "/";
    }
}
