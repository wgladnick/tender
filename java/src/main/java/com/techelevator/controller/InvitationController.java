package com.techelevator.controller;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.invitation.InvitationDAO;
import com.techelevator.dao.invitation.InviteRestaurantsDAO;
import com.techelevator.dao.invitation.InviteeDAO;
import com.techelevator.model.invitation.Invitation;
import com.techelevator.model.invitation.InviteRestaurants;
import com.techelevator.model.invitation.Invitee;

@RestController
@ComponentScan({"com.techelevator.dao"})
@CrossOrigin 
@RequestMapping(value = "/invitation")
public class InvitationController {
	
	private final InvitationDAO invitationDAO;
	private final InviteeDAO inviteeDAO;
	private final InviteRestaurantsDAO inviteRestaurantsDAO;
	
	public InvitationController(InvitationDAO invitationDAO, InviteeDAO inviteeDAO, InviteRestaurantsDAO inviteRestaurantsDAO) {
		this.invitationDAO = invitationDAO;
		this.inviteeDAO = inviteeDAO;
		this.inviteRestaurantsDAO = inviteRestaurantsDAO;
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
	public Invitation create(@RequestBody Invitation invitation) {
		return invitationDAO.create(invitation);
	}
	
	@RequestMapping(value = "/invitee/{inviteId}", method = RequestMethod.GET)
	public List<Invitee> getInviteeById(@PathVariable Long inviteId) {
		return inviteeDAO.getInviteeById(inviteId);
	}
	
	
	@RequestMapping(value = "/invitee", method = RequestMethod.PUT)
	public Invitee updateInviteeStatus(@RequestBody Invitee invitee) {
		return inviteeDAO.updateInviteeStatus(invitee);
	}
	
	
	@RequestMapping(value = "/restaurant/{inviteId}", method = RequestMethod.GET)
	public List<InviteRestaurants> getInviteRestaurantById(@PathVariable Long inviteId) {
		return inviteRestaurantsDAO.getInviteRestaurantById(inviteId);
	}
	
	
	@RequestMapping(value = "/thumbsup", method = RequestMethod.PUT)
	public InviteRestaurants voteThumbsUp(@RequestBody InviteRestaurants inviterestaurants) {
		return inviteRestaurantsDAO.voteThumbsUp(inviterestaurants);
	}
	
	
	@RequestMapping(value = "/thumbsdown", method = RequestMethod.PUT)
	public InviteRestaurants voteThumbsDown(@RequestBody InviteRestaurants inviterestaurants) {
		return inviteRestaurantsDAO.voteThumbsDown(inviterestaurants);
	}
	
	
	
}
