package com.delivery.controller.command.actions;

import com.delivery.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.delivery.controller.command.TextConstants.Routes.TO_CALCULATOR_REDIRECT;

public class CalculateDelivery implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  {

        //todo print the price value

        return TO_CALCULATOR_REDIRECT;
    }
}
