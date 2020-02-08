package com.delivery.model.entity.bill;

public enum Material {
    STANDARD(1.0f),
    GLASS(1.3f);


    float coefficient;

    public float getCoefficient(){
        return coefficient;
    }

    Material(float coefficient) {

        this.coefficient = coefficient;

    }
}
