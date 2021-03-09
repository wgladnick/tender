package com.techelevator.dao.invitation;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.invitation.Invitee;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
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
		// Generates the unique ID we will use on the front end to track the users.
		String uniqueId = generateUniqueId();
		invitee.setUniqueId(uniqueId);

		String sql = "INSERT INTO invitee_details (invite_id,unique_id, invitee_user_id, first_name, last_name, email) VALUES (?,?,?,?,?,?)";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql,invitee.getInviteId(),invitee.getUniqueId(),invitee.getUserId(),
				invitee.getFirstName(), invitee.getLastName(),invitee.getEmail());

		return invitee;
	}

	@Override
	public Invitee updateInviteeStatus(Invitee invitee) {

		String sql = "UPDATE invitee_details SET has_voted = ?, is_attending = ? WHERE unique_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql,invitee.getHasVoted(), invitee.getIsAttending(), invitee.getUniqueId());

		return invitee;
	}
	
	public Invitee mapRowToInvitee(SqlRowSet rs) {
		Invitee invitee = new Invitee();
		
		invitee.setInviteId(rs.getInt("invite_id"));
		invitee.setUniqueId(rs.getString("unique_id"));
		invitee.setUserId(rs.getInt("invitee_user_id"));
		invitee.setFirstName(rs.getString("first_name"));
		invitee.setLastName(rs.getString("last_name"));
		invitee.setEmail(rs.getString("email"));
		invitee.setHasVoted(rs.getBoolean("has_voted"));
		invitee.setIsAttending(rs.getString("is_attending"));
		
		return invitee;
	}


	public String generateUniqueId() {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 15;
		Random random = new Random();

		return random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();


	}

}
