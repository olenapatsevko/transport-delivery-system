package com.delivery.model.db;


import com.delivery.model.entity.Bill;
import com.delivery.model.entity.User;

import java.util.List;

public interface BillDao extends CrudPageableDao<Bill> {

   List<Bill> findAllBillsForUser(int page, int perPage , String email);
}
