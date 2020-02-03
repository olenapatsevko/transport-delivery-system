package com.delivery.entity.enums.shipment;

public enum Material {
    STANDARD(1.0f),
    GLASS(1.3f);


    float coefficient;

    Material(float coefficient) {

        this.coefficient = coefficient;

    }
}
