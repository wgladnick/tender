package com.techelevator.dao.invitation;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.invitation.InviteRestaurants;

public class InviteRestaurantsSqlDAO implements InviteRestaurantsDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public InviteRestaurantsSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public InviteRestaurants getInviteRestaurantById(Long inviteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InviteRestaurants findInviteRestaurantByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InviteRestaurants create(InviteRestaurants listOfChoices) {
		// TODO Auto-generated method stub
		return null;
	}

}
