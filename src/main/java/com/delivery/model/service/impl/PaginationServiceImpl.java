package com.delivery.model.service.impl;

import com.delivery.model.domain.BillDomain;
import com.delivery.model.service.BillService;
import com.delivery.model.service.PaginationService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.delivery.controller.command.CommandUtil.getCurrentSessionUser;
import static com.delivery.controller.command.TextConstants.Parameters.*;

public class PaginationServiceImpl implements PaginationService {
    private final BillService billService;

    public PaginationServiceImpl(BillService billService) {
        this.billService = billService;
    }

    @Override
    public int calculateCurrentPage(HttpServletRequest request, int perPage, int currentPage) {
        try {
            if (request.getParameter(CURRENT_PAGE) != null) {
                currentPage = Integer.parseInt(request.getParameter(CURRENT_PAGE));
            }
            if (currentPage < 0 || currentPage > calculateNumberOfPages(billService.getCountOfBills(getCurrentSessionUser(request).getEmail()), perPage)) {

                currentPage = 1;
            }
        } catch (NumberFormatException e) {
            currentPage = 1;
        }
        return currentPage;
    }

    @Override
    public int calculateNumberOfPages(int noOfRecords, int recordsPerPage) {
        return (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    }

    @Override
    public void performPagination(HttpServletRequest request, int currentPage, int recordsPerPage) {

        List<BillDomain> bills = billService.getListForPage(currentPage, recordsPerPage, getCurrentSessionUser(request).getEmail());

        int noOfRecords = billService.getCountOfBills(getCurrentSessionUser(request).getEmail());
        int noOfPages = calculateNumberOfPages(noOfRecords, recordsPerPage);

        request.setAttribute(BILLS, bills);
        request.setAttribute(NO_OF_PAGES, noOfPages);
        request.setAttribute(CURRENT_PAGE, currentPage);
    }
}
