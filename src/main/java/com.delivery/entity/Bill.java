package com.delivery.entity;

import com.delivery.entity.enums.DeliveryType;
import com.delivery.entity.enums.shipment.Material;
import com.delivery.entity.enums.shipment.Size;
import com.delivery.entity.enums.shipment.Weight;

public class Bill {

    private final int id;
    private final Shipment shipment;
    private final boolean payment;
    private final DeliveryType deliveryType;
    private final Size size;
    private final Weight weight;
    private final Material material;

    public Bill(Builder builder) {
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

        public Bill build() {
            return new Bill(this);
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
