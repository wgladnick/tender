/*
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.techelevator.services.yelpservicev3.client;

import com.techelevator.services.yelpservicev3.reviews.Review;
import org.json.JSONException;

import java.util.List;

/**
 * Response sent by Yelp's Reviews API
 *
 * @link https://www.yelp.com/developers/documentation/v3/business_reviews
 */
public class ReviewsResponse extends Response {
    public static ReviewsResponse fromOriginalResponse(String jsonResponse) {
        return new ReviewsResponse(jsonResponse);
    }

    public List<Review> reviews() throws JSONException {
        return parser.reviews(jsonResponse);
    }

    private ReviewsResponse(String jsonResponse) {
        super(jsonResponse);
    }
}
