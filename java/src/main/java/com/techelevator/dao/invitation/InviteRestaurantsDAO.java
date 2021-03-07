package com.techelevator.dao.invitation;


import com.techelevator.model.invitation.InviteRestaurants;

public interface InviteRestaurantsDAO {

	InviteRestaurants getInviteRestaurantById(Long inviteId);
	
	InviteRestaurants findInviteRestaurantByUserId(int userId);
	
	InviteRestaurants create(InviteRestaurants listOfChoices);
}
