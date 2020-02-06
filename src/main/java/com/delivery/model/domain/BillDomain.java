package com.delivery.model.domain;

import com.delivery.model.entity.Shipment;
import com.delivery.model.entity.bill.DeliveryType;
import com.delivery.model.entity.bill.Material;
import com.delivery.model.entity.bill.Size;
import com.delivery.model.entity.bill.Weight;

import java.util.Objects;

public class BillDomain {
    private int id;
    private Shipment shipment;
    private boolean payment;
    private DeliveryType deliveryType;
    private Size size;
    private Weight weight;
    private Material material;

    public BillDomain(Builder builder) {
        this.id = builder.id;
        this.shipment = builder.shipment;
        this.payment = builder.payment;
        this.deliveryType = builder.deliveryType;
        this.size = builder.size;
        this.weight = builder.weight;
        this.material = builder.material;
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
                Objects.equals(shipment, bill.shipment) &&
                deliveryType == bill.deliveryType &&
                size == bill.size &&
                weight == bill.weight &&
                material == bill.material;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shipment, payment, deliveryType, size, weight, material);
    }

    public int getId() {
        return id;
    }

    public Shipment getShipment() {
        return shipment;
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

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", shipment=" + shipment +
                ", payment=" + payment +
                ", deliveryType=" + deliveryType +
                ", size=" + size +
                ", weight=" + weight +
                ", shipmentMaterial=" + material +
                '}';
    }

    public static class Builder {
        private int id;
        private Shipment shipment;
        private boolean payment;
        private DeliveryType deliveryType;
        private Size size;
        private Weight weight;
        private Material material;

        public BillDomain build() {
            return new BillDomain(this);
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withShipment(Shipment shipment) {
            this.shipment = shipment;
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
