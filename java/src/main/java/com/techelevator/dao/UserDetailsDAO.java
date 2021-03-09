package com.techelevator.dao;

import com.techelevator.model.UserDetails;

public interface UserDetailsDAO {
    UserDetails getDetails(long userId);
    UserDetails updateDetails(UserDetails userDetails);
}
