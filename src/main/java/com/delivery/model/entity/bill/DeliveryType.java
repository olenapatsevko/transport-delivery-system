package com.delivery.model.entity.bill;

public enum DeliveryType {
    CITY(10),TOWN(10), REGION(30), COUNTRY(90);

    int i ;

    public int getI() {
        return i;
    }




    DeliveryType(int i) {
    this.i = i;
    }
}
