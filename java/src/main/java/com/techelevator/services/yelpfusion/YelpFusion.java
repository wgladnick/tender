package com.techelevator.services.yelpfusion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.techelevator.services.yelpfusion.models.business.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YelpFusion {
    private static final String ACCESS_TOKEN =
            "BSlG5RWR1DJPC4Zf4vabLNFi0YS5-qS2iXkrxpCSY-i02RQWrXuc9CBJtvkeW_EbgUPJ_8oNLscX_yvmu67cQ2HNLxxR6CwFGFoTiUlPsIsDEz-GRtHV88D7dBFBYHYx";



    private RestTemplate restTemplate = new RestTemplate();

    public YelpFusion() {

    }

    public Business[] getBusinessesByAddress(String address, int radius) {

        Business[] businesses = null;
        if (radius == 0) {
            radius = 5000;
        }

        String endpointURL = "https://api.yelp.com/v3/businesses/search?categories=restaurants&location=\"" + address + "\"&radius=" + radius;

        businesses = restTemplate.exchange(endpointURL, HttpMethod.GET, makeAuthEntity(), Business[].class).getBody();

        return businesses;
    }

    public Business getBusinessById(String Id) {
        String endpointURL = "https://api.yelp.com/v3/businesses/ifEkf8JxP3RCBeszcIGLww";

        Business business = restTemplate.exchange(endpointURL, HttpMethod.GET, makeAuthEntity(), Business.class).getBody();

        return business;

    }



    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(ACCESS_TOKEN);
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }
}
