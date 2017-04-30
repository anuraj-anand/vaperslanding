package com.orderbid.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.orderbid.beans.Order;
import com.orderbid.beans.User;
import com.orderbid.dao.UserDao;

@Repository("UserDao")
@Transactional
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	/**
	 * 
	 */
	public List<User> getAllUsers(User user) {
		List<User> users = new ArrayList<User>();
		String query = "from User where company = ? ";
		Object[] queryParam = { user.getCompany() };
		users = (List<User>) find(query, queryParam);
		if (users != null && users.size() > 0) {
			return users;
		}
		return null;
	}

	@Override
	public boolean isUserExist(String userName, String emailAddr,
			String mobileNo) {
		// this.hibernateTemplate.findByCriteria(DetachedCriteria.forClass(User.class))
		List<User> users = new ArrayList<User>();
		String query = "from User where userName = ? or mobile = ? or email = ? ";
		Object[] queryParam = { userName };
		users = (List<User>) hibernateTemplate.find(query, queryParam);
		if (users != null && users.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isUserExist(User user) {
		user = (User) super.find(user);
		return user != null ? true : false;
	}

	@Override
	public boolean isUserExist(String userName) {
		List<User> users = new ArrayList<User>();
		String query = "from User where username = ? ";
		Object[] queryParam = { userName };
		users = (List<User>) hibernateTemplate.find(query, queryParam);
		if (users != null && users.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isUserMobileExist(String mobileNo) {
		// this.hibernateTemplate.findByCriteria(DetachedCriteria.forClass(User.class))
		List<User> users = new ArrayList<User>();
		String query = "from User where mobile = ? ";
		Object[] queryParam = { mobileNo };
		users = (List<User>) hibernateTemplate.find(query, queryParam);
		if (users != null && users.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isUserEmailExist(String emailAddr) {
		// this.hibernateTemplate.findByCriteria(DetachedCriteria.forClass(User.class))
		List<User> users = new ArrayList<User>();
		String query = "from User where email = ? ";
		Object[] queryParam = { emailAddr };
		users = (List<User>) hibernateTemplate.find(query, queryParam);
		if (users != null && users.size() > 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public Integer addUser(User user){
		return (Integer)createEntity(user);
	}
	
	@Override
	public void updateUser(User user){
		updateEntity(user);
	}

	@Override
	public void saveOrUpdateChangePassword(User user) {
		// TODO Auto-generated method stub
		updateEntity(user);
		
	}
	
}
