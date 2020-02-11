package com.delivery.controller.command.actions;

import com.delivery.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.delivery.controller.command.TextConstants.Routes.BASE;

public class PayCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        return BASE;
    }
}
