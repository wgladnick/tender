package com.techelevator.services.yelpfusion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.techelevator.services.yelpfusion.models.business.APIWrapper;
import com.techelevator.services.yelpfusion.models.business.Business;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YelpFusion {
    private static final String ACCESS_TOKEN =
            "BSlG5RWR1DJPC4Zf4vabLNFi0YS5-qS2iXkrxpCSY-i02RQWrXuc9CBJtvkeW_EbgUPJ_8oNLscX_yvmu67cQ2HNLxxR6CwFGFoTiUlPsIsDEz-GRtHV88D7dBFBYHYx";



    private RestTemplate restTemplate = new RestTemplate();

    public YelpFusion() {

    }

    public Business[] getBusinessesByAddress(String address, int radius) {
        APIWrapper resultList = null;
        if (radius == 0) {
            radius = 5000;
        }

        String endpointURL = "https://api.yelp.com/v3/businesses/search?categories=restaurants&location=\""
                            + address + "\"&radius=" + radius;

        resultList = restTemplate.exchange(endpointURL, HttpMethod.GET, makeAuthEntity(), APIWrapper.class).getBody();

        return resultList.getBusinesses();

    }

    public Business getBusinessById(String Id) {
        String endpointURL = "https://api.yelp.com/v3/businesses/" + Id;

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
