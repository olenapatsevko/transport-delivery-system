package com.delivery.entity;

public class Shipment {

    private final int id;
    private final float weight;
    private final float height;
    private final float width;
    private final float length;
    private final Order order;

    public Shipment(Builder builder) {
        this.id = builder.id;
        this.height = builder.height;
        this.length = builder.length;
        this.order = builder.order;
        this.width = builder.width;
        this.weight = builder.weight;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getId() {
        return id;
    }

    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public float getLength() {
        return length;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "id=" + id +
                ", weight=" + weight +
                ", height=" + height +
                ", width=" + width +
                ", length=" + length +
                ", order=" + order +
                '}';
    }

    public static class Builder {
        private int id;
        private float weight;
        private float height;
        private float width;
        private float length;
        private Order order;

        public Shipment build() {
            return new Shipment(this);
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withWeight(float weight) {
            this.weight = weight;
            return this;
        }

        public Builder withHeight(float height) {
            this.height = height;
            return this;
        }

        public Builder withWidth(float width) {
            this.width = width;
            return this;
        }

        public Builder withLength(float length) {
            this.length = length;
            return this;
        }

        public Builder withOrder(Order order) {
            this.order = order;
            return this;
        }
    }

}
