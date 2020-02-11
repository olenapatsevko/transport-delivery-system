package com.delivery.controller.command.actions;

import com.delivery.controller.command.Command;
import com.delivery.model.domain.PlaceDomain;
import com.delivery.model.domain.ShipmentDomain;
import com.delivery.model.domain.UserDomain;
import com.delivery.model.entity.bill.Material;
import com.delivery.model.exeption.SqlRuntimeException;
import com.delivery.model.exeption.ValidationException;
import com.delivery.model.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.delivery.controller.command.TextConstants.Parameters.*;
import static com.delivery.controller.command.TextConstants.Routes.*;
import static com.delivery.model.utility.DeliveryUtility.getListOfMaterials;
import static com.delivery.model.utility.DeliveryUtility.getListOfTowns;
import static java.lang.Float.parseFloat;


public class MakeOrder implements Command {
    private final OrderServiceImpl orderService;

    public MakeOrder(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        orderFormParams(request);
        try {
            orderService.makeOrder(new PlaceDomain(request.getParameter(DEPARTURE)),
                    new PlaceDomain(request.getParameter(DESTINATION)),
                    ShipmentDomain.builder()
                            .withHeight(parseFloat(request.getParameter(HEIGHT)))
                            .withLength(parseFloat(request.getParameter(LENGTH)))
                            .withWidth(parseFloat(request.getParameter(WIDTH)))
                            .withWeight(parseFloat(request.getParameter(WEIGHT))).build(),
                    Material.valueOf(request.getParameter(MATERIAL)),
                    UserDomain.builder()
                            .withEmail((String) request.getSession().getAttribute(EMAIL)).build(),
                    request.getParameter("address"));

        } catch (ValidationException | SqlRuntimeException e) {

            return BASE_ORDER_FAIL;
        }

        return BASE_ORDER_SUCCESS;


    }

    private void orderFormParams(HttpServletRequest request) {
        request.removeAttribute("towns");
        request.removeAttribute("materials");
        request.setAttribute("towns", getListOfTowns());
        request.setAttribute("materials", getListOfMaterials());
    }
}
