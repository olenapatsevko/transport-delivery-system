package com.delivery.model.dao;


import com.delivery.model.entity.Bill;

import java.util.List;

public interface BillDao extends CrudPageableDao<Bill> {

   List<Bill> findAllBillsForUser(int page, int perPage, String email);

   int countUserBill(String email);
}
