package com.orderbid.dao;

import java.util.List;

import com.orderbid.beans.User;

public interface UserDao {
	List<User> getAllUsers(User user);
	boolean isUserExist(User user);
	boolean isUserExist(String userName);
	boolean isUserExist(String userName, String emailAddr, String mobileNo);
	boolean isUserMobileExist(String mobileNo);
	boolean isUserEmailExist(String emailAddr);
	Integer addUser(User user);
	void updateUser(User user);
	void saveOrUpdateChangePassword(User user);
	
}
