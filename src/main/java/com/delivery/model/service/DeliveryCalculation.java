package com.delivery.model.service;

import com.delivery.model.domain.PlaceDomain;
import com.delivery.model.domain.ShipmentDomain;
import com.delivery.model.entity.bill.DeliveryType;
import com.delivery.model.entity.bill.Material;
import com.delivery.model.entity.bill.Size;
import com.delivery.model.entity.bill.Weight;

public interface DeliveryCalculation {

    float calculateDelivery(PlaceDomain dispatch,
                            PlaceDomain destination,
                            Material material,
                            ShipmentDomain shipmentDomain);

     Size sizeTypeDefiner(ShipmentDomain s);

    DeliveryType deliveryTypeDefiner(PlaceDomain dispatch, PlaceDomain destination);
    Weight weightTypeDefiner(Float width);
}
