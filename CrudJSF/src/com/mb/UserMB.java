package com.mb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.facade.UserFacade;
import com.github.theholywaffle.lolchatapi.ChatServer;
import com.github.theholywaffle.lolchatapi.FriendRequestPolicy;
import com.github.theholywaffle.lolchatapi.LolChat;
import com.github.theholywaffle.lolchatapi.riotapi.RateLimit;
import com.github.theholywaffle.lolchatapi.riotapi.RiotApiKey;
import com.github.theholywaffle.lolchatapi.wrapper.Friend;
import com.model.User;

@RequestScoped
@ManagedBean
public class UserMB {
	private User user = new User();
	private List<Friend> friends = new ArrayList<Friend>();
	private Friend friend;
	
	@EJB
	private UserFacade userFacade;
	
	
	public String login() {
	       
	       final LolChat api = new LolChat(ChatServer.BR, FriendRequestPolicy.ACCEPT_ALL, new RiotApiKey("009946ee-2a06-4966-91d4-35cdaa487d81",RateLimit.DEFAULT));
	 
	       if (api.login(user.getLogin(),user.getPassword())) {
	          	           
	           for (Friend f : api.getFriends()) {
	               if(f.isOnline()) {
	                   friends.add(f);          	                   
	               }
	           }
	           
	           user.setName(api.getName(false));
	       }
	       return "listAllDogs";
	   }

    public User getUser(){
		if(user == null){
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			String userEmail = context.getUserPrincipal().getName();
			
			user = userFacade.findUserByEmail(userEmail);
		}
		
		return user;
	}
	
	public boolean isUserAdmin(){
		return getRequest().isUserInRole("ADMIN");
	}
	
	public String logOut(){
		getRequest().getSession().invalidate();
		return "logout";
	}
	

    public List<Friend> getFriends() {
        return friends;
    }

    
    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }
}