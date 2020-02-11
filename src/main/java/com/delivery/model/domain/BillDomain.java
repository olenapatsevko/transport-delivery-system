package com.delivery.model.domain;

import com.delivery.model.entity.bill.DeliveryType;
import com.delivery.model.entity.bill.Material;
import com.delivery.model.entity.bill.Size;
import com.delivery.model.entity.bill.Weight;

import java.util.Objects;

public class BillDomain {
    private int id;
    private boolean payment;
    private DeliveryType delivery;
    private Size size;
    private Weight weight;
    private Material material;
    private OrderDomain orderDomain;
    private float totalValue;

    public BillDomain(Builder builder) {
        this.id = builder.id;
        this.orderDomain = builder.orderDomain;
        this.payment = builder.payment;
        this.delivery = builder.delivery;
        this.size = builder.size;
        this.weight = builder.weight;
        this.material = builder.material;
        this.totalValue = builder.totalValue;
    }

    public static Builder builder() {

        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillDomain bill = (BillDomain) o;
        return id == bill.id &&
                payment == bill.payment &&
                Objects.equals(orderDomain, bill.orderDomain) &&
                delivery == bill.delivery &&
                size == bill.size &&
                weight == bill.weight &&
                material == bill.material;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDomain, payment, delivery, size, weight, material);
    }

    public int getId() {
        return id;
    }

    public OrderDomain getOrder() {
        return orderDomain;
    }

    public boolean isPayment() {
        return payment;
    }

    public DeliveryType getDelivery() {
        return delivery;
    }

    public Size getSize() {
        return size;
    }

    public Weight getWeight() {
        return weight;
    }

    public float getTotalValue() {
        return totalValue;
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", orderdDomain=" + orderDomain +
                ", payment=" + payment +
                ", deliveryType=" + delivery +
                ", size=" + size +
                ", weight=" + weight +
                ", shipmentMaterial=" + material +
                '}';
    }

    public static class Builder {
        private int id;
        private OrderDomain orderDomain;
        private boolean payment;
        private DeliveryType delivery;
        private Size size;
        private Weight weight;
        private Material material;
        private float totalValue;

        public BillDomain build() {
            return new BillDomain(this);
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withTotalValue(float totalValue) {
            this.totalValue = totalValue;
            return this;
        }

        public Builder withOrder(OrderDomain shipment) {
            this.orderDomain = shipment;
            return this;
        }

        public Builder withPayment(boolean payment) {
            this.payment = payment;
            return this;
        }

        public Builder withDeliveryType(DeliveryType delivery) {
            this.delivery = delivery;
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
