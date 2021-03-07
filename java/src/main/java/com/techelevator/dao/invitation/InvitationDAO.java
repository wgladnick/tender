package com.techelevator.dao.invitation;

import com.techelevator.model.invitation.Invitation;

public interface InvitationDAO {

	Invitation getInviteById(Long inviteId);
	
	Invitation[] findInviteByUserId(int creatorId);
	
	Invitation create(Invitation invitation);
	
}
