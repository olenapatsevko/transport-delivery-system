package com.delivery.model.entity;

import com.delivery.model.entity.bill.DeliveryType;
import com.delivery.model.entity.bill.Material;
import com.delivery.model.entity.bill.Size;
import com.delivery.model.entity.bill.Weight;

import java.util.Objects;

public class Bill {

    private final int id;
    private final Order order;
    private final boolean payment;
    private final DeliveryType deliveryType;
    private final Size size;
    private final Weight weight;
    private final Material material;
    private final float totalValue;

    public Bill(Builder builder) {
        this.id = builder.id;
        this.order = builder.order;
        this.payment = builder.payment;
        this.deliveryType = builder.deliveryType;
        this.size = builder.size;
        this.weight = builder.weight;
        this.material = builder.material;
        this.totalValue = builder.totalValue;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", order=" + order +
                ", payment=" + payment +
                ", deliveryType=" + deliveryType +
                ", size=" + size +
                ", weight=" + weight +
                ", material=" + material +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return id == bill.id &&
                payment == bill.payment &&
                Objects.equals(order, bill.order) &&
                deliveryType == bill.deliveryType &&
                size == bill.size &&
                weight == bill.weight &&
                material == bill.material;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, payment, deliveryType, size, weight, material);
    }

    public int getId() {
        return id;
    }
    public float getTotalValue(){
        return totalValue;
    }

    public Order getOrder() {
        return order;
    }

    public boolean isPayment() {
        return payment;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public Size getSize() {
        return size;
    }

    public Weight getWeight() {
        return weight;
    }

    public Material getMaterial() {
        return material;
    }

    public static class Builder {
        private int id;
        private Order order;
        private boolean payment;
        private DeliveryType deliveryType;
        private Size size;
        private Weight weight;
        private Material material;
        private float totalValue;

        public Bill build() {
            return new Bill(this);
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withTotalValue(float totalValue){
            this.totalValue = totalValue;
            return this;

        }
        public Builder withOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder withPayment(boolean payment) {
            this.payment = payment;
            return this;
        }

        public Builder withDeliveryType(DeliveryType deliveryType) {
            this.deliveryType = deliveryType;
            return this;
        }

        public Builder withSize(Size size) {
            this.size = size;
            return this;
        }

        public Builder withWeight(Weight weight) {
            this.weight = weight;
            return this;
        }

        public Builder withShipmentMaterial(Material material) {
            this.material = material;
            return this;
        }


    }
}
