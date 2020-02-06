package com.delivery.model.entity;

import java.util.Objects;

public class Place {

    private final int id;
    private final String country;
    private final String region;
    private final String city;


    public Place(Builder builder) {
        this.country = builder.country;
        this.region = builder.region;
        this.city = builder.city;
        this.id = builder.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return id == place.id &&
                Objects.equals(country, place.country) &&
                Objects.equals(region, place.region) &&
                Objects.equals(city, place.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, region, city);
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public static class Builder {
        private String country;
        private String region;
        private String city;
        private int id;

        public Place build() {
            return new Place(this);
        }

        public Builder withCounty(String country) {
            this.country = country;
            return this;
        }

        public Builder withRegion(String region) {
            this.region = region;
            return this;
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

    }
}
