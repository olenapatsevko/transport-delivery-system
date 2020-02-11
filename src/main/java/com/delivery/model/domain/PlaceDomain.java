package com.delivery.model.domain;

import java.util.Objects;

public class PlaceDomain {
    private  String country;
    private  String region;
    private  String city;

    public PlaceDomain(String country, String region, String city) {
        this.country = country;
        this.region = region;
        this.city = city;
    }

    public PlaceDomain(String city){
        this.region = "";
        this.country = "";
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaceDomain that = (PlaceDomain) o;
        return Objects.equals(country, that.country) &&
                Objects.equals(region, that.region) &&
                Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, region, city);
    }
}
