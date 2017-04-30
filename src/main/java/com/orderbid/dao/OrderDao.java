package com.orderbid.dao;

import java.util.List;

import com.orderbid.beans.Order;
import com.orderbid.beans.OrderWatch;
import com.orderbid.beans.User;
import com.orderbid.beans.vo.OrderStatusVO;
import com.orderbid.beans.vo.OrderVO;
import com.orderbid.util.GridRangeData;
import com.orderbid.util.OrderStatus;

public interface OrderDao {
	int createOrder(Order order, User user);
	int importOrders(List<Order> lstOrders);
	Order getOrder(Order odr);
	public List<Order> getAutQulifyOrders(User user);
	List<Order> getAutoQulifyOrders(GridRangeData range, User user);
	List<Order> getTotalUpalodedOrders(User user);
	List<Order> getExpriedOrders(User user);
	
	List<Order> getEsselerOrder(User user);
	List<Order> getAdminOrder();
	List<Order> getRecetntlyAddedOrders(User user);
	
	int importOrders(List<Order> lstOrders, User user);
	List<Order> getDeliveredOrders(User user);
	
	List<Order> getCanelledOrder();
	
	List<Order> getOpenOrders(User user);
	Integer getHotOrders(User user);
	List<Order> getRecetntlyAddedOrders(User user, GridRangeData range);
	List<Order> getNewlyAddedOrders(User user);
	Order getOrder(String orderNo);
	Order getOrderById(String Id);
	List<OrderWatch> getWatches(User user, GridRangeData range);
	List<Order> getOrderByZipCode(String zipcode);
	void addWatch(int[] selectedOrders, User user);
	void updateOrder(String orderNo, OrderStatus completed);
	List<Order> getClosedOrders(User user, GridRangeData rangeData);
	Order getOrderDetailsByNo(String orderNo);
	Boolean validateOrderNo(String orderNo,String userId);
	List<com.orderbid.beans.OrderStatus> getOrderStatusList();
	Boolean updateOrderStatus(String orderNo,Integer orderStatus);
	


}