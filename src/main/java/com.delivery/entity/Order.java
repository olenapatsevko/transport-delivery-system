package com.delivery.entity;

import com.delivery.entity.enums.OrderStatus;

public class Order {

    private final int id;
    private final User sender;
    private final Place dispatch;
    private final Place destination;
    private final User receiver;
    private final OrderStatus orderStatus;
    private final String address;

    public Order(Builder builder) {
        this.id = builder.id;
        this.sender = builder.sender;
        this.destination = builder.destination;
        this.dispatch = builder.dispatch;
        this.receiver = builder.receiver;
        this.orderStatus = builder.orderStatus;
        this.address = builder.address;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public Place getDispatch() {
        return dispatch;
    }

    public Place getDestination() {
        return destination;
    }

    public User getReceiver() {
        return receiver;
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
                ", dispatch=" + dispatch +
                ", destination=" + destination +
                ", receiver=" + receiver +
                ", orderStatus=" + orderStatus +
                ", address='" + address + '\'' +
                '}';
    }

    public static class Builder {

        private int id;
        private User sender;
        private Place dispatch;
        private Place destination;
        private User receiver;
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

        public Builder withDispatch(Place dispatch) {
            this.dispatch = dispatch;
            return this;
        }

        public Builder withDestination(Place destination) {
            this.destination = destination;
            return this;
        }

        public Builder withReceiver(User receiver) {
            this.receiver = receiver;
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