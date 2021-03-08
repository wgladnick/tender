package com.techelevator.dao.invitation;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.invitation.Invitee;

public class InviteeSqlDAO implements InviteeDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public InviteeSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate =jdbcTemplate;
	}

	@Override
	public Invitee getInviteeById(Long inviteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invitee[] findInviteeByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invitee createInvitee(Invitee invitee) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Invitee mapRowToInvitee(SqlRowSet rs) {
		Invitee invitee = new Invitee();
		
		invitee.setInviteId(rs.getInt("invite_id"));
		invitee.setUniqueId(rs.getString("unique_id"));
		invitee.setUserId(rs.getInt("user_id"));
		invitee.setFirstName(rs.getString("first_name"));
		invitee.setLastName(rs.getString("last_name"));
		invitee.setEmail(rs.getString("email"));
		invitee.setHasVoted(rs.getBoolean("has_voted"));
		invitee.setIsAttending(rs.getString("is_attending"));
		
		return invitee;
	}

}
