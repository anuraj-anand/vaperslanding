package com.orderbid.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.orderbid.beans.Broad_casting_Message;
import com.orderbid.beans.Company;
import com.orderbid.beans.OngoingBid;
import com.orderbid.beans.Order;
import com.orderbid.beans.User;
import com.orderbid.beans.vo.BidVO;
import com.orderbid.beans.vo.CompanyVO;
import com.orderbid.beans.vo.OrderVO;
import com.orderbid.dao.AdminDao;
import com.orderbid.service.AdminService;
import com.orderbid.util.BeansVOConverter;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDao adminDao;

	@Override
	public Map<String, Object> getEselLogPendingApproval() {
		return adminDao.getEselLogPendingApproval();
	}

	@Override
	public List<CompanyVO> getEselLogisUserList() {
		return BeansVOConverter.getEselLogisUserList(adminDao.getEselLogisUserList());
	}

	@Override
	public List<CompanyVO> filterRegisEselLogisUsers(Integer userType, Integer filterType, Integer startTimestamp,
			Integer endTimestamp) {
		// TODO Auto-generated method stub
		return BeansVOConverter.filterRegisEselLogisUsers(adminDao.filterRegisEselLogisUsers(userType, filterType, startTimestamp, endTimestamp));
	}

	@Override
	public List<BidVO> filterOrdersByLogisticUser(String userId, Integer filterType, Long startTimestamp,
			Long endTimestamp) {

		return BeansVOConverter.filterOrdersByLogisticUser(adminDao.filterOrdersByLogisticUser(userId, filterType, startTimestamp, endTimestamp));
	}

	@Override
	public List<OrderVO> filterOrdersRegisteredByEsellerUser(Integer userId, Integer filterType, Long startDateTimestamp,
			Long endDateTimestamp) {

		return BeansVOConverter.filterOrdersRegisteredByEsellerUser(adminDao.filterOrdersRegisteredByEsellerUser(userId, filterType, startDateTimestamp, endDateTimestamp));
	}

	@Override
	public Map<String, Object> getActiveEselLogis() {

		return BeansVOConverter.getActiveEselLogis(adminDao.getActiveEselLogis());
	}

	@Override
	public Map<String, Object> getEsselLogiskycStatus() {
		
		return BeansVOConverter.getEsselLogiskycStatus(adminDao.getEsselLogiskycStatus());
	}

	@Override
	public Map<String, List<Company>> PendingKyc() {
		// TODO Auto-generated method stub
		return adminDao.PendingKyc();
	}

	@Override
	public List<OrderVO> getTotalRegisteredOrders() {
		return BeansVOConverter.getOrderVOList(adminDao.getTotalRegisteredOrders());
	}

	@Override
	public List<BidVO> getTotalBidedOrders() {
		// TODO Auto-generated method stub
		return BeansVOConverter.getTotalBidedOrders(adminDao.getTotalBidedOrders());
	}

	@Override
	public Map<String, Object> getEselLogisNameList() {
		
		return BeansVOConverter.getEselLogisNameList(adminDao.getEselLogisNameList());
	}

	@Override
	public Map<String, Object> getUsernamelist() {
		// TODO Auto-generated method stub
		return BeansVOConverter.getUserNameList((adminDao.getUsernamelist()));
	}

	@Override
	public void saveOrUpdateBroadcastingmessage(Broad_casting_Message kc) {
		// TODO Auto-generated method stub
		adminDao.saveOrUpdateBroadcastingmeassage(kc);
	}

	@Override
	public Map<String, Object> getNotificationMessage(int type) {
		// TODO Auto-generated method stub
		System.err.println("inside the Service layer type"+type);
		System.err.println("inside the Service layer type"+type);
		System.err.println("inside the Service layer type"+type);
		
		return BeansVOConverter.getNotificationMessage(adminDao.getNotificationMessage(type));
		
	}

	
	
}
