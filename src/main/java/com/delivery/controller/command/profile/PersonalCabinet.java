package com.delivery.controller.command.profile;

import com.delivery.controller.command.Action;
import com.delivery.controller.command.CommandUtil;
import com.delivery.model.entity.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.delivery.controller.command.TextConstants.Parameters.ROLE;
import static com.delivery.controller.command.TextConstants.Routes.BASE;
import static com.delivery.controller.command.TextConstants.Routes.USER_NOT_EXIST;

public class PersonalCabinet implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final HttpSession session = request.getSession();
        final Role role = (Role) session.getAttribute(ROLE);

        if (session.getAttribute(ROLE) != Role.GUEST) {
            //to prevent user coming back to cached pages after logout by clicking "back arrow" in browser
            CommandUtil.disallowBackToCached(request, response);
        }

        if (role.equals(Role.ADMIN) || role.equals(Role.USER)) {
            return BASE;
        } else {
            return USER_NOT_EXIST;
        }

    }

}

