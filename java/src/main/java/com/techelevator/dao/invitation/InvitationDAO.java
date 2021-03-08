package com.techelevator.dao.invitation;

import java.util.List;

import com.techelevator.model.invitation.Invitation;

public interface InvitationDAO {

	Invitation getInviteById(Long inviteId);
	
	List<Invitation> findInviteByUserId(int creatorId);
	
	Invitation create(Invitation invitation);
	
}
