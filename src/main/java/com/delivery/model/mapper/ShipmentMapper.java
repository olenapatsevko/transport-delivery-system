package com.delivery.model.mapper;

import com.delivery.model.domain.ShipmentDomain;
import com.delivery.model.entity.Shipment;

public class ShipmentMapper implements Mapper<Shipment, ShipmentDomain>{


    @Override
    public ShipmentDomain mapToDomain(Shipment shipment) {
        return ShipmentDomain.builder()
                .withHeight(shipment.getHeight())
                .withId(shipment.getId())
                .withLength(shipment.getLength())
                .withOrder(shipment.getOrder())
                .withWeight(shipment.getWeight())
                .withWidth(shipment.getWidth())
                .build();
    }

    @Override
    public Shipment mapToEntity(ShipmentDomain shipment) {
        return Shipment.builder()
                .withHeight(shipment.getHeight())
                .withId(shipment.getId())
                .withLength(shipment.getLength())
                .withOrder(shipment.getOrder())
                .withWeight(shipment.getWeight())
                .withWidth(shipment.getWidth())
                .build();
    }
}
