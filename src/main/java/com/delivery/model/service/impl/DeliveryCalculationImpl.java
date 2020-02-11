package com.delivery.model.service.impl;

import com.delivery.model.db.impl.PlaceDaoImpl;
import com.delivery.model.domain.PlaceDomain;
import com.delivery.model.domain.ShipmentDomain;
import com.delivery.model.entity.bill.DeliveryType;
import com.delivery.model.entity.bill.Material;
import com.delivery.model.entity.bill.Size;
import com.delivery.model.entity.bill.Weight;
import com.delivery.model.mapper.PlaceMapper;

public class DeliveryCalculationImpl implements com.delivery.model.service.DeliveryCalculation {
    private final PlaceMapper placeMapper;
    private final PlaceDaoImpl placeDao;

    public DeliveryCalculationImpl(PlaceMapper placeMapper, PlaceDaoImpl placeDao) {
        this.placeMapper = placeMapper;
        this.placeDao = placeDao;
    }

    @Override
    public float calculateDelivery(PlaceDomain dispatch,
                                   PlaceDomain destination,
                                   Material material,
                                   ShipmentDomain shipmentDomain) {
        return deliveryTypeDefiner(dispatch, destination).getCoefficient()
                * sizeTypeDefiner(shipmentDomain).getCoefficient()
                * weightTypeDefiner(shipmentDomain.getWeight()).getCoefficient()
                * material.getCoefficient();

    }

    @Override
    public DeliveryType deliveryTypeDefiner(PlaceDomain dispatch, PlaceDomain destination) {
        PlaceDomain dis = placeMapper.mapToDomain(placeDao.findByName(dispatch.getCity()).get());
        PlaceDomain des = placeMapper.mapToDomain(placeDao.findByName(destination.getCity()).get());
        if (des.getCity().equals(dis.getCity())) {
            return DeliveryType.TOWN;
        }
        if (des.getRegion().equals(dis.getRegion())) {
            return DeliveryType.REGION;
        }
        return DeliveryType.COUNTRY;

    }

    @Override
    public Size sizeTypeDefiner(ShipmentDomain s) {
        double max = Math.max(s.getLength(), Math.max(s.getHeight(), s.getWidth()));
        if (max < 0.5) {
            return Size.TINY;
        }
        if (max >= 0.5 && max <= 0.7) {
            return Size.SMALL;
        }
        if (max > 0.7 && max < 1.2) {
            return Size.MEDIUM;
        }
        if (max >= 1.2 && max <= 3.0) {
            return Size.BIG;
        }
        return Size.HUGE;
    }

    @Override
    public Weight weightTypeDefiner(Float width) {
        if (width < 5) {
            return Weight.LIGHT;
        }
        if (width >= 5 && width <= 10) {
            return Weight.MEDIUM;
        }
        if (width > 10 && width < 20) {
            return Weight.HEAVY;
        }
        return Weight.ENORMOUS;
    }

}
