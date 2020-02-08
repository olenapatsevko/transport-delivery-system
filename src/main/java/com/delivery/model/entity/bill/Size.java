package com.delivery.model.entity.bill;

public enum Size {
    TINY(1.0f),
    SMALL(1.05f) ,
    MEDIUM(1.2f) ,
    BIG(1.3f) ,
    HUGE(1.5f);

    float coefficient;
    Size(float v) {
    this.coefficient = v;
    }

    public float getCoefficient(){
        return this.coefficient;
    }
}
