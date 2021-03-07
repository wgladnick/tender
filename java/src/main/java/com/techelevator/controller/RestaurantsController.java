package com.techelevator.controller;

import com.techelevator.services.yelpfusion.YelpFusion;
import com.techelevator.services.yelpfusion.models.business.Businesses;
import com.techelevator.services.yelpfusion.models.business.BusinessDetails;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(value = "/restaurant")
public class RestaurantsController {

    private YelpFusion yelpFusion = new YelpFusion();

    @PreAuthorize("permitAll")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Businesses[] getListRestaurants() {
        String address = "1836 N Lincoln St wilmington de 19806";
        int radius = 500;
        return yelpFusion.getBusinessesByAddress(address, radius);

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BusinessDetails getListRestaurants(@PathVariable String id) {

        return yelpFusion.getBusinessById(id);

    }



}
