package com.revature.dao;

import com.revature.models.User;

public interface IUserDao {

    public void createUser(User user);

    public User getUserById(int id);

    public User updateUser(User user);
    public void deleteUser(int id);
}
