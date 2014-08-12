package com.facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.UserDAO;
import com.model.User;

@Stateless
public class UserFacadeImp implements UserFacade {

	@EJB 
	private UserDAO userDAO;
	
	public User findUserByEmail(String email) {
		return userDAO.findUserByEmail(email);
	}
}