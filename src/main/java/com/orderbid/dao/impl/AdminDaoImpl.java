package com.orderbid.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.orderbid.beans.Broad_casting_Message;
import com.orderbid.beans.Company;
import com.orderbid.beans.KycInfo;
import com.orderbid.beans.OngoingBid;
import com.orderbid.beans.Order;
import com.orderbid.beans.User;
import com.orderbid.dao.AdminDao;

@Repository("AdminDao")
@Transactional
public class AdminDaoImpl extends BaseDaoImpl implements AdminDao {

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getEselLogPendingApproval() {
		List<Company> eselCompanylist = new ArrayList<Company>();
		List<Company> logisCompanylist = new ArrayList<Company>();
		;
		Map<String, Object> eselLogisCompanyDetails = new HashMap<String, Object>();

		String eselQuery = "from Company where active=0 and type=1";
		eselCompanylist = (List<Company>) find(eselQuery);

		String logisQuery = "from Company where active=0 and type=2";
		logisCompanylist = (List<Company>) find(logisQuery);

		eselLogisCompanyDetails.put("eselPendingApprovalCount", eselCompanylist.size());
		eselLogisCompanyDetails.put("logisPendingApprovalCount", logisCompanylist.size());
		eselLogisCompanyDetails.put("eselPendingApprovalList", eselCompanylist);
		eselLogisCompanyDetails.put("logisPendingApprovalList", logisCompanylist);
		System.out.println("getting esel logistic user details======>" + eselLogisCompanyDetails);
		return eselLogisCompanyDetails;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getEselLogisUserList() {
		List<Company> userList = null;
		String query = "from Company where type in(1,2)";
		userList = (List<Company>) find(query);
		return userList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> filterRegisEselLogisUsers(Integer userType, Integer filterType, Integer startDateTimestamp,
			Integer endDateTimestamp) {
		List<Company> filterUserList = null;
		String query = null;
		if (userType == -1) { // both E-seller and Logistic
			query = "from Company where type in(1,2) and active=1";
		}
		else{
		 query = "from Company where type="+userType+" and active=1";
		}
		
		
		Calendar calendar = Calendar.getInstance();
		Integer endTimestamp = (int) (calendar.getTimeInMillis() / 1000);
		Integer startTimestamp = null;

		switch (filterType) {
		case 1:// weekly
			calendar.add(Calendar.DATE, -7);
			startTimestamp = (int) (calendar.getTimeInMillis() / 1000);
			break;
		case 2:// monthly
			calendar.add(Calendar.MONTH, -1);
			startTimestamp = (int) (calendar.getTimeInMillis() / 1000);
			break;
		case 3:// date range
			startTimestamp = startDateTimestamp;
			endTimestamp = endDateTimestamp;
			break;

		default:
			break;
		}
		
		query = query + " and creationDatetime between "+startTimestamp+" and "+endTimestamp+"";
		filterUserList = (List<Company>) find(query);
		return filterUserList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OngoingBid> filterOrdersByLogisticUser(String userId, Integer filterType, Long startDateTimestamp,
			Long endDateTimestamp) {
		List<OngoingBid> filteredOrdersBidedList = null;
		String query = null;
//			query = "from OngoingBid where bidderId="+userId+"";
			query = "from OngoingBid where bidderId=?";
		
		Calendar calendar = Calendar.getInstance();
		Date startTimestamp = new Date(calendar.getTimeInMillis());
		Date endTimestamp = null;

		switch (filterType) {
		case 1:// weekly
			calendar.add(Calendar.DATE, -7);
			endTimestamp = new Date(calendar.getTimeInMillis());
			break;
		case 2:// monthly
			calendar.add(Calendar.MONTH, -1);
			endTimestamp = new Date(calendar.getTimeInMillis());
			break;
		case 3:// date range
			startTimestamp = new Date(startDateTimestamp);
			endTimestamp =new Date(endDateTimestamp);
			break;

		default:
			break;
		}
		
		query = query + " and bidTime between ? and ?";
		Object[] queryParams = {userId,startTimestamp,endTimestamp};
		
		filteredOrdersBidedList = (List<OngoingBid>) find(query,queryParams);
		return filteredOrdersBidedList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> filterOrdersRegisteredByEsellerUser(Integer userId, Integer filterType, Long startDateTimestamp,
			Long endDateTimestamp) {
		List<Order> filteredOrdersRegisteredList = null;
		String query = null;
		query = "from Order where createBy=?";
	
	
	Calendar calendar = Calendar.getInstance();
	Date endTimestamp =  new Date(calendar.getTimeInMillis());
	Date startTimestamp = null;

	switch (filterType) {
	case 1:// weekly
		calendar.add(Calendar.DATE, -7);
		startTimestamp = new Date(calendar.getTimeInMillis());
		break;
	case 2:// monthly
		calendar.add(Calendar.MONTH, -1);
		startTimestamp = new Date(calendar.getTimeInMillis());
		break;
	case 3:// date range
		startTimestamp = new Date(startDateTimestamp);
		endTimestamp =new Date(endDateTimestamp);
		break;

	default:
		break;
	}
	
	query = query + " and orderDate between ? and ?";
	
	Object[] queryParams = {userId,startTimestamp,endTimestamp};
	filteredOrdersRegisteredList = (List<Order>) find(query,queryParams);
		return filteredOrdersRegisteredList;
	}
	
	
	
	
	@Override
	public Map<String, Object> getActiveEselLogis() {
		// TODO Auto-generated method stub

		Map<String, Object> m = new HashMap<String, Object>();
		SessionFactory sfactory = hibernateTemplate.getSessionFactory();
		Session session = sfactory.openSession();
		Transaction tx = null;
		try {
		
			tx = session.beginTransaction();

			Criteria critesseler = session.createCriteria(Company.class);
			critesseler.add(Restrictions.eq("type", 1));
			critesseler.add(Restrictions.eq("active", 1));
			
			List<Company> masterEsseler = critesseler.list();
			
			

			Criteria critlogistic = session.createCriteria(Company.class);
			critlogistic.add(Restrictions.eq("type", 2));
			critlogistic.add(Restrictions.eq("active", 1));
			List<Company> masterLogistic = critlogistic.list();
		
			m.put("esselerUserList", masterEsseler);
			m.put("logisticUserList", masterLogistic);

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}

		return m;
	}


	@Override
	public Map<String, Object> getEsselLogiskycStatus() {
		// TODO Auto-generated method stub
		Map<String, Object> m = new HashMap<String,Object>();
		SessionFactory sfactory = hibernateTemplate.getSessionFactory();
		Session session = sfactory.openSession();
		Transaction tx = null;
		try {
			
			
			tx = session.beginTransaction();

			Criteria criteKycComplete = session.createCriteria(Company.class);
			criteKycComplete.add(Restrictions.eq("kycFlag", "1"));
			criteKycComplete.add(Restrictions.ne("type",0 ));
			

			List<Company> masterKYcCompleted = criteKycComplete.list();
			
			

			Criteria critkycincomplt = session.createCriteria(Company.class);
			critkycincomplt.add(Restrictions.eq("kycFlag","0"));
			critkycincomplt.add(Restrictions.ne("type",0 ));
			List<Company> masterKYcIncompleted = critkycincomplt.list();
			m.put("masterKycCompleted", masterKYcCompleted);
			m.put("masterKycIncompleted", masterKYcIncompleted);

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return m;
	}


	@Override
	public Map<String, List<Company>> PendingKyc() {
		// TODO Auto-generated method stub
		
		Map<String, List<Company>> m = new HashMap<String, List<Company>>();
		SessionFactory sfactory = hibernateTemplate.getSessionFactory();
		Session session = sfactory.openSession();
		Transaction tx = null;
		try {
			
			
			tx = session.beginTransaction();

			Criteria criteKycComplete = session.createCriteria(Company.class);
			criteKycComplete.add(Restrictions.eq("kycFlag", "0"));
			

			List<Company> masterKYcCompleted = criteKycComplete.list();
			System.err.println("size of comapny pending"+masterKYcCompleted.size());
		   
			
		
			
			for(int i=0;i<masterKYcCompleted.size();i++)
			{
				
				Criteria kycdetails=session.createCriteria(KycInfo.class);
				
				kycdetails.add(Restrictions.eq("", masterKYcCompleted.get(i).getId()));
				System.err.println("=========");
				System.err.println("=========");
				
				List<KycInfo> kycinfo = kycdetails.list();
				System.err.println("=========");
				System.err.println(""+kycinfo.size());
				System.err.println("======="+kycinfo.size());
				System.err.println("======="+kycinfo.size());
				System.err.println("======="+kycinfo.size());
				
				
				
				
			}
			
			

			/*Criteria critkycincomplt = session.createCriteria(Company.class);
			critkycincomplt.add(Restrictions.eq("kycFlag","0"));
			List<Company> masterKYcIncomplted = critkycincomplt.list();
			
			System.err.println("Size of The Active Esseler"+masterKYcCompleted.size());
			System.err.println("Size of the Active Logistic"+masterKYcIncomplted.size());

			m.put("KYC_COM", masterKYcCompleted);
			m.put("KYC_INCOM", masterKYcIncomplted);
*/
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return m;
	}

	@Override
	public List<Order> getTotalRegisteredOrders() {
		List<Order> orders = new ArrayList<Order>();
		String query = "from Order order by id";
		
		orders = (List<Order>) find(query);
		if (orders != null && orders.size() > 0) {
			return orders;
		}
		return null;
	}


	@Override
	public List<OngoingBid> getTotalBidedOrders() {
		List<OngoingBid> bidedOrderList = null;
		String	query = "from OngoingBid order by id";
		bidedOrderList = (List<OngoingBid>) find(query);
		return bidedOrderList;
	}


	@Override
	public Map<String, Object> getEselLogisNameList() {
		Map<String,Object> mapEselLogisUsers = new HashMap<String,Object>();
		List<Object[]> eselNameList = new ArrayList<>();
		List<Object[]> logisNameList = new ArrayList<>();
		

		String eselQuery = "select c.id,c.name from Company c where  c.type=1";
		eselNameList = (List<Object[]>) find(eselQuery);

		String logisQuery = "select c.id,c.name from Company c  where  c.type=2";
		logisNameList = (List<Object[]>) find(logisQuery);
		mapEselLogisUsers.put("eselNames",eselNameList);
		mapEselLogisUsers.put("logisNames", logisNameList);
		return mapEselLogisUsers;
	}


	@Override
	public Map<String, Object> getUsernamelist() {
		// TODO Auto-generated method stub
		Map<String,Object> mapUsers = new HashMap<String,Object>();
		List<Object[]> eselUsernamelist = new ArrayList<>();
		

		String eselQuery = "select c.id,c.name from Company c where  c.type!=0";
		eselUsernamelist = (List<Object[]>) find(eselQuery);
		
		
		System.err.println("==========");
		
		System.err.println("======+====="+eselUsernamelist.size());
		System.err.println("====+====="+eselUsernamelist.size());
		System.err.println("======+====="+eselUsernamelist.size());
		System.err.println("=====+====="+eselUsernamelist.size());

		mapUsers.put("usernames",eselUsernamelist);
		return mapUsers;
	}


	@Override
	public void saveOrUpdateBroadcastingmeassage(Broad_casting_Message bmessage) {
		// TODO Auto-generated method stub
		
		
		
		updateEntity(bmessage);
		
	}


	@Override
	public Map<String, Object> getNotificationMessage(int type) {
		// TODO Auto-generated method stub
		
		
		
		System.err.println("Inside the dao layer value of type"+type);

		System.err.println("Inside the dao layer value of type"+type);
		System.err.println("Inside the dao layer value of type"+type);
		Map<String,Object> mapMessage = new HashMap<String,Object>();
		List<Object[]> Usernotification = new ArrayList<>();
		
        
       
        	
        	
        	System.err.println("inside the try block");
        	
        	
        	String eselQuery = "select c.Username,c.Bmessage,c.Last_Modified_Time from Broad_casting_Message c where  c.Company_Id="+type;
        	Usernotification = (List<Object[]>) find(eselQuery);
        	
        	
        	System.err.println("=Usernamelist==="+Usernotification.size());
        	//List<Broad_casting_Message> lust=criteria.list();
        	
		
		/*String eselQuery = "select c.Last_Modified_Time,c.Bmessage from Broad_casting_Message c where  c.Company_Id!="+type+"";
		Usernamelist = (List<Object[]>) find(eselQuery);*/
		
		System.err.println("=======size of the no of message==="+Usernotification.size());
		System.err.println("------messagege"+Usernotification.toString());
		

		mapMessage.put("userMessage",Usernotification);
		return mapMessage;
		
	}


	


	
	
	

}
