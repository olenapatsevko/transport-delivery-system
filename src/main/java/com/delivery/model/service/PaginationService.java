package com.delivery.model.service;

import javax.servlet.http.HttpServletRequest;

public interface PaginationService {
    int calculateCurrentPage(HttpServletRequest request, int perPage, int currentPage);
    int calculateNumberOfPages(int noOfRecords, int recordsPerPage);
    void performPagination(HttpServletRequest request, int currentPage, int recordsPerPage);



}
