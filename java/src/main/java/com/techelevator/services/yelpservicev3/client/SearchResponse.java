/*
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.techelevator.services.yelpservicev3.client;

import com.techelevator.services.yelpservicev3.businesses.SearchResult;
import org.json.JSONException;

/**
 * Response sent by Yelp's Search API
 *
 * @link https://www.yelp.com/developers/documentation/v3/business_search
 */
public class SearchResponse extends Response {
    public static SearchResponse fromOriginalResponse(String jsonResponse) {
        return new SearchResponse(jsonResponse);
    }

    public SearchResult searchResult() throws JSONException {
        return parser.searchResult(jsonResponse);
    }

    private SearchResponse(String jsonResponse) {
        super(jsonResponse);
    }
}
