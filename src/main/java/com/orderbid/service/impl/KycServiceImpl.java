package com.orderbid.service.impl;

import org.elasticsearch.cluster.routing.allocation.RerouteExplanation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderbid.beans.BankDetails;
import com.orderbid.beans.DisplayDetails;
import com.orderbid.beans.KycInfo;
import com.orderbid.beans.PickupInfo;
import com.orderbid.beans.PrimaryContactInfo;
import com.orderbid.beans.User;
import com.orderbid.dao.KycDao;
import com.orderbid.service.KycServices;


@Service
public class KycServiceImpl implements KycServices {

	
	@Autowired
	private KycDao kycDao;
	
	@Override
	public void saveOrUpdatePickupInfo(PickupInfo pickup) {
		kycDao.saveOrUpdatePickupInfo(pickup);
	}
	
	@Override
	public void saveOrUpdatePrimaryInfo(PrimaryContactInfo primary) {
		kycDao.saveOrUpdatePrimaryInfo(primary);
	}
	
	@Override
	public void saveOrUpdateBankInfo(BankDetails bank) {
		kycDao.saveOrUpdateBankInfo(bank);
	}

	@Override
	public void saveOrUpdateKycInfo(KycInfo kc) {
		kycDao.saveOrUpdateKycInfo(kc);
	}
	
	@Override
	public void saveorupdatedisplayinfo(DisplayDetails display) {
		kycDao.saveorupdatedisplayinfo(display);
	}

	@Override
	public DisplayDetails getDisplayDetails(int companyId) {
		return kycDao.getDisplayDetails(companyId);
	}

	@Override
	public PrimaryContactInfo getPrimaryInfo(int companyId) {
		return kycDao.getPrimaryInfo(companyId);
	}

	@Override
	public PickupInfo getPickupInfo(int companyId) {
		return kycDao.getPickupInfo(companyId);
	}

	@Override
	public BankDetails getBankInfo(int companyId) {
		return kycDao.getBankInfo(companyId);
	}

	@Override
	public KycInfo getKycInfo(int companyId) {
		return kycDao.getKycInfo(companyId);
	}

	@Override
	public void saveOrUpdateProfilePics(User userpics) {
		// TODO Auto-generated method stub
		
		kycDao.saveOrUpdateProfilePics(userpics);
		
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return kycDao.getUser(username);
	}
	
}
