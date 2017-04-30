package com.orderbid.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderbid.beans.User;
import com.orderbid.dao.LoginDao;
import com.orderbid.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDao loginDao;

	public User userLogin(User user) {
		return loginDao.userLogin(user);
	}
	
	public Integer createUser(User user){
		return loginDao.createUser(user);
	}
	
	@Override
	public boolean isUserExist(User user){
		return loginDao.isUserExist(user);
	}

	@Override
	public boolean isEmailExist(User user) {
		// TODO Auto-generated method stub
		return loginDao.isEmailExist(user);
	}

	@Override
	public User updatepwd(User user) {
		// TODO Auto-generated method stub
		return loginDao.updatepwd(user);
	}
}
