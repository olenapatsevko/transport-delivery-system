package com.delivery.controller.command.account;

import com.delivery.controller.command.Command;
import com.delivery.controller.command.CommandUtil;
import com.delivery.model.domain.BillDomain;
import com.delivery.model.entity.Role;
import com.delivery.model.service.BillService;
import com.delivery.model.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.delivery.controller.command.CommandUtil.getCurrentSessionUser;
import static com.delivery.controller.command.TextConstants.Parameters.*;
import static com.delivery.controller.command.TextConstants.Routes.BASE;
import static com.delivery.controller.command.TextConstants.Routes.USER_NOT_EXIST;
import static com.delivery.model.utility.DeliveryUtility.getListOfMaterials;
import static com.delivery.model.utility.DeliveryUtility.getListOfTowns;

public class PersonalCabinet implements Command {
    private final OrderServiceImpl orderService;
    private final BillService billService;

    public PersonalCabinet(OrderServiceImpl orderService, BillService billService) {
        this.orderService = orderService;
        this.billService = billService;
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
            int RECORDS_PER_PAGE = 5;
            int currentPage = 1;
            currentPage = getCurrentPage(request, RECORDS_PER_PAGE, currentPage);
            performPagination(request, currentPage, RECORDS_PER_PAGE);

            return BASE;
        } else {
            return USER_NOT_EXIST;
        }

    }

    private void orderFormParams(HttpServletRequest request) {
        request.removeAttribute("towns");
        request.removeAttribute("materials");
        request.setAttribute("towns", getListOfTowns());
        request.setAttribute("materials", getListOfMaterials());
    }

    private void performPagination(HttpServletRequest request, int currentPage, int recordsPerPage) {

        List<BillDomain> bills = billService.getListForPage(currentPage, recordsPerPage, getCurrentSessionUser(request).getEmail());

        int noOfRecords = billService.getCountOfBills(getCurrentSessionUser(request).getEmail());
        int noOfPages = calcNoOfPages(noOfRecords, recordsPerPage);

        request.setAttribute(BILLS, bills);
        request.setAttribute(NO_OF_PAGES, noOfPages);
        request.setAttribute(CURRENT_PAGE, currentPage);
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

    private int calcLowerBound(int currentPage, int recordsPerPage) {
        return (currentPage - 1) * recordsPerPage;
    }

    private int calcNoOfPages(int noOfRecords, int recordsPerPage) {
        return (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    }

}

