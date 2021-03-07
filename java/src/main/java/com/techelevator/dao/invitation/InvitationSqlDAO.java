package com.techelevator.dao.invitation;

import java.util.ArrayList;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.invitation.Invitation;

public class InvitationSqlDAO  implements InvitationDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public InvitationSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Invitation getInviteById(Long inviteId) {
		return jdbcTemplate.queryForObject("select * from invitation where invite_id = ?", Invitation.class, inviteId);
	}

	
	@Override
	public Invitation[] findInviteByUserId(int creatorId) {
		String sql = "SELECT * FROM invitation WHERE creator_user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, creatorId);
		
		return null;
			
		}
	

	@Override
	public Invitation create(Invitation invitation) {
		// TODO Auto-generated method stub
		return null;
	}

	private Invitation mapRowToInvitation(SqlRowSet rs) {
		Invitation invite = new Invitation();
		invite.setInviteId(rs.getLong("invite_id"));
		invite.setLocation(rs.getString("location"));
		invite.setRadius(rs.getInt("radius"));
		invite.setCreatorId(rs.getInt("creator_user_id"));
		//invite.setDeadline(rs.getTimestamp("deadline"));
		invite.setReservationDate(rs.getString("reservation_date_time"));
		return invite;
		
		
	}
}
