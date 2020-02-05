package com.delivery.controller.command.directions;


import com.delivery.controller.command.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.delivery.controller.command.TextConstants.Routes.TO_LOGIN;

/**
 * This class is responsible for forwarding
 * to logging page from home page.
 *
 * @author Stanislav Holovachuk
 */

public class LogMe implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return TO_LOGIN;
    }
}
