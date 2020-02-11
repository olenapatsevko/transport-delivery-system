package com.delivery.controller.command.actions;

import com.delivery.controller.command.Command;
import com.delivery.model.domain.BillDomain;
import com.delivery.model.domain.PlaceDomain;
import com.delivery.model.domain.ShipmentDomain;
import com.delivery.model.domain.UserDomain;
import com.delivery.model.entity.User;
import com.delivery.model.entity.bill.Material;
import com.delivery.model.exeption.SqlRuntimeException;
import com.delivery.model.exeption.ValidationException;
import com.delivery.model.service.BillService;
import com.delivery.model.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.delivery.controller.command.CommandUtil.getCurrentSessionUser;
import static com.delivery.controller.command.TextConstants.Parameters.*;
import static com.delivery.controller.command.TextConstants.Routes.BASE_ORDER_FAIL;
import static com.delivery.controller.command.TextConstants.Routes.BASE_ORDER_SUCCESS;
import static com.delivery.model.utility.DeliveryUtility.getListOfMaterials;
import static com.delivery.model.utility.DeliveryUtility.getListOfTowns;
import static java.lang.Float.parseFloat;


public class MakeOrder implements Command {
    private final OrderServiceImpl orderService;
    private final BillService billService;

    public MakeOrder(OrderServiceImpl orderService, BillService billService) {
        this.orderService = orderService;
        this.billService = billService;
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

            int RECORDS_PER_PAGE = 5;
            int currentPage = 1;
            currentPage = getCurrentPage(request, RECORDS_PER_PAGE, currentPage);


            performPagination(request, currentPage, RECORDS_PER_PAGE);

            return BASE_ORDER_FAIL;
        }

        return BASE_ORDER_SUCCESS;


    }

    private int getCurrentPage(HttpServletRequest request, int perpage, int currentPage) {
        try {
            if (request.getParameter(CURRENT_PAGE) != null) {
                currentPage = Integer.parseInt(request.getParameter(CURRENT_PAGE));
            }
            if (currentPage < 0 || currentPage > calcNoOfPages(billService.getCountOfBills(getCurrentSessionUser(request).getEmail()), perpage)) {

                currentPage = 1;
            }
        } catch (NumberFormatException e) {
            currentPage = 1;
        }
        return currentPage;
    }

    private void orderFormParams(HttpServletRequest request) {
        request.removeAttribute("towns");
        request.removeAttribute("materials");
        request.setAttribute("towns", getListOfTowns());
        request.setAttribute("materials", getListOfMaterials());
    }

    private void performPagination(HttpServletRequest request, int currentPage, int recordsPerPage) {

        final User currentSessionUser = getCurrentSessionUser(request);
        final long currentUserId = currentSessionUser.getId();

        int lowerBound = calcLowerBound(currentPage, recordsPerPage);


//        PaginationResult paginationResult =
//                reportService.getReportsByPagination(lowerBound, recordsPerPage, currentUserId);

        List<BillDomain> bills = billService.getListForPage(currentPage, recordsPerPage, getCurrentSessionUser(request).getEmail());

        int noOfRecords = billService.getCountOfBills(getCurrentSessionUser(request).getEmail());
        int noOfPages = calcNoOfPages(noOfRecords, recordsPerPage);

        request.setAttribute(BILLS, bills);
        request.setAttribute(NO_OF_PAGES, noOfPages);
        request.setAttribute(CURRENT_PAGE, currentPage);
    }

    private int calcLowerBound(int currentPage, int recordsPerPage) {
        return (currentPage - 1) * recordsPerPage;
    }

    private int calcNoOfPages(int noOfRecords, int recordsPerPage) {
        return (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    }
}
