package com.delivery.model.entity.bill;

public enum DeliveryType {
    CITY(10f),
    TOWN(10f),
    REGION(30f),
    COUNTRY(90f);

    float coefficient ;

    public float getCoefficient() {
        return coefficient;
    }




    DeliveryType(float i) {
    this.coefficient = i;
    }
}
