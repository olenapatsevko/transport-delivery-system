package com.delivery.model.service.impl;

import com.delivery.model.dao.BillDao;
import com.delivery.model.domain.BillDomain;
import com.delivery.model.entity.Bill;
import com.delivery.model.mapper.Mapper;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BillServiceImplTest {

    @Mock
    private BillDao billDao;
    @Mock
    private Mapper<Bill, BillDomain>  mapper;

    @InjectMocks
    private BillServiceImpl billService;



}