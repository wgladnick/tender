package com.techelevator.services.yelpfusion.models.business;

import java.util.Collections;
import java.util.List;

public class Location {
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String state;
    private List<String> display_Address;

    public Location() {
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

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
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

    public List<String> getDisplay_Address() {
        return display_Address;
    }

    public void setDisplay_Address(List<String> display_Address) {
        this.display_Address = Collections.unmodifiableList(display_Address);
    }
}
