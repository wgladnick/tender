package com.techelevator;

import com.techelevator.services.YelpService;
import com.techelevator.services.yelpfusion.YelpFusion;
import com.techelevator.services.yelpservicev3.Yelp;
import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws JSONException {
        /** Will be needed once we have a working yelp service.
         YelpService yelpService = new YelpService();
         yelpService.initializeYelpService();
         **/
        YelpFusion yelpFusion = new YelpFusion();
        SpringApplication.run(Application.class, args);

    }

}
