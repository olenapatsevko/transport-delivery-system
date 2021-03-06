package com.delivery.controller.command.directions;

import com.delivery.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.delivery.controller.command.TextConstants.Routes.TO_LOGIN;

public class LogMe implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  {
        return TO_LOGIN;
    }
}
