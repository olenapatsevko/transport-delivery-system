package com.delivery.model.service.impl;

import com.delivery.model.db.impl.PlaceDaoImpl;
import com.delivery.model.domain.PlaceDomain;
import com.delivery.model.entity.bill.DeliveryType;
import com.delivery.model.entity.bill.Material;
import com.delivery.model.entity.bill.Size;
import com.delivery.model.entity.bill.Weight;
import com.delivery.model.mapper.PlaceMapper;
import com.delivery.model.service.DeliveryCalculation;




public class DeliveryCalculationImpl implements DeliveryCalculation {
    private final PlaceMapper placeMapper;
    private final PlaceDaoImpl placeDao;

    public DeliveryCalculationImpl(PlaceMapper placeMapper, PlaceDaoImpl placeDao) {
        this.placeMapper = placeMapper;
        this.placeDao = placeDao;
    }

    public float calculateDelivery(PlaceDomain dispatch, PlaceDomain destination, Material material, double length, double width, double height, double weight) {
        return deliveryTypeDefiner(dispatch, destination) * sizeTypeDefiner(length, width, height) * weightTypeDefiner(weight)*material.getCoefficient();

    }

    private float deliveryTypeDefiner(PlaceDomain dispatch, PlaceDomain destination) {
        PlaceDomain dis = placeMapper.mapToDomain(placeDao.findByName(dispatch.getCity()).get());
        PlaceDomain des = placeMapper.mapToDomain(placeDao.findByName(destination.getCity()).get());
        if (des.getCity().equals(dis.getCity())) {
            return DeliveryType.TOWN.getI();
        }
        if (des.getRegion().equals(dis.getRegion())) {
            return DeliveryType.REGION.getI();
        }
        return DeliveryType.COUNTRY.getI();

    }

    private float sizeTypeDefiner(double length, double width, double height) {
        double max = Math.max(length, Math.max(width, height));
        if (max < 0.5) {
            return Size.TINY.getCoefficient();
        }
        if (max >= 0.5 && max <= 0.7) {
            return Size.SMALL.getCoefficient();
        }
        if (max > 0.7 && max < 1.2) {
            return Size.MEDIUM.getCoefficient();
        }
        if (max >= 1.2 && max <= 3.0) {
            return Size.BIG.getCoefficient();
        }
            return Size.HUGE.getCoefficient();
    }

    private float weightTypeDefiner(Double width) {
        if(width<5){
            return Weight.LIGHT.getCoefficient();
        }
        if (width>=5 && width <= 10 ){
            return Weight.MEDIUM.getCoefficient();
        }
        if (width>10 && width< 20){
            return Weight.HEAVY.getCoefficient();
        }

        return Weight.ENORMOUS.getCoefficient();
    }


}
