package com.orderbid.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderbid.beans.Order;
import com.orderbid.beans.User;
import com.orderbid.beans.vo.OrderStatusVO;
import com.orderbid.beans.vo.OrderVO;
import com.orderbid.beans.vo.OrderWatchVO;
import com.orderbid.dao.OrderDao;
import com.orderbid.dao.impl.OrderDaoImpl;
import com.orderbid.service.OrderService;
import com.orderbid.util.BeansVOConverter;
import com.orderbid.util.GridRangeData;
import com.orderbid.util.OrderStatus;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao orderDao;
	
	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public OrderServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int createOrder(Order order, User user){
		return orderDao.createOrder(order, user);
	}
	
	@Override
	public List<OrderVO> getAutQulifyOrders(User user) {
		return BeansVOConverter.getOrderVOList(orderDao.getAutQulifyOrders(user));
	}

	@Override
	public List<OrderVO> getAutoQulifyOrders(User user, GridRangeData rangeData){
		return BeansVOConverter.getOrderVOList(orderDao.getAutoQulifyOrders(rangeData, user));
	}
	
	@Override
	public void importOrders(List<OrderVO> lstOrders) {
		if(orderDao == null)
			orderDao = new OrderDaoImpl();
		System.err.println(orderDao.importOrders(BeansVOConverter.getOrderList(lstOrders)));
	}
	
	@Override
	public void importOrders(List<OrderVO> lstOrders, User user) {
		if(orderDao == null)
			orderDao = new OrderDaoImpl();
		orderDao.importOrders(BeansVOConverter.getOrderList(lstOrders), user);
	}
	
	@Override
	public  OrderVO createOrder(OrderVO orderVO) {
		if(orderDao == null)
			orderDao = new OrderDaoImpl();
		return  BeansVOConverter.getOrderVO(orderDao.getOrder(BeansVOConverter.getOrder(orderVO)));
	}

	@Override
	public void exportOrders() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateOrders() {
		

	}

	@Override
	public void getOngoingBidingOrders() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getCompleteOrders(int frmRange, int toRange) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ordersListing() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<OrderVO> getRecentlyAddedOrders(GridRangeData range, User user) {
		return BeansVOConverter.getOrderVOList(orderDao.getRecetntlyAddedOrders(user, range));
	}

	@Override
	public List<OrderVO> getTotalUpalodedOrders(User user) {
		return BeansVOConverter.getOrderVOList(orderDao.getTotalUpalodedOrders(user));
	}

	@Override
	public List<OrderVO> getExpriedOrders(User user) {
		return BeansVOConverter.getOrderVOList(orderDao.getExpriedOrders(user));
	}
	
	@Override
	public List<OrderVO> getEsselerOrders(User user) {
		// TODO Auto-generated method stub
		return BeansVOConverter.getOrderVOList(orderDao.getEsselerOrder(user));
	}
	
	@Override
	public List<OrderVO> getCanelledOrder() {
		
		System.err.println("inisde the Service layetrrrrrrrr");
		System.err.println("inisde the Service layetrrrrrrrr");
		System.err.println("inisde the Service layetrrrrrrrr");
		System.err.println("inisde the Service layetrrrrrrrr");
		// TODO Auto-generated method stub
		return BeansVOConverter.getOrderVOList(orderDao.getCanelledOrder());
	}
	
	
	@Override
	public List<OrderVO> getAdminOrder() {
		// TODO Auto-generated method stub
		return BeansVOConverter.getOrderVOList(orderDao.getAdminOrder());
	}

	@Override
	public List<OrderVO> getRecentlyAddedOrders(User user) {
		return BeansVOConverter.getOrderVOList(orderDao.getRecetntlyAddedOrders(user));
	}

	@Override
	public List<OrderVO> getDeliveredOrders(User user) {
		return BeansVOConverter.getOrderVOList(orderDao.getDeliveredOrders(user));
	}
	
	@Override
	public List<OrderVO> getOpenOrders(User user) {
		return BeansVOConverter.getOrderVOList(orderDao.getOpenOrders(user));
	}
	
	@Override
	public Integer getHotOrders(User user) {
		return orderDao.getHotOrders(user);
	}
	
	public List<OrderVO> getNewlyAddedOrders(User user){
		return BeansVOConverter.getOrderVOList(orderDao.getNewlyAddedOrders(user));
	}
	
	public Order getOrder(String orderNo){
		return orderDao.getOrder(orderNo);
	}

	public Order getOrderById(String Id){
		return orderDao.getOrderById(Id);
	}
	
	@Override
	public List<OrderWatchVO> getWatches(User user, GridRangeData range) {
		return BeansVOConverter.getOrderWatchVOList(orderDao.getWatches(user, range));
	}

	@Override
	public void addWatch(int[] selectedOrders, User user) {
		// TODO Auto-generated method stub
		orderDao.addWatch(selectedOrders, user);
	}

	@Override
	public List<OrderVO> getOrderByZipcode(String zipcoe) {
		// TODO Auto-generated method stub
		return BeansVOConverter.getOrderVOList(orderDao.getOrderByZipCode(zipcoe));
	}
 
	@Override
	public void updateOrder(String orderNo, OrderStatus completed) {
		// TODO Auto-generated method stub
		orderDao.updateOrder(orderNo, completed);
	}
	
	@Override
	public List<OrderVO> getClosedOrders(User user, GridRangeData rangeData){
	 	return BeansVOConverter.getOrderVOList(orderDao.getClosedOrders(user, rangeData));
	}
	
	
	@Override
		public OrderVO getOrderDetailsByNo(String orderNo) {
			
			return BeansVOConverter.getOrderDetailsByNo(orderDao.getOrderDetailsByNo(orderNo));
		}

	@Override
	public Boolean validateOrderNo(String orderNo,String userId) {
		return orderDao.validateOrderNo(orderNo, userId);
	}
	
	@Override
	public List<OrderStatusVO> getOrderStatusList(){
		return BeansVOConverter.getOrderStatusList(orderDao.getOrderStatusList());
	}
	
	@Override
	public Boolean updateOrderStatus(String orderNo,Integer orderStatus){
		return orderDao.updateOrderStatus(orderNo, orderStatus);
	}

	

	

	

	
}