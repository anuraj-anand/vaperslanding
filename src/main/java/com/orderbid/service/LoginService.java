package com.orderbid.service;

import com.orderbid.beans.User;

public interface LoginService {
	
	User userLogin(User user);
	
	Integer createUser(User user);

	boolean isUserExist(User user);
	
	boolean isEmailExist(User user);

	User updatepwd(User user);
}
