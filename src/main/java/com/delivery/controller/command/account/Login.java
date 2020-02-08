package com.delivery.controller.command.account;

import com.delivery.controller.command.Command;
import com.delivery.controller.command.CommandUtil;
import com.delivery.model.entity.Role;
import com.delivery.model.entity.User;
import com.delivery.model.exeption.LoginException;
import com.delivery.model.exeption.ValidationException;
import com.delivery.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.delivery.controller.command.TextConstants.Parameters.*;
import static com.delivery.controller.command.TextConstants.Routes.*;

public class Login implements Command {

    private static final Logger LOGGER = LogManager.getLogger(Login.class);
    private final UserService userService ;

    public Login(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final String email = request.getParameter(EMAIL);
        final String password = request.getParameter(PASSWORD);
        User user;
        try {
             user = userService.login(email, password);
        }catch (ValidationException e){
            LOGGER.info("User  {} entered wrong data.",email);
            return LOGIN_FAIL_INVALID_INPUT;
        }catch (LoginException b){
            LOGGER.info("No user with  {} email .",email);
            return USER_NOT_EXIST;
        }catch (Exception e){
            return LOGIN_FAIL_INVALID_INPUT;
        }


        Role role = user.getRole();
        CommandUtil.logUser(request, email, password, role);
        LOGGER.info("User {} role {} signed in successfully.", user.toString(), role.toString());

        String path = request.getServletContext().getContextPath();
        return REDIRECT + path + TO_PERSONAL_CABINET;
    }
}

