package com.revature.services;

import com.revature.dao.IUserDao;
import com.revature.models.User;

public class UserService {

	private IUserDao iud;

	public UserService(IUserDao iud) {
		this.iud = iud;
	}


	public void registerUser(String username, String password, String firstName, String lastName, String email, int userRole) {
		User register = new User(0, username, password, firstName, lastName, email, userRole);
		iud.createUser(register);
	}

	public User loginUser(String username, String password) {
		User u = iud.getUserByUsername(username);
		if(u != null){
			if(password.equals(u.getPassword())){
				//This is out success
				return u;
			} else {
				//This means the password did not match
				return null;
			}
		}
		return null;
	}

}
