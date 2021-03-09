package com.techelevator.dao.invitation;

import java.util.ArrayList;
import java.util.List;

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
	public List<InviteRestaurants> getInviteRestaurantById(Long inviteId) {
		List<InviteRestaurants> inviterestaurants = new ArrayList<>();
		String sql = "SELECT * FROM invitation_restaurant WHERE invite_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, inviteId);
		
		while(results.next()) {
			InviteRestaurants invrest = mapRowToInviteRestaurant(results);
			inviterestaurants.add(invrest);
		}
		return inviterestaurants;
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
	
	public InviteRestaurants mapRowToInviteRestaurant(SqlRowSet rs) {
		InviteRestaurants ir = new InviteRestaurants();
		
		ir.setInviteId(rs.getInt("invite_id"));
		ir.setYelpId(rs.getString("yelp_id"));
		ir.setThumbsUp(rs.getInt("thumbs_up"));
		ir.setThumbsDown(rs.getInt("thumbs_down"));
		
		return ir;
		
	}
	
	public InviteRestaurants voteCount(InviteRestaurants inviterestaurants) {
		
		String sql = "UPDATE invitation_restaurant " + 
				"SET thumbs_up = thumbs_up + 1 " + 
				"WHERE yelp_id = ? AND invite_id = ?";
		jdbcTemplate.update(sql, inviterestaurants.getYelpId(), inviterestaurants.getInviteId());
		
		String sql2 = "SELECT * FROM invitation_restaurant WHERE yelp_id = ? AND invite_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql2, inviterestaurants.getYelpId(), inviterestaurants.getInviteId());
		
		while(results.next()) {
			inviterestaurants = mapRowToInviteRestaurant(results);
		}
		return inviterestaurants;		

	}

}
