package com.orderbid.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderbid.beans.User;
import com.orderbid.beans.vo.UserVO;
import com.orderbid.dao.UserDao;
import com.orderbid.service.UserService;
import com.orderbid.util.BeansVOConverter;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public List<UserVO> getAllUsers(User user) {
		return BeansVOConverter.getUserVOList(userDao.getAllUsers(user));
	}

	@Override
	public boolean isUserExist(String userName) {
		// TODO Auto-generated method stub
		return userDao.isUserExist(userName);
	}

	@Override
	public boolean isUserExist(User user) {
		// TODO Auto-generated method stub
		return userDao.isUserExist(user);
	}

	@Override
	public boolean isUserExist(String userName, String emailAddr,
			String mobileNo) {
		// TODO Auto-generated method stub
		return userDao.isUserExist(userName, emailAddr, mobileNo);
	}

	@Override
	public boolean isUserMobileExist(String mobileNo) {
		// TODO Auto-generated method stub
		return userDao.isUserMobileExist(mobileNo);
	}

	@Override
	public boolean isUserEmailExist(String emailAddr) {
		// TODO Auto-generated method stub
		return userDao.isUserEmailExist(emailAddr);
	}

	@Override
	public Integer addUser(User user){
		// TODO Auto-generated method stub
		return userDao.addUser(user);
	}
	@Override
	public void updateUser(User user){
		userDao.updateUser(user);
	}

	@Override
	public void saveOrUpdateChangePassword(User user) {
		// TODO Auto-generated method stub
		userDao.saveOrUpdateChangePassword(user);
	}
}
