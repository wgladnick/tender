package com.techelevator.controller;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.invitation.InvitationDAO;
import com.techelevator.model.invitation.Invitation;

@RestController
@ComponentScan({"com.techelevator.dao"})
@CrossOrigin 
@RequestMapping(value = "/invitation")
public class InvitationController {
	
	private final InvitationDAO invitationDAO;
	
	public InvitationController(InvitationDAO invitationDAO) {
		this.invitationDAO = invitationDAO;
	}
	
	
	@RequestMapping(value = "/{inviteId}", method = RequestMethod.GET)
	public Invitation getInviteById(@PathVariable Long inviteId) {
		return invitationDAO.getInviteById(inviteId);
	}
	
	
	@RequestMapping(value = "/user/{creatorId}", method = RequestMethod.GET)
	public List<Invitation> findInviteByUserId(@PathVariable int creatorId) {
		return invitationDAO.findInviteByUserId(creatorId);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public Invitation create(Invitation invitation) {
		Invitation invitation2 = invitation;
		return invitationDAO.create(invitation);
	}
	
	
	
	
}
