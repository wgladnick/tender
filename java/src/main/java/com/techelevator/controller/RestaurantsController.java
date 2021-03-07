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

    @RequestMapping(method = RequestMethod.GET)
    public Businesses[] getBusinessesByAddressAndRadius(@RequestParam(defaultValue = "") String address,
                                                        @RequestParam(defaultValue = "5000") int radius) {
        return yelpFusion.getBusinessesByAddressAndRadius(address, radius);

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BusinessDetails getBusinessById(@PathVariable String id) {

        return yelpFusion.getBusinessById(id);

    }


}
