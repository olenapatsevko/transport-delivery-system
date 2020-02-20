package com.delivery.model.service;

import com.delivery.model.domain.BillDomain;
import com.delivery.model.entity.Bill;

import java.util.List;

public interface BillService {
    List<BillDomain> getListForPage(int page, int perPage , String email);
    int getCountOfBills(String email);
    void payTheBill(int id);

}
