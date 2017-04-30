package com.orderbid.service;

import com.orderbid.beans.BankDetails;
import com.orderbid.beans.DisplayDetails;
import com.orderbid.beans.KycInfo;
import com.orderbid.beans.PickupInfo;
import com.orderbid.beans.PrimaryContactInfo;
import com.orderbid.beans.User;

public interface KycServices {
	
	public void saveorupdatedisplayinfo(DisplayDetails display);
	public void saveOrUpdatePickupInfo(PickupInfo pickup);
	public void saveOrUpdatePrimaryInfo(PrimaryContactInfo primary);
	public void saveOrUpdateBankInfo(BankDetails bank);
	public void saveOrUpdateKycInfo(KycInfo kc);
	public void saveOrUpdateProfilePics(User userpics);
	public DisplayDetails getDisplayDetails(int companyId);
	public PrimaryContactInfo getPrimaryInfo(int companyId);
	public PickupInfo getPickupInfo(int companyId);
	public BankDetails getBankInfo(int companyId);
	public KycInfo getKycInfo(int companyId);
	public User getUser(String username);
	
}
