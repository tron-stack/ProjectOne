package com.revature.services;

import com.revature.dao.IUserDao;
import com.revature.models.User;
import java.util.List;

public class UserService {
	private IUserDao iud;
	public UserService(IUserDao iud) {
		this.iud = iud;
	}
	public void registerUser(User u) {

				iud.createUser(u);
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
	public List<User> getAllUser() {
		return iud.getAllUsers();
	}
	public User getUserByUsername(String username){
		return iud.getUserByUsername(username);
	}
	public User getUserById(int id){
		return iud.getUserById(id);
	}
	public void updateUser(User user){
		iud.updateUser(user);
	}
	public List<User> readUserList() {
		return iud.getAllUsers();
	}
}
