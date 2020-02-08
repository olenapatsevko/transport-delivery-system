package com.delivery.model.entity.bill;

public enum Weight {
    LIGHT(1.0f),
    MEDIUM(1.2f),
    HEAVY(1.25f),
    ENORMOUS(1.7f);

    float coefficient;

    public float getCoefficient(){
        return coefficient;
    }

    Weight(float v) {
        this.coefficient = v;
    }
}
