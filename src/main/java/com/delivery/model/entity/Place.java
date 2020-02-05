package com.delivery.model.entity;

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
