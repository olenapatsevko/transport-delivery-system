package com.delivery.controller.command.account;

import com.delivery.controller.command.Command;
import com.delivery.controller.command.CommandUtil;
import com.delivery.model.entity.Role;
import com.delivery.model.service.BillService;
import com.delivery.model.service.PaginationService;
import com.delivery.model.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.delivery.controller.command.TextConstants.CommandPaths.PER_PAGE;
import static com.delivery.controller.command.TextConstants.Parameters.ROLE;
import static com.delivery.controller.command.TextConstants.Routes.BASE;
import static com.delivery.controller.command.TextConstants.Routes.USER_NOT_EXIST;
import static com.delivery.model.utility.DeliveryUtility.getListOfMaterials;
import static com.delivery.model.utility.DeliveryUtility.getListOfTowns;

public class PersonalCabinet implements Command {
    private final OrderServiceImpl orderService;
    private final BillService billService;
    private final PaginationService paginationService;

    public PersonalCabinet(OrderServiceImpl orderService, BillService billService, PaginationService paginationService) {
        this.orderService = orderService;
        this.billService = billService;
        this.paginationService = paginationService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final HttpSession session = request.getSession();
        final Role role = Role.valueOf((String) session.getAttribute(ROLE));

        orderFormParams(request);
        if (session.getAttribute(ROLE) != Role.GUEST) {
            //to prevent user coming back to cached pages after logout by clicking "back arrow" in browser
            CommandUtil.disallowBackToCached(request, response);
        }

        if (role.equals(Role.ADMIN) || role.equals(Role.USER)) {
            paginationService.performPagination(
                    request,
                    paginationService.calculateCurrentPage(
                            request,
                            PER_PAGE,
                            paginationService.calculateCurrentPage(
                                    request,
                                    PER_PAGE,
                                    1)),
                    PER_PAGE);

            return BASE;
        } else {
            return USER_NOT_EXIST;
        }

    }

    private void orderFormParams(HttpServletRequest request) {
        request.setAttribute("towns", getListOfTowns());
        request.setAttribute("materials", getListOfMaterials());
    }

}

