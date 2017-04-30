package com.orderbid.dao;

import java.util.List;
import java.util.Map;

import com.orderbid.beans.Broad_casting_Message;
import com.orderbid.beans.Company;
import com.orderbid.beans.OngoingBid;
import com.orderbid.beans.Order;
import com.orderbid.beans.vo.OrderVO;

public interface AdminDao {
	Map<String, Object> getEselLogPendingApproval();

	Map<String, Object> getActiveEselLogis();

	Map<String, Object> getEsselLogiskycStatus();

	Map<String, List<Company>> PendingKyc();

	List<Company> getEselLogisUserList();

	List<Company> filterRegisEselLogisUsers(Integer userType, Integer filterType, Integer startTimestamp,
			Integer endTimestamp);
	
	List<OngoingBid> getTotalBidedOrders();

	List<OngoingBid> filterOrdersByLogisticUser(String userId, Integer filterType, Long startTimestamp,
			Long endTimestamp);

	List<Order> filterOrdersRegisteredByEsellerUser(Integer userId, Integer filterType, Long startDateTimestamp,
			Long endDateTimestamp);
	List<Order> getTotalRegisteredOrders();
	
	Map<String,Object> getEselLogisNameList();
	Map<String,Object> getUsernamelist();
	Map<String,Object> getNotificationMessage(int type);
	public void saveOrUpdateBroadcastingmeassage(Broad_casting_Message kc);

	
}
