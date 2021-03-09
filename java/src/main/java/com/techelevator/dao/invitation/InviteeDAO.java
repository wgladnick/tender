package com.techelevator.dao.invitation;


import com.techelevator.model.invitation.Invitee;

public interface InviteeDAO {

    Invitee getInviteeById(Long inviteId);

    Invitee[] findInviteeByUserId(int userId);

    Invitee createInvitee(Invitee invitee);

    Invitee updateInviteeStatus(Invitee invitee);

    String getPreferredCategories();


}
