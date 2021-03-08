package com.techelevator.model.invitation;

public class Invitee {
	private int inviteId;
	private String uniqueId;
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private Boolean hasVoted;
	private String isAttending;
	
	public Invitee() {
		
	}

	public int getInviteId() {
		return inviteId;
	}

	public void setInviteId(int inviteId) {
		this.inviteId = inviteId;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getHasVoted() {
		return hasVoted;
	}

	public void setHasVoted(Boolean hasVoted) {
		this.hasVoted = hasVoted;
	}

	public String getIsAttending() {
		return isAttending;
	}

	public void setIsAttending(String isAttending) {
		this.isAttending = isAttending;
	}
	
	
}