package com.techelevator.controller;

import com.techelevator.services.yelpfusion.YelpFusion;
import com.techelevator.services.yelpfusion.models.business.APIWrapper;
import com.techelevator.services.yelpfusion.models.business.Business;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(value = "/restaurant")
public class RestaurantsController {

    private YelpFusion yelpFusion = new YelpFusion();

    @PreAuthorize("permitAll")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Business[] getListRestaurants() {
        String address = "1836 N Lincoln St wilmington de 19806";
        int radius = 500;
        return yelpFusion.getBusinessesByAddress(address, radius);

    }

    @PreAuthorize("permitAll")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Business getListRestaurants(@PathVariable String id) {

        return yelpFusion.getBusinessById(id);

    }



}
