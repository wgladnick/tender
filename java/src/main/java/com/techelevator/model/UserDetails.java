package com.techelevator.model;

import java.util.List;

public class UserDetails {
    private String address;
    private String city;
    private String state;
    private int zip;
    private int default_radius;
    private String searchCategories;

    public UserDetails() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public int getDefault_radius() {
        return default_radius;
    }

    public void setDefault_radius(int default_radius) {
        this.default_radius = default_radius;
    }

    public String getSearchCategories() {
        return searchCategories;
    }

    public void setSearchCategories(String searchCategories) {
        this.searchCategories = searchCategories;
    }
}
