package com.orderbid.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderbid.beans.Bid;
import com.orderbid.beans.BiddingSession;
import com.orderbid.beans.OngoingBid;
import com.orderbid.beans.Order;
import com.orderbid.beans.User;
import com.orderbid.beans.WinnerBid;
import com.orderbid.beans.vo.BidVO;
import com.orderbid.beans.vo.OrderVO;
import com.orderbid.dao.BidDao;
import com.orderbid.dao.impl.BidDaoImpl;
import com.orderbid.service.BidService;
import com.orderbid.util.BeansVOConverter;
import com.orderbid.util.BidStatus;

@Service
public class BidServiceImpl implements BidService {

	@Autowired
	BidDao bidDao;
	
	public BidServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getAvaibleBidOrders() {
		// TODO Auto-generated method stub

	}

	@Override
	public void placeOrderBid(Order odr, Bid bid) {
		bidDao.createEntity(bid);
	}

	@Override
	public List<Bid> findWiningBid(){
		return bidDao.findWiningBid();
	}
	
	@Override
	public Bid findWiningBid(String orderNo){
		return bidDao.findWiningBid(orderNo);
	}
	
	@Override
	public void removeOngongBids(List<OngoingBid> lstBids){
		for (OngoingBid ongoingBid : lstBids) {
			bidDao.removeOngongBid(ongoingBid);
		}
		
	}
	
	@Override
	public void creatWiningBid(){
		if(bidDao == null)
			bidDao = new BidDaoImpl();
		List<Bid> winingBidLst= findWiningBid();
		List<WinnerBid> winingBidLsts = BeansVOConverter.getWinnerBidList(winingBidLst);
		for (Iterator<WinnerBid> iterator = winingBidLsts.iterator(); iterator.hasNext();) {
			WinnerBid winnerBid = iterator.next();
			winnerBid.setBidStatus(BidStatus.WINNER.getStatus());
			System.err.println("Winner Bid toString() : "+BidStatus.WINNER);
			bidDao.createEntity(winnerBid);
		}
		removeOngongBids(BeansVOConverter.getOngoingBid(winingBidLst));
	}
	
	@Override
	public List<Bid> bidHistory(User user) {
		return bidDao.bidHistory(user);
	}
	
	@Override
	public List<Bid> bidHistory(String orderNo) {
		return bidDao.bidHistory(orderNo);
	}

	@Override
	public void updateBid() {
		// TODO Auto-generated method stub

	}

	@Override
	public void bidHistory() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public OrderVO getOrderVOWithMaxBids(OrderVO orderVO, User user){
		orderVO = bidDao.getOrderVOWithMaxBids(orderVO, user);
		return orderVO;
	}
	
	@Override
	public List<OrderVO> getOrderVOWithMaxBids(List<OrderVO> lstOrderVO, User user){
		for (OrderVO orderVO : lstOrderVO) {
			orderVO = bidDao.getOrderVOWithMaxBids(orderVO, user);
		}
		return lstOrderVO;
	}
	
	public List<OrderVO> getOrderVOWithWinnerBids(List<OrderVO> lstOrderVO, User user){
		for (OrderVO orderVO : lstOrderVO) {
			orderVO = bidDao.getOrderVOWithWinnerBids(orderVO, user);
		}
		return lstOrderVO;
	}

	public List<OrderVO> getOrderWinningCompany(List<OrderVO> lstOrderVO, User user){
		for (OrderVO orderVO : lstOrderVO) {
			orderVO = bidDao.getOrderWinningCompany(orderVO, user);
		}
		return lstOrderVO;
	}
	
	@Override
	public void updateCompletedBidStatus() {
		bidDao.updateCompletedBidStatus();
	}

	@Override
	public List<BiddingSession> getBiddingSessions(User user){
		return bidDao.getBiddingSessions(user);
	}
	
	@Override
	public void addBiddingSession(BiddingSession biddingSession, User user){
		bidDao.addBiddingSession(biddingSession, user);
	}
	
	@Override
	public void deleteBiddingSession(BiddingSession biddingSession, User user){
		bidDao.deleteBiddingSession(biddingSession, user);
	}

	@Override
	public void creatWiningBid(String orderNo) {
		if(bidDao == null)
			bidDao = new BidDaoImpl();
		Bid winingBid= findWiningBid(orderNo);
		WinnerBid winingBidLsts = BeansVOConverter.getWinnerBid(winingBid);
		winingBidLsts.setBidStatus(BidStatus.WINNER.getStatus());
		
		System.err.println("Winner Bid toString() : "+BidStatus.WINNER);
		bidDao.createEntity(winingBidLsts);
		removeOngongBids(BeansVOConverter.getOngoingBid(winingBidLsts));
	}
	
	@Override
	public void removeOngongBids(OngoingBid bid){
		bidDao.removeOngongBid(bid);
	}

	@Override
	public List<Bid> ongoingBidHistory(User user) {
		// TODO Auto-generated method stub
		return bidDao.ongoingBidHistory(user);
	}

	@Override
	public List<OngoingBid> getLogisticOrder(User user) {
		// TODO Auto-generated method stub
		return bidDao.getLogisticOrder(user);
	}

	@Override
	public List<OngoingBid> getAdminBidOrder() {
		// TODO Auto-generated method stub
		return bidDao.getAdminBidOrder();
	}

	@Override
	public Boolean updateCancelStatus(String orderNo, String orderStatus) {
		// TODO Auto-generated method stub
		return bidDao.updateOrderStatus(orderNo, orderStatus);
	}

}
