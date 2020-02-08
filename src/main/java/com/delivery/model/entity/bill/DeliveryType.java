package com.delivery.model.entity.bill;

public enum DeliveryType {
    CITY(10f),
    TOWN(10f),
    REGION(30f),
    COUNTRY(90f);

    float i ;

    public float getI() {
        return i;
    }




    DeliveryType(float i) {
    this.i = i;
    }
}
