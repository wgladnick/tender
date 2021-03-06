/*
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.techelevator.services.yelpservice.jsonparser;
import com.techelevator.services.yelpservice.businesses.Business;
import com.techelevator.services.yelpservice.businesses.Businesses;
import com.techelevator.services.yelpservice.businesses.Region;
import com.techelevator.services.yelpservice.businesses.SearchResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class SearchResultParser {
    static SearchResult parseFrom(JSONObject result) throws JSONException  {
        return new SearchResult(
            result.getInt("total"),
            parseBusinesses(result.getJSONArray("businesses")),
            Region.withCenter(CoordinatesParser.from(
                result.getJSONObject("region").getJSONObject("center")
            ))
        );
    }

    private static Businesses parseBusinesses(JSONArray businessResults) throws JSONException {
        List<Business> businesses = new ArrayList<>();
        for (int i = 0; i < businessResults.length(); i++)
            businesses.add(BusinessParser.businessFrom(businessResults.getJSONObject(i)));
        return new Businesses(businesses);
    }
}
