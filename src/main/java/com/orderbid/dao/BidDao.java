package com.orderbid.dao;

import java.util.List;

import com.orderbid.beans.Bid;
import com.orderbid.beans.BiddingSession;
import com.orderbid.beans.OngoingBid;
import com.orderbid.beans.Order;
import com.orderbid.beans.User;
import com.orderbid.beans.vo.OrderVO;

public interface BidDao {

	Integer createEntity(Bid orderBidDao);

	/**
	 * Find Wining Bids
	 * @return 
	 */
	List<Bid> findWiningBid();

	List<Bid> bidHistory(User user);

	List<Bid> ongoingBidHistory(User user);
	List<OngoingBid> getLogisticOrder(User user);
	List<OngoingBid> getAdminBidOrder();
	Boolean updateOrderStatus(String orderNo,String orderStatus);

	List<Bid> winingBidHistory(User user);

	void maxOngoingBidForOrder(OrderVO order);

	void maxOngoingUserBidForOrder(OrderVO Order, User user);

	OrderVO getOrderVOWithMaxBids(OrderVO Order, User user);
	OrderVO getOrderVOWithWinnerBids(OrderVO Order, User user);
	OrderVO getOrderWinningCompany(OrderVO orderVO, User user);
	void removeOngongBid(OngoingBid bid);

	void updateCompletedBidStatus();
	
	//BiddingSessionR
	List<BiddingSession> getBiddingSessions(User user);
	void addBiddingSession(BiddingSession biddingSession, User user);
	void deleteBiddingSession(BiddingSession biddingSession, User user);

	Bid findWiningBid(String orderNo);

	List<Bid> bidHistory(String orderNo);

	List<Bid> winingBidHistory(String orderNo);

	List<Bid> ongoingBidHistory(String orderNo);
}
