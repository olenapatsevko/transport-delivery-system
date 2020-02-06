package com.delivery.controller.command.profile;

import com.delivery.controller.command.Action;
import com.delivery.model.entity.Role;
import com.delivery.model.service.UserService;
import com.delivery.model.service.impl.UserServiceImpl;
import com.delivery.model.service.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.delivery.controller.command.TextConstants.Parameters.*;
import static com.delivery.controller.command.TextConstants.Routes.REGISTRATION_FAIL_INVALID_DATA;
import static com.delivery.controller.command.TextConstants.Routes.REGISTRATION_SUCCESS;

public class Registration implements Action {
    private static final Logger logger = LogManager.getLogger(Registration.class);

    private UserService userService;
    private UserValidator validator;

    public Registration() {
        this.userService = new UserServiceImpl();
        this.validator = new UserValidator();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        final String email = request.getParameter(EMAIL);
        final String password = request.getParameter(PASSWORD);
        final String firstName = request.getParameter(FIRST_NAME);
        final String lastName = request.getParameter(LAST_NAME);

        if (!(validator.validate(email, "email") && validator.validate(password, "password"))) {
            logger.info("User [" + email + "]" + " entered invalid data.");
            return REGISTRATION_FAIL_INVALID_DATA;
        }

        Role userRole = Role.valueOf("USER");
        logger.info("User [" + email + "]" + " successfully registered.");
        return REGISTRATION_SUCCESS;
    }
}
