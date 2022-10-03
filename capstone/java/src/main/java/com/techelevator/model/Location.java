package com.techelevator.model;

import java.util.Objects;

public class Location {
    private int locationId;
    private String locationName;
    private String address1;
    private String address2;
    private String city;
    private String stateAbbreviation;
    private String zipCode;
    private double latitude;
    private double longitude;

    public Location() {
    }

    public Location(int locationId, String locationName, String address1, String address2, String city, String stateAbbreviation, String zipCode, double latitude, double longitude) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.stateAbbreviation = stateAbbreviation;
        this.zipCode = zipCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return locationId == location.locationId &&
                Double.compare(location.latitude, latitude) == 0 &&
                Double.compare(location.longitude, longitude) == 0 &&
                Objects.equals(locationName, location.locationName) &&
                Objects.equals(address1, location.address1) &&
                Objects.equals(address2, location.address2) &&
                Objects.equals(city, location.city) &&
                Objects.equals(stateAbbreviation, location.stateAbbreviation) &&
                Objects.equals(zipCode, location.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, locationName, address1, address2, city, stateAbbreviation, zipCode, latitude, longitude);
    }
}
