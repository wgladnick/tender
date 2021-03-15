package com.techelevator.model.invitation;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Invitation {
	private Long inviteId;
	private String inviteName;
	private String location;
	private int radius;
	private int creatorId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
	private Timestamp deadline;
	private String reservationDate;
	private List<Invitee> invitees;
	private List<InviteRestaurants> restaurantChoices;
	
	public Invitation() {
		
	}

	public Long getInviteId() {
		return inviteId;
	}

	public void setInviteId(Long inviteId) {
		this.inviteId = inviteId;
	}

	public String getLocation() {
		return location;
	}

	public String getInviteName() {
		return inviteName;
	}

	public void setInviteName(String inviteName) {
		this.inviteName = inviteName;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	public Timestamp getDeadline() {
		return deadline;
	}

	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public List<Invitee> getInvitees() {
		return invitees;
	}

	public void setInvitees(List<Invitee> invitees) {
		this.invitees = invitees;
	}

	public List<InviteRestaurants> getRestaurantChoices() {
		return restaurantChoices;
	}

	public void setRestaurantChoices(List<InviteRestaurants> restaurantChoices) {
		this.restaurantChoices = restaurantChoices;
	}

	
	
	
	
}

