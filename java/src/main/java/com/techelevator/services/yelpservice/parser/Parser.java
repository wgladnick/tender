/*
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.techelevator.services.yelpservice.parser;

import com.techelevator.services.yelpservice.businesses.BusinessDetails;
import com.techelevator.services.yelpservice.businesses.SearchResult;
import com.techelevator.services.yelpservice.client.AccessToken;
import com.techelevator.services.yelpservice.reviews.Review;
import org.json.JSONException;

import java.util.List;

public interface Parser {
    BusinessDetails business(String response) throws JSONException;

    SearchResult searchResult(String response) throws JSONException ;

    List<Review> reviews(String response) throws JSONException ;

    AccessToken token(String response) throws JSONException ;
}
