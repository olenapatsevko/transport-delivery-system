package com.delivery.dao.entity;

import com.delivery.dao.entity.core.CrudPageableDao;
import com.delivery.entity.Bill;

import java.util.List;

public interface BillDao extends CrudPageableDao<Bill> {

   List<Bill> findAllBillsForUser();
}
