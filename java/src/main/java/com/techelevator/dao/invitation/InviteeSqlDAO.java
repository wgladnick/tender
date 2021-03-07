package com.techelevator.dao.invitation;

import org.springframework.jdbc.core.JdbcTemplate;

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

}
