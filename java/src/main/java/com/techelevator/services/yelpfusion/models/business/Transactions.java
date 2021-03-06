package com.techelevator.services.yelpfusion.models.business;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class Transactions {
    private String type;
    private String label;

    public Transactions(String type) {
        this.type = type;
        this.label = createLabel(this.type);
    }

    private String createLabel(String tag) {
        return Arrays.stream(tag.split("_"))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .reduce("", (label, transaction) -> label += transaction + " ")
                .trim();

    }

}
