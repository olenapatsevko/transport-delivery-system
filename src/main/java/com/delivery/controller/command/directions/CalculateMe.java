package com.delivery.controller.command.directions;

import com.delivery.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.delivery.controller.command.TextConstants.Routes.TO_CALCULATOR_REDIRECT;
import static com.delivery.model.utility.DeliveryUtility.getListOfMaterials;
import static com.delivery.model.utility.DeliveryUtility.getListOfTowns;

public class CalculateMe implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("towns", getListOfTowns());
        request.setAttribute("materials", getListOfMaterials());
        return TO_CALCULATOR_REDIRECT;
    }
}
