package com.techelevator.dao.invitation;

import java.util.List;

import com.techelevator.model.invitation.InviteRestaurants;

public interface InviteRestaurantsDAO {

	List<InviteRestaurants> getInviteRestaurantById(Long inviteId);
	
	InviteRestaurants create(InviteRestaurants listOfChoices);
	
	InviteRestaurants voteThumbsUp(InviteRestaurants inviterestaurants);
	
	InviteRestaurants voteThumbsDown(InviteRestaurants inviterestaurants);
}
