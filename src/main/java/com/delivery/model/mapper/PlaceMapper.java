package com.delivery.model.mapper;

import com.delivery.model.domain.PlaceDomain;
import com.delivery.model.entity.Place;

public class PlaceMapper implements Mapper<Place, PlaceDomain> {



    @Override
    public PlaceDomain mapToDomain(Place placeDomain) {
        return new PlaceDomain(placeDomain.getCountry(), placeDomain.getRegion(), placeDomain.getCity());
    }

    @Override
    public Place mapToEntity(PlaceDomain place) {
        throw new UnsupportedOperationException("This table is restricted to expand");
    }
}
