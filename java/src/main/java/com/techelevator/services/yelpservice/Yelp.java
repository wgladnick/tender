/*
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.techelevator.services.yelpservice;

import com.techelevator.services.yelpservice.apacheclient.ApacheHttpClient;
import com.techelevator.services.yelpservice.client.*;
import com.techelevator.services.yelpservice.jsonparser.JSONParser;
import com.techelevator.services.yelpservice.parser.Parser;
import com.techelevator.services.yelpservice.search.SearchCriteria;
import org.apache.http.impl.client.HttpClientBuilder;

public class Yelp {
    private final Credentials credentials;
    private final YelpClient yelpClient;
    private final Parser parser;

    public Yelp(Credentials credentials) {
        this(
            credentials,
            new ApacheHttpClient(HttpClientBuilder.create().build(), new YelpURIs()),
            new JSONParser()
        );
    }

    public Yelp(Credentials credentials, YelpClient yelpClient) {
        this(credentials, yelpClient, new JSONParser());
    }

    public Yelp(Credentials credentials, YelpClient yelpClient, Parser parser) {
        this.credentials = credentials;
        this.yelpClient = yelpClient;
        this.parser = parser;
    }

    public SearchResponse search(SearchCriteria criteria) {
        yelpClient.allBusinessesMatching(criteria, token().accessToken());
        return SearchResponse.fromOriginalResponse(yelpClient.responseBody());
    }

    public BusinessResponse searchById(String id) {
        yelpClient.businessWith(id, token().accessToken());
        return BusinessResponse.fromOriginalResponse(yelpClient.responseBody());
    }

    public ReviewsResponse reviews(String id) {
        yelpClient.allReviewsFor(id, token().accessToken());
        return ReviewsResponse.fromOriginalResponse(yelpClient.responseBody());
    }

    public AccessToken token() {
        if (credentials.isTokenExpired()) authenticate();

        return credentials.token();
    }

    private void authenticate() {
        yelpClient.authenticate(credentials.toMap());
        credentials.updateToken(parser.token(yelpClient.responseBody()));
    }
}
