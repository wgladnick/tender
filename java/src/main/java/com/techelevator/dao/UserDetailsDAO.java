package com.techelevator.dao;

import com.techelevator.model.User;
import com.techelevator.model.UserDetails;

import java.util.List;

public interface UserDetailsDAO {
    UserDetails getDetails(long userId);
    UserDetails updateDetails(UserDetails userDetails);
    void create(long userId);
    UserDetails addCategories(UserDetails userDetails);
    UserDetails removeCategories(UserDetails userDetails);
    UserDetails setActiveCategories(UserDetails userDetails);
}
