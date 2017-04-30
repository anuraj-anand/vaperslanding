package com.orderbid.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.orderbid.beans.Bid;
import com.orderbid.beans.BiddingSession;
import com.orderbid.beans.Company;
import com.orderbid.beans.OngoingBid;
import com.orderbid.beans.Order;
import com.orderbid.beans.User;
import com.orderbid.beans.WinnerBid;
import com.orderbid.beans.vo.OrderVO;
import com.orderbid.dao.BidDao;
import com.orderbid.util.BeansVOConverter;
import com.orderbid.util.QueryPopulator;

@Repository("BidDao")
@Transactional
public class BidDaoImpl extends BaseDaoImpl implements BidDao {

	public BidDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Integer createEntity(Bid orderBidDao) {
		return (Integer) super.createEntity(orderBidDao);
	}

	/**
	 * This API find winning bid for each order provided no one can place same
	 * amount of bid
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Bid> findWiningBid() {

		String sql = "SELECT o.ID, o.BID_AMOUNT, o.BID_TIME, o.USER, o.ORDER_NO, o.STATUS FROM BID o "
				+ "LEFT JOIN BID b ON o.order_no = b.order_no AND o.bid_amount < b.bid_amount "
				+ "WHERE b.bid_amount is NULL "
				+ "and o.STATUS = 'A' "
				+ "AND o.order_no in (select ORDER_NO from PLTORDER where ORDER_BID_EXPIRYTIME <  NOW())"
				+ " group by o.order_no ";

		Query query = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql);
		List<Object> result = query.list();
		return QueryPopulator.getOrderBidList(result);
	}
	
	@Override
	public Bid findWiningBid(String orderNo) {
		String sql = "SELECT o.ID, o.BID_AMOUNT, o.BID_TIME, o.USER, o.ORDER_NO, o.STATUS FROM BID o "
				//+ "LEFT JOIN BID b ON o.order_no = b.order_no AND o.bid_amount < b.bid_amount "
				+ "WHERE o.bid_amount is not NULL "
				+ "and o.STATUS = 'A' "
				+ "AND o.order_no in ('"+orderNo+"')"
				+ " order by o.bid_amount asc limit 1 ";

		Query query = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql);
		List<Object> result = query.list();
		return QueryPopulator.getOrderBid(result);
	}

	@Override
	public List<Bid> bidHistory(User user) {
		List<Bid> lstBidHistory = new ArrayList<Bid>();
		List<Bid> lstOngoing = this.ongoingBidHistory(user);
		List<Bid> lstWining = this.winingBidHistory(user);
		boolean chck = (lstOngoing != null) ? lstBidHistory.addAll(lstOngoing)
				: false;
		chck = (lstWining != null) ? lstBidHistory.addAll(lstWining) : false;
		return null;
	}
	
	@Override
	public List<Bid> bidHistory(String orderNo) {
		List<Bid> lstBidHistory = new ArrayList<Bid>();
		List<Bid> lstOngoing = this.ongoingBidHistory(orderNo);
		List<Bid> lstWining = this.winingBidHistory(orderNo);
		boolean chck = (lstOngoing != null) ? lstBidHistory.addAll(lstOngoing)
				: false;
		chck = (lstWining != null) ? lstBidHistory.addAll(lstWining) : false;
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bid> ongoingBidHistory(User user) {
		List<Bid> ongoingBids = new ArrayList<Bid>();
		String query = "from BID where user = ? order by BID_TIME DESC";
		Object[] queryParam = { user.getId() };
		ongoingBids = (List<Bid>) find(query, queryParam);
		if (ongoingBids != null && ongoingBids.size() > 0) {
			
			System.err.println("----------inside the dao layer----"+ongoingBids.size());
			System.err.println("----------inside the dao layer----"+ongoingBids.size());
			System.err.println("----------inside the dao layer----"+ongoingBids.size());
			return ongoingBids;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bid> ongoingBidHistory(String orderNo) {
		List<Bid> ongoingBids = new ArrayList<Bid>();
		String query = "from BID where order_no = ? order by BID_TIME DESC";
		Object[] queryParam = { orderNo };
		ongoingBids = (List<Bid>) find(query, queryParam);
		if (ongoingBids != null && ongoingBids.size() > 0) {
			return ongoingBids;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void updateCompletedBidStatus() {
		String sql = "UPDATE BID SET STATUS = 'C' WHERE ORDER_NO IN (SELECT DISTINCT ORDER_NO FROM WINNERBID)";
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql);
		query.executeUpdate();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public void maxOngoingBidForOrder(OrderVO orderVO) {
		List<Bid> ongoingMaxBid = new ArrayList<Bid>();
		String sql = "select b.ID, b.BID_AMOUNT, b.BID_TIME, b.USER, b.ORDER_NO, b.STATUS from BID b where  "
				+ " b.BID_AMOUNT = (select min(bid_amount) as amt from BID where order_no ='"+orderVO.getOrderNo()+"') and order_no ='"+orderVO.getOrderNo()+"'";
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql);
		List<Object> result = query.list();
		if(result == null || result.size() == 0)
			return;
		ongoingMaxBid = QueryPopulator.getOrderBidList(result);
		orderVO.setMaxBidVO(BeansVOConverter.getBidVO(ongoingMaxBid.get(0)));
	}
	
	public void winningBidForOrder(OrderVO orderVo){
		Bid winnerBid = null;
		
		List<Bid> winningBids = new ArrayList<Bid>();
		String sql = "From WinnerBid where orderNo = ? ";
		Object[] queryParam = {  orderVo.getOrderNo()};
		winningBids = (List<Bid>) find(sql, queryParam);
		if (winningBids != null && winningBids.size() > 0) {
			winnerBid = winningBids.get(0);
		}
		orderVo.setWinnerBidVO(BeansVOConverter.getBidVO(winnerBid));
	}

	
	public void setWinningCompany(OrderVO orderVO){
		String sql = "From Company c JOIN c.users cu where cu.id = "+orderVO.getWinnerBidVO().getBidderId();
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(sql);
		List<Object> objects = (List<Object>)query.list();
		if(objects != null && objects.size() > 0){
			Company company = (Company)((Object[]) objects.get(0))[0];
			orderVO.setWinnerCompanyVO(BeansVOConverter.getCompanyVO(company));	
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void maxOngoingUserBidForOrder(OrderVO orderVO, User user) {
		List<Bid> ongoingMaxBid = new ArrayList<Bid>();
		String sql = "select b.ID, b.BID_AMOUNT, b.BID_TIME, b.USER, b.ORDER_NO, b.STATUS  from BID b "
				+" where b.BID_AMOUNT = (select min(bid_amount) as amt from BID where order_no ='"+orderVO.getOrderNo()+"' and user='"+user.getId()+"') "
						+ "and order_no ='"+orderVO.getOrderNo()+"' and user='"+user.getId()+"'";
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql);
		List<Object> result = query.list();
		if(result == null || result.size() == 0)
			return;
		ongoingMaxBid = QueryPopulator.getOrderBidList(result);
		orderVO.setMaxUserBidVO(BeansVOConverter.getBidVO(ongoingMaxBid.get(0)));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Bid> winingBidHistory(User user) {
		List<Bid> winingBids = new ArrayList<Bid>();
		String query = "from WINNERBID where user = ? order by BID_TIME DESC";
		winingBids = (List<Bid>) find(query);
		if (winingBids != null && winingBids.size() > 0) {
			return winingBids;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Bid> winingBidHistory(String orderNo) {
		List<Bid> winingBids = new ArrayList<Bid>();
		String query = "from WINNERBID where order_no = ? order by BID_TIME DESC";
		Object[] queryParam = { orderNo };
		winingBids = (List<Bid>) find(query, queryParam);
		
		if (winingBids != null && winingBids.size() > 0) {
			return winingBids;
		}
		return null;
	}

	@Override
	public OrderVO getOrderVOWithMaxBids(OrderVO orderVO, User user) {
		this.maxOngoingBidForOrder(orderVO);
		if(user.getCompany().getType() == 2){
			this.maxOngoingUserBidForOrder(orderVO, user);
		}
		return orderVO;
	}

	public OrderVO getOrderVOWithWinnerBids(OrderVO orderVO, User user){
		this.winningBidForOrder(orderVO);
		return orderVO;
	}
	
	public OrderVO getOrderWinningCompany(OrderVO orderVO, User user){
		this.setWinningCompany(orderVO);
		return orderVO;
	}
	
	@Override
	public void removeOngongBid(OngoingBid bid) {
		deleteEntity(bid);
	}

	@Override
	public List<BiddingSession> getBiddingSessions(User user){
		List<BiddingSession> sessions = new ArrayList<BiddingSession>();
		String query = "from BiddingSession";
		sessions = (List<BiddingSession>) find(query);
		if (sessions != null && sessions.size() > 0) {
			return sessions;
		}
		return null;
	}
	
	@Override
	public void addBiddingSession(BiddingSession biddingSession, User user){
		createEntity(biddingSession);
	}
	
	@Override
	public void deleteBiddingSession(BiddingSession biddingSession, User user){
		deleteEntity(biddingSession);
	}

	@Override
	public List<OngoingBid> getLogisticOrder(User user) {
		// TODO Auto-generated method stub
		
		String userId=""+user.getCompany().getId();
		List<OngoingBid> filteredOrdersBidedList = null;
		String query = null;
//			query = "from OngoingBid where bidderId="+userId+"";
			query = "from OngoingBid where bidderId=?";
			Object[] queryParams = {userId};
			
			filteredOrdersBidedList = (List<OngoingBid>) find(query,queryParams);
			return filteredOrdersBidedList;
			
		
		
	}

	@Override
	public List<OngoingBid> getAdminBidOrder() {
		// TODO Auto-generated method stub
		
		
		List<OngoingBid> filteredOrdersBidedList = null;
		String query = null;
//			query = "from OngoingBid where bidderId="+userId+"";
			query = "from OngoingBid";
			
			
			filteredOrdersBidedList = (List<OngoingBid>) find(query);
			return filteredOrdersBidedList;
	}

	@Override
	public Boolean updateOrderStatus(String orderNo, String orderStatus) {
		// TODO Auto-generated method stub
		String hql = "update OngoingBid set bidStatus=? where orderNo=?";
		Session session = hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter(0, orderNo);
		query.setParameter(1, orderStatus);
		int result = query.executeUpdate();
		if (result > 0) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
