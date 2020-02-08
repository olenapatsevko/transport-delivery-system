package com.delivery.controller.command.account;

import com.delivery.controller.command.Command;
import com.delivery.model.domain.UserDomain;
import com.delivery.model.entity.Role;
import com.delivery.model.exeption.SqlRuntimeException;
import com.delivery.model.exeption.ValidationException;
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

public class Registration implements Command {
    private static final Logger logger = LogManager.getLogger(Registration.class);

    private UserService userService;


    public Registration(UserService userService ) {
        this.userService = userService;

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

      try {


          userService.register(UserDomain.builder()
                  .withEmail(request.getParameter(EMAIL))
                  .withRole(Role.USER)
                  .withFirstName(request.getParameter(FIRST_NAME))
                  .withSecondName(request.getParameter(LAST_NAME))
                  .withPhone(request.getParameter(PHONE))
                  .withPassword(request.getParameter(PASSWORD))
                  .build());

      }catch (ValidationException | SqlRuntimeException e){
          logger.info("User {} entered invalid data.", request.getParameter(EMAIL));
          return REGISTRATION_FAIL_INVALID_DATA;

      }


        logger.info("User {} successfully registered.",request.getParameter(EMAIL));
        return REGISTRATION_SUCCESS;
    }
}
