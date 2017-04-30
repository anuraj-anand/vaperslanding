package com.orderbid.dao;

import com.orderbid.beans.User;

public interface LoginDao {
	User userLogin(User user);
	Integer createUser(User user);
	boolean isUserExist(User user);
	boolean isUserExist(String userName);
	boolean isEmailExist(User user);
	User updatepwd(User user);
}
