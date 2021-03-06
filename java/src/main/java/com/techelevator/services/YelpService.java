package com.techelevator.services;

import com.techelevator.services.yelpservicev3.Yelp;
import com.techelevator.services.yelpservicev3.client.AccessToken;
import com.techelevator.services.yelpservicev3.client.Credentials;
import org.json.JSONException;

public class YelpService  {
    private String clientId = "cFwCouq0_Y-ZJJUhAfW8jg";
    private String clientSecret = "BSlG5RWR1DJPC4Zf4vabLNFi0YS5-qS2iXkrxpCSY-i02RQWrXuc9CBJtvkeW_EbgUPJ_8oNLscX_yvmu67cQ2HNLxxR6CwFGFoTiUlPsIsDEz-GRtHV88D7dBFBYHYx";
    private Credentials credentials = new Credentials(clientId, clientSecret);

    private Yelp yelp = new Yelp(credentials);


    public void initializeYelpService() throws JSONException {

       AccessToken token = yelp.token() ;

        System.out.printf("Access token:%s%n", token.accessToken());
        System.out.printf("Token type:%s%n", token.tokenType());
        System.out.printf("Expires on:%s%n", token.expiresOn());
        System.out.printf("Is it expired? %s%n", token.isExpired());

    }

}
