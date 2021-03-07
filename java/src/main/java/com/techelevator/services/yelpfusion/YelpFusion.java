package com.techelevator.services.yelpfusion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.techelevator.services.yelpfusion.models.business.BusinessesWrapper;
import com.techelevator.services.yelpfusion.models.business.Businesses;
import com.techelevator.services.yelpfusion.models.business.BusinessDetails;
import com.techelevator.services.yelpfusion.models.review.Review;
import com.techelevator.services.yelpfusion.models.review.ReviewWrapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YelpFusion {
    private static final String ACCESS_TOKEN =
            "BSlG5RWR1DJPC4Zf4vabLNFi0YS5-qS2iXkrxpCSY-i02RQWrXuc9CBJtvkeW_EbgUPJ_8oNLscX_yvmu67cQ2HNLxxR6CwFGFoTiUlPsIsDEz-GRtHV88D7dBFBYHYx";

    private RestTemplate restTemplate = new RestTemplate();

    public YelpFusion() {

    }

    public Businesses[] getBusinessesByLocation(String address, int radius) {
        BusinessesWrapper resultList = null;
        if (radius == 0) {
            radius = 5000;
        }

        String endpointURL = "https://api.yelp.com/v3/businesses/search?categories=restaurants&location=\""
                            + address + "\"&radius=" + radius;

        resultList = restTemplate.exchange(endpointURL, HttpMethod.GET, makeAuthEntity(), BusinessesWrapper.class).getBody();

        return resultList.getBusinesses();

    }

    public BusinessDetails getBusinessById(String Id) {
        String endpointURL = "https://api.yelp.com/v3/businesses/" + Id;

        BusinessDetails business = restTemplate.exchange(endpointURL, HttpMethod.GET, makeAuthEntity(), BusinessDetails.class).getBody();

        return business;

    }

    public Review[] getReviewsByBusinessById(String Id) {
        ReviewWrapper reviews;
        String endpointURL = "https://api.yelp.com/v3/businesses/" + Id +"/reviews";

        reviews = restTemplate.exchange(endpointURL, HttpMethod.GET, makeAuthEntity(), ReviewWrapper.class).getBody();

        return reviews.getReviews();

    }
    
    public Businesses[] getBusinessesByCategory(String address, int radius, String category) {
        BusinessesWrapper resultList = null;
        if (radius == 0) {
            radius = 5000;
        }

        String endpointURL = "https://api.yelp.com/v3/businesses/search?categories=" + category + "&location=\""
                            + address + "\"&radius=" + radius;

        resultList = restTemplate.exchange(endpointURL, HttpMethod.GET, makeAuthEntity(), BusinessesWrapper.class).getBody();

        return resultList.getBusinesses();

    }
    
    

    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(ACCESS_TOKEN);
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }
}
