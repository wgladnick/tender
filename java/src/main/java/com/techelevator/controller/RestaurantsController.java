package com.techelevator.controller;

import com.techelevator.services.yelpfusion.YelpFusion;
import com.techelevator.services.yelpfusion.models.business.Business;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/restaurants")
public class RestaurantsController {

    private YelpFusion yelpFusion = new YelpFusion();

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Business[] getListRestaurants(@RequestParam(name = "address") String address, @RequestParam(name = "radius", defaultValue = "0") int radius) {
       return yelpFusion.getBusinessesByAddress("300 pennsylvania ave wilmington de 19806", 5000);

    }



}
