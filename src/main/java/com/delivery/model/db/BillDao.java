package com.delivery.model.db;


import com.delivery.model.db.CrudPageableDao;
import com.delivery.model.entity.Bill;
import com.delivery.model.entity.User;

import java.util.List;

public interface BillDao extends CrudPageableDao<Bill> {

   List<Bill> findAllBillsForUser(User user);
}
