package com.delivery.model.service.impl;

import com.delivery.model.dao.BillDao;
import com.delivery.model.domain.BillDomain;
import com.delivery.model.mapper.BillMapper;
import com.delivery.model.service.BillService;

import java.util.List;
import java.util.stream.Collectors;

public class BillServiceImpl implements BillService {
    private final BillDao billDao;
    private final BillMapper billMapper;

    public BillServiceImpl(BillDao billDao, BillMapper billMapper) {
        this.billDao = billDao;
        this.billMapper = billMapper;
    }

    @Override
    public List<BillDomain> getListForPage(int page, int perPage , String email){
        return (billDao.findAllBillsForUser(page, perPage ,email).stream().map(billMapper::mapToDomain).collect(Collectors.toList()));
    }

    @Override
    public int getCountOfBills(String email) {
        return billDao.countUserBill(email);
    }

    @Override
    public void payTheBill(int id){
        billDao.paymentChange(id);
    }
}
