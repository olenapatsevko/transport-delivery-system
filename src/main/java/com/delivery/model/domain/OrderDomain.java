package com.delivery.model.domain;

import com.delivery.model.entity.OrderStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public class OrderDomain {
    private int id;
    private UserDomain sender;
    private PlaceDomain destination;
    private OrderStatus orderStatus;
    private PlaceDomain dispatch;
    private String address;
    private ShipmentDomain shipmentDomain;
    private LocalDateTime localDateTime;

    public OrderDomain(Builder builder) {
        this.id = builder.id;
        this.sender = builder.sender;
        this.dispatch = builder.dispatch;
        this.destination = builder.destination;
        this.orderStatus = builder.orderStatus;
        this.address = builder.address;
        this.localDateTime = builder.localDateTime;
        this.shipmentDomain = builder.shipmentDomain;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDomain order = (OrderDomain) o;
        return id == order.id &&
                Objects.equals(sender, order.sender) &&
                Objects.equals(destination, order.destination) &&
                orderStatus == order.orderStatus &&
                Objects.equals(address, order.address) &&
                Objects.equals(dispatch, order.dispatch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sender, destination, orderStatus, address, localDateTime, shipmentDomain);
    }

    public int getId() {
        return id;
    }

    public UserDomain getSender() {
        return sender;
    }

    public PlaceDomain getDestination() {
        return destination;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public String getAddress() {
        return address;
    }

    public PlaceDomain getDispatch() {
        return dispatch;
    }

    public ShipmentDomain getShipmentDomain() {
        return shipmentDomain;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
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
        private UserDomain sender;
        private PlaceDomain destination;
        private PlaceDomain dispatch;
        private OrderStatus orderStatus;
        private String address;
        private LocalDateTime localDateTime;
        private ShipmentDomain shipmentDomain;


        public OrderDomain build() {
            return new OrderDomain(this);
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withShipment(ShipmentDomain shipment) {
            this.shipmentDomain = shipment;
            return this;

        }

        public Builder withLocalDateTime(LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
            return this;
        }

        public Builder withSender(UserDomain sender) {
            this.sender = sender;
            return this;
        }

        public Builder withDestination(PlaceDomain destination) {
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

        public Builder withDispatch(PlaceDomain dispatch) {
            this.dispatch = dispatch;
            return this;
        }
    }
}
