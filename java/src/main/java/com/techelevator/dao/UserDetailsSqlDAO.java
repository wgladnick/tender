package com.techelevator.dao;

import com.techelevator.model.User;
import com.techelevator.model.UserDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailsSqlDAO implements UserDetailsDAO {
    private JdbcTemplate jdbcTemplate;

    public UserDetailsSqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserDetails getDetails(long userId) {
        String sql = "SELECT user_id, address, city, state, zip, default_radius" +
                "FROM user_details" +
                "WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()) {
            return mapRowToUserDetails(results);
        } else {
            throw new RuntimeException("userId " + userId + " was not found.");
        }
    }

    @Override
    public UserDetails updateDetails(UserDetails userDetails) {
        return null;
    }



    private UserDetails mapRowToUserDetails(SqlRowSet results) {
        UserDetails userDetails = null;

        userDetails.setAddress(results.getString("address"));
        userDetails.setCity(results.getString("city"));
        userDetails.setState(results.getString("state"));
        userDetails.setZip(results.getInt("zip"));
        userDetails.setDefault_radius(results.getInt("default_radius"));

        long userId = results.getLong("user_id");
        userDetails.setSearchCategories(getUserDetailsSearchCategories(userId));

        return userDetails;

    }

    private String getUserDetailsSearchCategories(long userId) {
        List<String> searchCategories = new ArrayList<>();
        String searchCategoryString = "";
        int counter = 0;

        String sql = "SELECT search_name FROM food_categories" +
                "JOIN user_categories ON food_categories.category_id = user_categories.category_id" +
                "WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        while (results.next()) {
            searchCategories.add(results.getString("search_name"));
        }

        if (searchCategories.size() > 0) {
            for(String searchItem : searchCategories) {
                searchCategoryString += searchItem;
                if (counter < searchCategories.size()) {
                    searchCategoryString += ",";
                    counter++;
                }
            }
        }

        return searchCategoryString;
    }


}
