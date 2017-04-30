package com.orderbid.service;

import java.util.List;

import com.orderbid.beans.Bid;
import com.orderbid.beans.BiddingSession;
import com.orderbid.beans.OngoingBid;
import com.orderbid.beans.Order;
import com.orderbid.beans.User;
import com.orderbid.beans.vo.BidVO;
import com.orderbid.beans.vo.OrderVO;


public interface BidService {
	
	public void getAvaibleBidOrders();
	public void bidHistory();
	public void updateBid();
	void placeOrderBid(Order odr, Bid bid);
	List<Bid> findWiningBid();
	void creatWiningBid();
	List<Bid> bidHistory(User user);
	List<Bid> ongoingBidHistory(User user);
	OrderVO getOrderVOWithMaxBids(OrderVO orderVO, User user);
	List<OrderVO> getOrderVOWithMaxBids(List<OrderVO> lstOrderVO, User user);
	List<OrderVO> getOrderVOWithWinnerBids(List<OrderVO> lstOrderVO, User user);
	List<OrderVO> getOrderWinningCompany(List<OrderVO> lstOrderVO, User user);
	void removeOngongBids(List<OngoingBid> lstBids);
	void updateCompletedBidStatus();
	
	List<OngoingBid> getLogisticOrder(User user);
	List<OngoingBid> getAdminBidOrder();
	
	//Bidding sessions
	List<BiddingSession> getBiddingSessions(User user);
	void addBiddingSession(BiddingSession biddingSession, User user);
	void deleteBiddingSession(BiddingSession biddingSession, User user);
	public void creatWiningBid(String orderNo);
	void removeOngongBids(OngoingBid bid);
	Bid findWiningBid(String orderNo);
	List<Bid> bidHistory(String orderNo);
	Boolean updateCancelStatus(String orderNo,String orderStatus);
	
}
