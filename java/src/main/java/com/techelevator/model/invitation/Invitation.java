package com.techelevator.model.invitation;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



public class Invitation {
	private Long inviteId;
	private String location;
	private int radius;
	private int creatorId;
	@JsonSerialize(as = Timestamp.class)
	private Timestamp deadline;
	private String reservationDate;
	private Invitee[] invitees;
	private InviteRestaurants[] restaurantChoices;
	
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

	public Invitee[] getInvitees() {
		return invitees;
	}

	public void setInvitees(Invitee[] invitees) {
		this.invitees = invitees;
	}

	public InviteRestaurants[] getRestaurantChoices() {
		return restaurantChoices;
	}

	public void setRestaurantChoices(InviteRestaurants[] restaurantChoices) {
		this.restaurantChoices = restaurantChoices;
	}

	
	
	
	
}

