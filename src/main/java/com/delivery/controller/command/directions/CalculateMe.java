package com.delivery.controller.command.directions;

import com.delivery.controller.command.Command;
import com.delivery.model.utility.DeliveryUtility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.delivery.controller.command.TextConstants.Routes.TO_CALCULATOR_REDIRECT;

public class CalculateMe implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("towns", DeliveryUtility.getListOfTowns());
        request.setAttribute("materials", DeliveryUtility.getListOfMaterials());
        return TO_CALCULATOR_REDIRECT;
    }
}
