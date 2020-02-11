package com.delivery.controller.command.actions;

import com.delivery.controller.command.Command;
import com.delivery.model.domain.PlaceDomain;
import com.delivery.model.domain.ShipmentDomain;
import com.delivery.model.entity.bill.Material;
import com.delivery.model.service.DeliveryCalculation;
import com.delivery.model.service.impl.DeliveryCalculationImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.delivery.controller.command.TextConstants.Parameters.*;
import static com.delivery.controller.command.TextConstants.Routes.TO_CALCULATOR_REDIRECT;
import static com.delivery.model.utility.DeliveryUtility.getListOfMaterials;
import static com.delivery.model.utility.DeliveryUtility.getListOfTowns;
import static java.lang.Float.*;

public class CalculateDelivery implements Command {
    private final DeliveryCalculation deliveryCalculationImpl;

    public CalculateDelivery(DeliveryCalculation deliveryCalculationImpl) {
        this.deliveryCalculationImpl = deliveryCalculationImpl;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.removeAttribute("calculationResult");
        request.setAttribute("towns", getListOfTowns());
        request.setAttribute("materials", getListOfMaterials());
        request.setAttribute("calculationResult", Math.round(deliveryCalculationImpl.calculateDelivery(
                new PlaceDomain(request.getParameter(DEPARTURE)),
                new PlaceDomain(request.getParameter(DESTINATION)),
                Material.valueOf(request.getParameter(MATERIAL)),
                ShipmentDomain.builder().withWeight(parseFloat(request.getParameter(WEIGHT)))
                .withLength(parseFloat(request.getParameter(LENGTH)))
                .withHeight(parseFloat(request.getParameter(HEIGHT)))
                .withWidth(parseFloat(request.getParameter(WIDTH)))
                .build()

        )));
       
        return TO_CALCULATOR_REDIRECT;

    }
}
