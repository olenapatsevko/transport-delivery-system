package com.delivery.controller.command.account;

import com.delivery.controller.command.Command;
import com.delivery.controller.command.CommandUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.delivery.controller.command.TextConstants.Routes.REDIRECT;

public class Logout implements Command {

    private static final Logger logger = LogManager.getLogger(Logout.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            {
        CommandUtil.logoutUser(request);
        logger.info("User logged out."  );

        String path = request.getServletContext().getContextPath();
        return REDIRECT + path + "/";
    }
}
