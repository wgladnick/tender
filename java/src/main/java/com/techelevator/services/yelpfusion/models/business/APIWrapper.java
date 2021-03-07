package com.techelevator.services.yelpfusion.models.business;

public class APIWrapper {
    private Businesses[] businesses;

    public APIWrapper() {
    }

    public Businesses[] getBusinesses() {
        return businesses;
    }

    public void setBusinesses(Businesses[] businesses) {
        this.businesses = businesses;
    }
}
