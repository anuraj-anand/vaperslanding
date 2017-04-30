package com.orderbid.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.orderbid.beans.BankDetails;
import com.orderbid.beans.DisplayDetails;
import com.orderbid.beans.KycInfo;
import com.orderbid.beans.PickupInfo;
import com.orderbid.beans.PrimaryContactInfo;
import com.orderbid.beans.User;
import com.orderbid.dao.KycDao;

@Repository("kycDao")
@Transactional
public class KycDaoImpl extends BaseDaoImpl implements KycDao {
	public void saveOrUpdatePickupInfo(PickupInfo pickup){
		updateEntity(pickup);
	}
	public void saveOrUpdatePrimaryInfo(PrimaryContactInfo primary){
		updateEntity(primary);
	}
	public void saveOrUpdateBankInfo(BankDetails bank){
		updateEntity(bank);
	}
	public void saveOrUpdateKycInfo(KycInfo kc){
		updateEntity(kc);
	}
	
	public void saveorupdatedisplayinfo(DisplayDetails display){
		updateEntity(display);
	}
	
	public DisplayDetails getDisplayDetails(int companyId){
		List<DisplayDetails> details = new ArrayList<DisplayDetails>();
		String query = "from DisplayDetails where company.id = ?";
		Object[] queryParam = { companyId };
		details = (List<DisplayDetails>) find(query, queryParam);
		if (details != null && details.size() > 0) {
			return details.get(0);
		}
		return null;
	}
	
	public PrimaryContactInfo getPrimaryInfo(int companyId){
		List<PrimaryContactInfo> details = new ArrayList<PrimaryContactInfo>();
		String query = "from PrimaryContactInfo where company.id = ?";
		Object[] queryParam = { companyId };
		details = (List<PrimaryContactInfo>) find(query, queryParam);
		if (details != null && details.size() > 0) {
			return details.get(0);
		}
		return null;
	}
	
	public PickupInfo getPickupInfo(int companyId){
		List<PickupInfo> details = new ArrayList<PickupInfo>();
		String query = "from PickupInfo where company.id = ?";
		Object[] queryParam = { companyId };
		details = (List<PickupInfo>) find(query, queryParam);
		if (details != null && details.size() > 0) {
			return details.get(0);
		}
		return null;
	}
	
	public BankDetails getBankInfo(int companyId){
		List<BankDetails> details = new ArrayList<BankDetails>();
		String query = "from BankDetails where company.id = ?";
		Object[] queryParam = { companyId };
		details = (List<BankDetails>) find(query, queryParam);
		if (details != null && details.size() > 0) {
			return details.get(0);
		}
		return null;
	}
	
	public KycInfo getKycInfo(int companyId){
		List<KycInfo> details = new ArrayList<KycInfo>();
		String query = "from KycInfo where company.id = ?";
		Object[] queryParam = { companyId };
		details = (List<KycInfo>) find(query, queryParam);
		if (details != null && details.size() > 0) {
			return details.get(0);
		}
		return null;
	}
	@Override
	public void saveOrUpdateProfilePics(User user) {
		// TODO Auto-generated method stub
		
		updateEntity(user);
		
		/*String hql = "UPDATE User set profilePicture = :"+user.getProfilePicture()+"WHERE userName = :"+user.getUserName()+"";
		Session session = hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query q=session.createQuery(hql);
		q.setParameter(1,user.getProfilePicture());
		q.setParameter(2, user.getUserName());*/
		/*Transaction tx=null;
		try
		{
			tx=session.beginTransaction();
			User userdata=(User) session.get(User.class,user.getId());
			userdata.setProfilePicture(user.getProfilePicture());
			session.update(userdata);
			tx.commit();
			System.err.println("after Successy=full update");
			System.err.println("after Successy=full update");
			System.err.println("after Successy=full update");
			session.close();
		}catch(Exception e)
		{
			
			
			e.printStackTrace();
		}
		*/
		
		/*System.err.println("userlistttttttt==="+user.toString());
		System.err.println("userlistttttttt==="+user.toString());
		System.err.println("userlistttttttt==="+user.toString());
		System.err.println("userlistttttttt==="+user.toString());
		System.err.println("userlistttttttt==="+user.toString());
		updateEntity(user);
*/	}
	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		String query = "from User where userName = ? ";
		Object[] queryParam = { username};
		users = (List<User>) find(query, queryParam);
		if (users != null && users.size() > 0) {
			return users.get(0);
		}
		return users.get(0);
				
	}
}
