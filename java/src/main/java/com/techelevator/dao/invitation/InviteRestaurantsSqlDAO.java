package com.techelevator.dao.invitation;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.invitation.InviteRestaurants;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
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
		String sql = "INSERT INTO invitation_restaurant (invite_id, yelp_id) VALUES (?,?) RETURNING invite_id, yelp_id, thumbs_up, thumbs_down";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql,listOfChoices.getInviteId(),listOfChoices.getYelpId());

		while (results.next()) {
			listOfChoices.setInviteId(results.getLong("invite_id"));
			listOfChoices.setYelpId(results.getString("yelp_id"));
			listOfChoices.setThumbsUp(results.getInt("thumbs_up"));
			listOfChoices.setThumbsDown(results.getInt("thumbs_down"));
		}

		return listOfChoices;
	}

}
