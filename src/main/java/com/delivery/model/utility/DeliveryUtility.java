package com.delivery.model.utility;

import com.delivery.controller.injector.ApplicationInjector;
import com.delivery.model.entity.Place;
import com.delivery.model.entity.bill.Material;

import java.util.List;
import java.util.stream.Collectors;

public final class DeliveryUtility {

    public static List<String> getListOfTowns() {
        return ApplicationInjector.getPlaceDao().findAll().stream().map(Place::getCity).collect(Collectors.toList());
    }

    public static List<String> getListOfMaterials(){
        return ApplicationInjector.getMaterialDao().findAll().stream().map(Material::toString).collect(Collectors.toList());
    }

}
