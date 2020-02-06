package com.delivery.model.entity;

import java.util.Objects;

public class Order {

    private final int id;
    private final User sender;
    private final Place destination;
    private final OrderStatus orderStatus;
    private final String address;

    public Order(Builder builder) {
        this.id = builder.id;
        this.sender = builder.sender;
        this.destination = builder.destination;
        this.orderStatus = builder.orderStatus;
        this.address = builder.address;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Objects.equals(sender, order.sender) &&
                Objects.equals(destination, order.destination) &&
                orderStatus == order.orderStatus &&
                Objects.equals(address, order.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sender, destination, orderStatus, address);
    }

    public int getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public Place getDestination() {
        return destination;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", sender=" + sender +
                ", destination=" + destination +
                ", orderStatus=" + orderStatus +
                ", address='" + address + '\'' +
                '}';
    }

    public static class Builder {

        private int id;
        private User sender;
        private Place destination;
        private OrderStatus orderStatus;
        private String address;

        public Order build() {
            return new Order(this);
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withSender(User sender) {
            this.sender = sender;
            return this;
        }

        public Builder withDestination(Place destination) {
            this.destination = destination;
            return this;
        }

        public Builder withOrderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }


    }
}
