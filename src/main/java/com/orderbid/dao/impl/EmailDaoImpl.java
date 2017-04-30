package com.orderbid.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.orderbid.beans.Bid;
import com.orderbid.beans.Order;
import com.orderbid.dao.EmailDao;

@Repository("EmailDao")
@Transactional
public class EmailDaoImpl extends BaseDaoImpl implements EmailDao {

	@Override
	public List<Bid> getAllBIDformail(String Status) {
		List<Bid > listbid=null;
		
		SessionFactory sfactory=hibernateTemplate.getSessionFactory();
		Session session=sfactory.openSession();
		Transaction tx=null;
		try
		{
			
			tx=session.beginTransaction();
			
			
			
			Criteria criteria=session.createCriteria(Bid.class);
			criteria.add(Restrictions.eq("bidStatus", "C"));
			
			 listbid=criteria.list();
			
			System.err.println("========size of the Bid Table with Status C"+listbid.size());
			tx.commit();
			
		}catch(Exception e)
		{
			e.getMessage();
		}
		finally
		{
			session.close();
		}
				
		
		// TODO Auto-generated method stub
		return listbid;
	}

	@Override
	public List<Order> getAllOrderForMail(String OrderStatus) {
		// TODO Auto-generated method stub
		
		List<Order> listorder=null;
		SessionFactory sfactory=hibernateTemplate.getSessionFactory();
		Session session=sfactory.openSession();
		Transaction tx=null;
		try
		{
			
			tx=session.beginTransaction();
			
			
			
			Criteria criteria=session.createCriteria(Order.class);
			criteria.add(Restrictions.eq("status", "A"));
			
			listorder=criteria.list();
			
			System.err.println("========size of the Bid Table with Status C"+listorder.size());
			tx.commit();
			
		}catch(Exception e)
		{
			e.getMessage();
		}
		finally
		{
			session.close();
		}
				
		
		// TODO Auto-generated method stub
		return listorder;
	}

}
