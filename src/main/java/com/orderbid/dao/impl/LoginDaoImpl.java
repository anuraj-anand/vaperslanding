package com.orderbid.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.orderbid.beans.User;
import com.orderbid.dao.LoginDao;
import com.orderbid.service.WarehouseService;

@Repository("LoginDao")
@Transactional
public class LoginDaoImpl extends BaseDaoImpl implements LoginDao {

	@Autowired
	WarehouseService warehouseService;

	@SuppressWarnings("unchecked")
	@Override
	public User userLogin(User user) {
		// this.hibernateTemplate.findByCriteria(DetachedCriteria.forClass(User.class))
		List<User> users = new ArrayList<User>();
		String query = "from User where username = ? and password = ?";
		Object[] queryParam = { user.getUserName(), user.getPassword() };
		users = (List<User>) hibernateTemplate.find(query, queryParam);
		if (users != null && users.size() > 0) {
			return users.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isUserExist(String userName) {
		// this.hibernateTemplate.findByCriteria(DetachedCriteria.forClass(User.class))
		List<User> users = new ArrayList<User>();
		String query = "from User where username = ? ";
		Object[] queryParam = { userName };
		users = (List<User>) hibernateTemplate.find(query, queryParam);
		if (users != null && users.size() > 0) {
			return true;
		}
		return false;
	}

	public Integer createUser(User user) {
		return (Integer) super.createEntity(user);
	}

	@Override
	public boolean isUserExist(User user) {
		/*
		 * user = (User)super.find(user); return user != null ? true : false;
		 */
		List<User> users = new ArrayList<User>();
		String query = "from User where username = ? or email = ? or mobile = ?";
		Object[] queryParam = { user.getUserName(), user.getEmail(),
				user.getMobile() };
		users = (List<User>) hibernateTemplate.find(query, queryParam);
		if (users != null && users.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isEmailExist(User user) {
		// TODO Auto-generated method stub

		List<User> users = new ArrayList<User>();
		String query = "from User where email = ?";
		Object[] queryParam = { user.getEmail() };
		users = (List<User>) hibernateTemplate.find(query, queryParam);
		if (users != null && users.size() > 0) {
			return true;
		}
		return false;

	}

	@Override
	public User updatepwd(User user) {

		User usr = new User();

		Transaction tx = null;
		SessionFactory sfactory = hibernateTemplate.getSessionFactory();
		Session session = sfactory.openSession();
		tx = session.beginTransaction();
		Criteria cri = session.createCriteria(User.class);
		cri.add(Restrictions.eq("email", user.getEmail()));
		List<User> results = cri.list();

		if (results != null && results.size() != 0) {

			User usernew = (User) session.get(User.class, results.get(0)
					.getId());
			usernew.setPassword(user.getPassword());
			session.update(usernew);
			tx.commit();
			session.close();

			return usernew;
		}

		return null;
	}
}
