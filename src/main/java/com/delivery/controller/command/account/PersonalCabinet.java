package com.delivery.controller.command.account;

import com.delivery.controller.command.Command;
import com.delivery.controller.command.CommandUtil;
import com.delivery.model.entity.Role;
import com.delivery.model.utility.DeliveryUtility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.delivery.controller.command.TextConstants.Parameters.ROLE;
import static com.delivery.controller.command.TextConstants.Routes.BASE;
import static com.delivery.controller.command.TextConstants.Routes.USER_NOT_EXIST;

public class PersonalCabinet implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException{
        final HttpSession session = request.getSession();
        final Role role = Role.valueOf((String) session.getAttribute(ROLE));
        request.setAttribute("towns", DeliveryUtility.getListOfTowns());
        request.setAttribute("materials", DeliveryUtility.getListOfMaterials());
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

