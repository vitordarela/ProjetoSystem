package com.mb;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.facade.UserFacade;
import com.model.User;

@SessionScoped
@ManagedBean
public class UserMB {
	private User user;
	
	@EJB
	private UserFacade userFacade;
	
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

	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
}