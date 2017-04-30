package com.orderbid.service;

import java.util.List;

import com.orderbid.beans.Order;
import com.orderbid.beans.User;
import com.orderbid.beans.vo.OrderStatusVO;
import com.orderbid.beans.vo.OrderVO;
import com.orderbid.beans.vo.OrderWatchVO;
import com.orderbid.util.GridRangeData;
import com.orderbid.util.OrderStatus;

public interface OrderService {
		
	int createOrder(Order order, User user);
	
	public void importOrders(List<OrderVO> lstOrders);
	public void exportOrders();
	public void updateOrders();
	public void getOngoingBidingOrders();
	public void getCompleteOrders(int frmRange, int toRange);
	public void ordersListing();
	public OrderVO createOrder(OrderVO order);
	List<OrderVO> getAutQulifyOrders(User user);
	List<OrderVO> getAutoQulifyOrders(User user, GridRangeData rangeData);
	List<OrderVO> getRecentlyAddedOrders(User user);
	List<OrderVO> getTotalUpalodedOrders(User user);
	List<OrderVO> getExpriedOrders(User user);
	List<OrderVO> getEsselerOrders(User user);
	List<OrderVO> getCanelledOrder();
	List<OrderVO> getAdminOrder();
	List<OrderVO> getRecentlyAddedOrders(GridRangeData range, User user);
	void importOrders(List<OrderVO> lstOrders, User user);
	List<OrderVO> getDeliveredOrders(User user);
	List<OrderVO> getOpenOrders(User user);
	Integer getHotOrders(User user);
	List<OrderVO> getNewlyAddedOrders(User user);
	Order getOrder(String orderNo);
	Order getOrderById(String Id);
    List<OrderVO> getOrderByZipcode(String zipcoe);	
 
	List<OrderWatchVO> getWatches(User user, GridRangeData range);

	void addWatch(int[] selectedOrders, User user);

	void updateOrder(String orderNo, OrderStatus completed);
	
	List<OrderVO> getClosedOrders(User user, GridRangeData rangeData);
	OrderVO getOrderDetailsByNo(String orderNo);
	
	Boolean validateOrderNo(String orderNo,String userId);
	
	List<OrderStatusVO> getOrderStatusList();
	
	
	Boolean updateOrderStatus(String orderNo,Integer orderStatus);
} 
