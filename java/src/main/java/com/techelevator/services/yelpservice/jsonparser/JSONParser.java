/*
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.techelevator.services.yelpservice.jsonparser;

import com.techelevator.services.yelpservice.businesses.BusinessDetails;
import com.techelevator.services.yelpservice.businesses.SearchResult;
import com.techelevator.services.yelpservice.client.AccessToken;
import com.techelevator.services.yelpservice.parser.Parser;
import com.techelevator.services.yelpservice.reviews.Review;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class JSONParser implements Parser {
    @Override
    public BusinessDetails business(String response) throws JSONException {
        return BusinessParser.detailsFrom(new JSONObject(response));
    }

    @Override
    public SearchResult searchResult(String response) throws JSONException  {
        return SearchResultParser.parseFrom(new JSONObject(response));
    }

    @Override
    public List<Review> reviews(String response) throws JSONException {
        return ReviewsParser.parseFrom(new JSONObject(response));
    }

    @Override
    public AccessToken token(String response) throws JSONException {
        JSONObject token = new JSONObject(response);

        return AccessToken.fromYELP(
            token.getString("access_token"),
            token.getString("token_type"),
            token.getLong("expires_in")
        );
    }
}
