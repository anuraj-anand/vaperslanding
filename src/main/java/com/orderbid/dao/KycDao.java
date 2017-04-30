package com.orderbid.dao;

import java.util.ArrayList;
import java.util.List;

import com.orderbid.beans.BankDetails;
import com.orderbid.beans.DisplayDetails;
import com.orderbid.beans.KycInfo;
import com.orderbid.beans.PickupInfo;
import com.orderbid.beans.PrimaryContactInfo;
import com.orderbid.beans.User;

public interface KycDao {
	
	public void saveOrUpdatePickupInfo(PickupInfo pickup);
	public void saveOrUpdatePrimaryInfo(PrimaryContactInfo primary);
	public void saveOrUpdateBankInfo(BankDetails bank);
	public void saveOrUpdateKycInfo(KycInfo kc);
	public void saveOrUpdateProfilePics(User user);
	public void saveorupdatedisplayinfo(DisplayDetails display);
	public DisplayDetails getDisplayDetails(int companyId);
	public PrimaryContactInfo getPrimaryInfo(int companyId);
	public PickupInfo getPickupInfo(int companyId);
	public BankDetails getBankInfo(int companyId);
	public KycInfo getKycInfo(int companyId);
	public User getUser(String username);
}
