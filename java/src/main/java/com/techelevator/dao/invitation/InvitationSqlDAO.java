package com.techelevator.dao.invitation;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.invitation.Invitation;

@Component
public class InvitationSqlDAO implements InvitationDAO {

	private JdbcTemplate jdbcTemplate;

	public InvitationSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Invitation getInviteById(Long inviteId) {
		return jdbcTemplate.queryForObject("select * from invitation where invite_id = ?", Invitation.class, inviteId);
	}

	@Override
	public List<Invitation> findInviteByUserId(int creatorId) {
		List<Invitation> inviteList = new ArrayList<>();
		String sql = "SELECT * FROM invitation WHERE creator_user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, creatorId);
		while (results.next()) {
			Invitation invite = mapRowToInvitation(results);
			inviteList.add(invite);
		}
		return inviteList;

	}

	@Override
	public Invitation create(Invitation invitation) {

		String sql = "INSERT INTO invitation (location, radius, creator_user_id, deadline, reservation_date_time) VALUES (?,?,?,?,?) RETURNING invite_id";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, invitation.getLocation(), invitation.getRadius(),
				invitation.getCreatorId(), invitation.getDeadline(), invitation.getReservationDate());
		if (results.next()) {
			Long invite_id = results.getLong("invite_id");
			invitation.setInviteId(invite_id);
			return invitation;
		} else {
			throw new RuntimeException("Unable to create your invitation");
		}
	}

	private Invitation mapRowToInvitation(SqlRowSet rs) {

		Invitation invite = new Invitation();

		invite.setInviteId(rs.getLong("invite_id"));
		invite.setLocation(rs.getString("location"));
		invite.setRadius(rs.getInt("radius"));
		invite.setCreatorId(rs.getInt("creator_user_id"));
		Timestamp deadline = rs.getTimestamp("deadline");
		if (deadline != null) {
			invite.setDeadline(deadline);
		}
		invite.setReservationDate(rs.getString("reservation_date_time"));
		return invite;

	}
}
