package com.orderbid.service;

import java.util.List;

import com.orderbid.beans.User;
import com.orderbid.beans.vo.UserVO;

public interface UserService {

	List<UserVO> getAllUsers(User user);
	
	boolean isUserExist(String usrName);
	
	boolean isUserExist(User user);

	boolean isUserMobileExist(String mobileNo);
	
	boolean isUserEmailExist(String emailAddr);
	
	boolean isUserExist(String userName, String emailAddr, String mobileNo);
	
	Integer addUser(User user);
	
	void updateUser(User user);
	void saveOrUpdateChangePassword(User user);
	
}
