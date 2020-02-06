package com.delivery.controller.command.profile;

import com.delivery.controller.command.Command;
import com.delivery.controller.command.CommandUtil;
import com.delivery.model.entity.Role;
import com.delivery.model.entity.User;
import com.delivery.model.service.UserService;
import com.delivery.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.delivery.controller.command.TextConstants.Parameters.*;
import static com.delivery.controller.command.TextConstants.Routes.*;

public class Login implements Command {

    private static final Logger logger = LogManager.getLogger(Login.class);
    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final String email = request.getParameter(EMAIL);
        final String password = request.getParameter(PASSWORD);
        User user = userService.login(email, password);
        if (user==null) {
            logger.info("User [" + email + "]" + " entered wrong data.");
            return LOGIN_FAIL_INVALID_INPUT;
        }

        Role role = user.getRole();
        CommandUtil.logUser(request, email, password, role);
        logger.info("User [" + email + "] role [" + role.toString() + "] signed in successfully.");

        String path = request.getServletContext().getContextPath();
        return REDIRECT + path + TO_PERSONAL_CABINET;
    }
}

