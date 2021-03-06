package com.techelevator;

import com.techelevator.services.YelpService;
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
        SpringApplication.run(Application.class, args);

    }

}
