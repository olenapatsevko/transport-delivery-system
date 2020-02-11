package com.delivery.model.mapper;

import com.delivery.model.domain.BillDomain;
import com.delivery.model.entity.Bill;

public class BillMapper implements Mapper<Bill, BillDomain> {


    @Override
    public BillDomain mapToDomain(Bill bill) {
        return BillDomain.builder()
                .withDeliveryType(bill.getDeliveryType())
                .withId(bill.getId())
                .withPayment(bill.isPayment())
                .withOrder(new OrderMapper().mapToDomain(bill.getOrder()))
                .withShipmentMaterial(bill.getMaterial())
                .withSize(bill.getSize())
                .withTotalValue(bill.getTotalValue())
                .withWeight(bill.getWeight())
                .build();
    }

    @Override
    public Bill mapToEntity(BillDomain bill) {
        return Bill.builder()
                .withDeliveryType(bill.getDeliveryType())
                .withId(bill.getId())
                .withPayment(bill.isPayment())
                .withTotalValue(bill.getTotalValue())
                .withOrder(new OrderMapper().mapToEntity(bill.getOrder()))
                .withShipmentMaterial(bill.getMaterial())
                .withSize(bill.getSize())
                .withWeight(bill.getWeight())
                .build();
    }
}
