package com.techelevator.dao.invitation;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.techelevator.model.invitation.InviteRestaurants;
import com.techelevator.model.invitation.Invitee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.invitation.Invitation;

@Component
public class InvitationSqlDAO implements InvitationDAO {

	private JdbcTemplate jdbcTemplate;
	private InviteeDAO inviteeDAO;
	private InviteRestaurantsDAO inviteRestaurantsDAO;

	public InvitationSqlDAO(JdbcTemplate jdbcTemplate, InviteeDAO inviteeDAO, InviteRestaurantsDAO inviteRestaurantsDAO) {
		this.jdbcTemplate = jdbcTemplate;
		this.inviteeDAO = inviteeDAO;
		this.inviteRestaurantsDAO = inviteRestaurantsDAO;
	}

	@Override
	public Invitation getInviteById(Long inviteId) {
		
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT * FROM invitation where invite_id = ?", inviteId);
		while (results.next()) {
			Invitation invite = mapRowToInvitation(results);
			invite.setInvitees(inviteeDAO.getInviteeById(inviteId));
			invite.setRestaurantChoices(inviteRestaurantsDAO.getInviteRestaurantById(inviteId));
			return invite;
		}
		return null;
	}

	@Override
	public List<Invitation> findInviteByUserId(int creatorId) {
		List<Invitation> inviteList = new ArrayList<>();
		String sql = "SELECT * FROM invitation WHERE creator_user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, creatorId);
		while (results.next()) {
			Invitation invite = mapRowToInvitation(results);
			invite.setInvitees(inviteeDAO.getInviteeById(invite.getInviteId()));
			invite.setRestaurantChoices(inviteRestaurantsDAO.getInviteRestaurantById(invite.getInviteId()));
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
			invitation = makeInviteeList(invitation,invite_id);
			invitation = makeInvitationRestaurants(invitation, invite_id);
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


		//todo call invitee and restaurant daos to get what we need to build a full invite list.
		return invite;

	}

	private Invitation makeInviteeList(Invitation invitation, long inviteId) {
		List<Invitee> suppliedInvitees = invitation.getInvitees();
		List<Invitee> invitees = new ArrayList<>();

		for (Invitee invitee : suppliedInvitees) {
			invitee.setInviteId(inviteId);
			Invitee newInvitee = inviteeDAO.createInvitee(invitee);
			invitees.add(newInvitee);
		}
		invitation.setInvitees(invitees);
		return invitation;
	}

	private Invitation makeInvitationRestaurants(Invitation invitation, long inviteId) {
		List<InviteRestaurants> suppliedRestaurants = invitation.getRestaurantChoices();
		List<InviteRestaurants> updatedRestaurantList = new ArrayList<>();

		for(InviteRestaurants restaurant : suppliedRestaurants) {
			restaurant.setInviteId(inviteId);
			InviteRestaurants newRestaurant = inviteRestaurantsDAO.create(restaurant);
			updatedRestaurantList.add(newRestaurant);
		}
		invitation.setRestaurantChoices(updatedRestaurantList);

		return invitation;
	}
}
