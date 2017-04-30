package com.orderbid.service;

import java.util.List;
import java.util.Map;

import com.orderbid.beans.Broad_casting_Message;
import com.orderbid.beans.Company;
import com.orderbid.beans.KycInfo;
import com.orderbid.beans.OngoingBid;
import com.orderbid.beans.Order;
import com.orderbid.beans.User;
import com.orderbid.beans.vo.BidVO;
import com.orderbid.beans.vo.CompanyVO;
import com.orderbid.beans.vo.OrderVO;

public interface AdminService {
	Map<String, Object> getEselLogPendingApproval();

	Map<String, Object> getActiveEselLogis();
	Map<String, Object> getEsselLogiskycStatus();
	Map<String,List<Company>> PendingKyc();


	List<CompanyVO> getEselLogisUserList();

	List<CompanyVO> filterRegisEselLogisUsers(Integer userType, Integer filterType, Integer startTimestamp,
			Integer endTimestamp);
	
	List<BidVO> getTotalBidedOrders();


	List<BidVO> filterOrdersByLogisticUser(String userId, Integer filterType, Long startTimestamp,
			Long endTimestamp);
	List<OrderVO> filterOrdersRegisteredByEsellerUser(Integer userId, Integer filterType, Long startDateTimestamp,
			Long endDateTimestamp);
	
	List<OrderVO> getTotalRegisteredOrders();
	
	Map<String,Object> getEselLogisNameList();
	Map<String,Object> getUsernamelist();
	Map<String,Object> getNotificationMessage(int type);
	
	public void saveOrUpdateBroadcastingmessage(Broad_casting_Message kc);
	
	
}
