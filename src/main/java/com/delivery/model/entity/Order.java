package com.delivery.model.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order {

    private final int id;
    private final User sender;
    private final Place dispatch;
    private final Place destination;
    private final OrderStatus orderStatus;
    private final String address;
    private final Shipment shipment;
    private final LocalDateTime deliveryDate;

    public Order(Builder builder) {
        this.id = builder.id;
        this.sender = builder.sender;
        this.dispatch = builder.dispatch;
        this.destination = builder.destination;
        this.orderStatus = builder.orderStatus;
        this.address = builder.address;
        this.shipment = builder.shipment;
        this.deliveryDate = builder.deliveryDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", sender=" + sender +
                ", dispatch=" + dispatch +
                ", destination=" + destination +
                ", orderStatus=" + orderStatus +
                ", address='" + address + '\'' +
                ", shipment=" + shipment +
                ", deliveryDate=" + deliveryDate +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public Place getDispatch() {
        return dispatch;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public Shipment getShipment() {
        return shipment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Objects.equals(sender, order.sender) &&
                Objects.equals(dispatch, order.dispatch) &&
                Objects.equals(destination, order.destination) &&
                orderStatus == order.orderStatus &&
                Objects.equals(address, order.address) &&
                Objects.equals(shipment, order.shipment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sender, dispatch, destination, orderStatus, address, shipment);
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

    public static class Builder {
        private Shipment shipment;
        private int id;
        private User sender;
        private Place dispatch;
        private Place destination;
        private OrderStatus orderStatus;
        private String address;
        private LocalDateTime deliveryDate;

        public Order build() {
            return new Order(this);
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withDeliveryDate(LocalDateTime deliveryDate) {
            this.deliveryDate = deliveryDate;
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

        public Builder withDispatch(Place dispatch) {
            this.dispatch = dispatch;
            return this;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withShipment(Shipment shipment) {
            this.shipment = shipment;
            return this;
        }


    }
}
