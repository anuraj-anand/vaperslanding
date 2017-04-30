package com.orderbid.dao.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.loader.custom.Return;
import org.springframework.stereotype.Repository;

import com.orderbid.beans.Bid;
import com.orderbid.beans.OngoingBid;
import com.orderbid.beans.Order;
import com.orderbid.beans.OrderFilter;
import com.orderbid.beans.OrderWatch;
import com.orderbid.beans.User;
import com.orderbid.beans.vo.OrderStatusVO;
import com.orderbid.beans.vo.OrderVO;
import com.orderbid.beans.vo.OrderWatchVO;
import com.orderbid.dao.OrderDao;
import com.orderbid.util.AppConfig;
import com.orderbid.util.ApplicationConstants;
import com.orderbid.util.BidStatus;
import com.orderbid.util.GridRangeData;
import com.orderbid.util.OrderStatus;
import com.orderbid.util.QueryPopulator;

@Repository("OrderDao")
@Transactional
public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {

	public final static String SELECT_ORDER_COLUMNS = " DISTINCT ODR.ID, ODR.UUID, ODR.ORDER_NO, ODR.DEST_ADDRESS, ODR.SOURCE_ADDRESS, ODR.ASK_QUANTITY, "
			+ "ODR.ORDER_DATE, ODR.DELIVERY_DATE, ODR.PRIORITY, ODR.STATUS, ODR.ADDITIONAL_FIELDS, "
			+ "ODR.LAST_MODIFIEDTIME, ODR.ORDER_BID_EXPIRYTIME, ODR.SOURCE_PIN, ODR.DEST_PIN, COM.ID AS COMPANYID, COM.NAME, "
			+ "COM.TYPE, ODR.ASK_RATE, ODR.WEIGHT, ODR.ITEM_SYMBOL, ODR.SHIPMENT_DATE ";
	public final static String SELECT_ORDER_COLUMNS_WITHOUT_COMPANY_COLUMNS = " DISTINCT ODR.ID, ODR.UUID, ODR.ORDER_NO, ODR.DEST_ADDRESS, ODR.SOURCE_ADDRESS, ODR.ASK_QUANTITY, "
			+ "ODR.ORDER_DATE, ODR.DELIVERY_DATE, ODR.PRIORITY, ODR.STATUS, ODR.ADDITIONAL_FIELDS, "
			+ "ODR.LAST_MODIFIEDTIME, ODR.ORDER_BID_EXPIRYTIME, ODR.SOURCE_PIN, ODR.DEST_PIN, ODR.ASK_RATE, ODR.WEIGHT, ODR.ITEM_SYMBOL, ODR.SHIPMENT_DATE ";

	public static String BID_PERCENT_TOLRENCE = " AND (ODR.ASK_RATE * ((100-"
			+ AppConfig.bidPercentTolarence + ")/100 )) <= TRF.CHARGE ";

	public final static String ROW_COUNT_1 = "select  SQL_CALC_FOUND_ROWS count_total.*,  (SELECT FOUND_ROWS() ) as total_count from ( ";
	public final static String ROW_COUNT_LIMIT_2 = ") count_total limit ";
	public final static String ROW_COUNT_2 = ") count_total ";
	public final static String SELECT_ROW_COUNT1 = ", ( SELECT COUNT( DISTINCT (ODR.ID)) ";
	public final static String SELECT_ROW_COUNT2 = " ) as total_count ";
	public final static String SELECT_ORDER_BY = " ORDER BY ODR.ID ";

	private static String getCompanyWatcherExclusionQuery(User user) {
		final String COMPANY_WATCHER_EXCLUSION = " AND ODR.ID NOT IN (SELECT ORDER_ID FROM WATCHES WHERE USER_ID IN ("
				+ " SELECT ID FROM USER usr  WHERE usr.ID = "
				+ user.getId()
				+ " AND usr.COMPANY_ID = " + user.getCompany().getId() + " ))";
		return COMPANY_WATCHER_EXCLUSION;
	}

	public final Boolean isCompanyWatcherExclusion = true;

	public OrderDaoImpl() {
	}

	public int createOrder(Order order, User user) {
		return (Integer) createEntity(order);
	}

	public int createOrderWatch(OrderWatch orderWatch) {
		return (Integer) createEntity(orderWatch);
	}

	@Override
	public void updateOrder(String orderNo, OrderStatus completed) {
		Order odr = new Order();
		odr.setOrderNo(orderNo);
		odr = getOrder(odr);
		odr.setStatus(completed.toString());
		updateEntity(odr);
	}

	@Override
	public int importOrders(List<Order> lstOrders, User user) {
		int count = 0;
		// TODO: This user will be populated from current session, Default O
		// means 'System'
		for (Order order : lstOrders) {
			if (order.getOrderNo() == null || order.getOrderNo().equals(""))
				continue;
			order.setCompany(user.getCompany());
			order.setCreateBy(user.getId());
			order.setUpdateBy(user.getId());
			order.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));
			order.setOrderDate(new Timestamp(System.currentTimeMillis()));
			createEntity(order);
			++count;
		}
		return count;
	}

	@Override
	public int importOrders(List<Order> lstOrders) {
		int count = 0;
		User usr = new User();
		// TODO: This user will be populated from current session, Default O
		// means 'System'
		usr.setId(1);
		for (Order order : lstOrders) {
			if (order.getOrderNo() == null || order.getOrderNo().equals(""))
				continue;

			order.setCreateBy(usr.getId());
			order.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));
			order.setOrderDate(new Timestamp(System.currentTimeMillis()));
			createEntity(order);
			++count;
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Order getOrder(Order odr) {
		List<Order> orders = new ArrayList<Order>();
		String query = "from Order where orderNo = ?";
		Object[] queryParam = { odr.getOrderNo() };
		orders = (List<Order>) find(query, queryParam);
		if (orders != null && orders.size() > 0) {
			return orders.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getAutoQulifyOrders(GridRangeData range, User user) {

		StringBuffer sql = new StringBuffer(" select ");
		sql.append(SELECT_ORDER_COLUMNS).append(SELECT_ROW_COUNT1);
		sql.append(" FROM ");
		sql.append("PLTORDER ODR, COMPANY COM, TARIFF TRF ");
		sql.append("where ODR.COMPANY_ID = COM.ID AND ODR.STATUS != 'C' ");
		// + "AND ODR.ORDER_BID_EXPIRYTIME > now() "
		sql.append("AND ODR.DEST_PIN = TRF.PINCODE  ");
		// sql.append( "AND TRF.RATE_CARD_ID = RATECD.ID ");
		sql.append(BID_PERCENT_TOLRENCE);
		if (isCompanyWatcherExclusion)
			sql.append(getCompanyWatcherExclusionQuery(user));
		sql.append(SELECT_ROW_COUNT2);
		sql.append(" FROM ");
		sql.append("PLTORDER ODR, COMPANY COM, TARIFF TRF ");
		sql.append("where ODR.COMPANY_ID = COM.ID AND ODR.STATUS != 'C' ");
		// + "AND ODR.ORDER_BID_EXPIRYTIME > now() "
		sql.append("AND ODR.DEST_PIN = TRF.PINCODE  ");
		// sql.append("AND TRF.RATE_CARD_ID = RATECD.ID ");
		sql.append(BID_PERCENT_TOLRENCE);
		if (isCompanyWatcherExclusion)
			sql.append(getCompanyWatcherExclusionQuery(user));
		if (range != null && range.getFilter() != null) {
			getFilterQuery(range.getFilter(), sql);
		}
		sql.append(SELECT_ORDER_BY);
		if (range != null && range.getFromRange() > 0
				&& range.getRangeDifference() > 0) {
			sql.append("LIMIT ");
			sql.append(range.getFromRange());
			sql.append(" , ");
			sql.append(range.getRangeDifference());
			sql.append(" ");
		}

		List<Object> result = getSQLQueryResult(sql.toString());
		return QueryPopulator.getOrderList(result, true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getAutQulifyOrders(User user) {

		String sql = " select " + SELECT_ORDER_COLUMNS + SELECT_ROW_COUNT1
				+ " FROM "
				+ "PLTORDER ODR, COMPANY COM, RATE_CARD RATECD, TARIFF TRF "
				+ "where ODR.COMPANY_ID = COM.ID "
				+ "AND  ODR.ORDER_BID_EXPIRYTIME > now() "
				+ "AND ODR.DEST_PIN = TRF.PINCODE  "
				+ "AND TRF.RATE_CARD_ID = RATECD.ID " + BID_PERCENT_TOLRENCE;
		if (isCompanyWatcherExclusion)
			sql += getCompanyWatcherExclusionQuery(user);
		sql += SELECT_ROW_COUNT2 + " FROM "
				+ "PLTORDER ODR, COMPANY COM, RATE_CARD RATECD, TARIFF TRF "
				+ "where ODR.COMPANY_ID = COM.ID "
				+ "AND  ODR.ORDER_BID_EXPIRYTIME > now() "
				+ "AND ODR.DEST_PIN = TRF.PINCODE  "
				+ "AND TRF.RATE_CARD_ID = RATECD.ID " + BID_PERCENT_TOLRENCE;
		if (isCompanyWatcherExclusion)
			sql += getCompanyWatcherExclusionQuery(user);
		sql += SELECT_ORDER_BY;

		List<Object> result = getSQLQueryResult(sql);
		return QueryPopulator.getOrderList(result, true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getRecetntlyAddedOrders(User user, GridRangeData range) {
		// String query =
		// "from Order where LAST_MODIFIEDTIME > (NOW() - INTERVAL 1 DAY) limit
		// ? , ? ";
		StringBuffer sql = new StringBuffer(" select ");
		sql.append(SELECT_ORDER_COLUMNS).append(SELECT_ROW_COUNT1);
		sql.append(" from PLTORDER ODR, COMPANY COM ");
		sql.append("where ODR.COMPANY_ID = COM.ID AND ODR.COMPANY_ID = ");
		sql.append(user.getCompany().getId());
		sql.append(" ");
		sql.append("AND LAST_MODIFIEDTIME > (NOW() - INTERVAL 200 DAY) ");
		if (isCompanyWatcherExclusion)
			sql.append(getCompanyWatcherExclusionQuery(user));
		sql.append(SELECT_ROW_COUNT2);
		sql.append(" from PLTORDER ODR, COMPANY COM ");
		sql.append("where ODR.COMPANY_ID = COM.ID AND ODR.COMPANY_ID = ");
		sql.append(user.getCompany().getId());
		sql.append(" ");
		sql.append("AND LAST_MODIFIEDTIME > (NOW() - INTERVAL 200 DAY) ");
		if (isCompanyWatcherExclusion)
			sql.append(getCompanyWatcherExclusionQuery(user));
		if (range.getFilter() != null) {
			getFilterQuery(range.getFilter(), sql);
		}
		sql.append(SELECT_ORDER_BY);
		sql.append("LIMIT ");
		sql.append(range.getFromRange());
		sql.append(" , ");
		sql.append(range.getRangeDifference());
		sql.append(" ");

		List<Object> result = getSQLQueryResult(sql.toString());
		return QueryPopulator.getOrderList(result, true);
	}

	@Override
	public List<Order> getRecetntlyAddedOrders(User user) {
		String sql = " select " + SELECT_ORDER_COLUMNS + SELECT_ROW_COUNT1
				+ " from PLTORDER ODR, COMPANY COM "
				+ "where ODR.COMPANY_ID = COM.ID AND ODR.COMPANY_ID = "
				+ user.getCompany().getId() + " "
				+ "AND LAST_MODIFIEDTIME > (NOW() - INTERVAL 200 DAY) ";
		if (isCompanyWatcherExclusion)
			sql += getCompanyWatcherExclusionQuery(user);
		sql += SELECT_ROW_COUNT2 + " from PLTORDER ODR, COMPANY COM "
				+ "where ODR.COMPANY_ID = COM.ID AND ODR.COMPANY_ID = "
				+ user.getCompany().getId() + " "
				+ "AND LAST_MODIFIEDTIME > (NOW() - INTERVAL 200 DAY) ";
		if (isCompanyWatcherExclusion)
			sql += getCompanyWatcherExclusionQuery(user);
		sql += SELECT_ORDER_BY;

		List<Object> result = getSQLQueryResult(sql);
		return QueryPopulator.getOrderList(result, true);
	}

	@Override
	public List<Order> getNewlyAddedOrders(User user) {
		String sql = "select " + SELECT_ORDER_COLUMNS + SELECT_ROW_COUNT1
				+ " from PLTORDER ODR, COMPANY COM "
				+ "where ODR.COMPANY_ID = COM.ID "
				+ "AND LAST_MODIFIEDTIME > (NOW() - INTERVAL 8 DAY) ";
		if (isCompanyWatcherExclusion)
			sql += getCompanyWatcherExclusionQuery(user);
		sql += SELECT_ROW_COUNT2 + " from PLTORDER ODR, COMPANY COM "
				+ "where ODR.COMPANY_ID = COM.ID "
				+ "AND LAST_MODIFIEDTIME > (NOW() - INTERVAL 8 DAY) ";
		if (isCompanyWatcherExclusion)
			sql += getCompanyWatcherExclusionQuery(user);
		sql += SELECT_ORDER_BY;
		List<Object> result = getSQLQueryResult(sql);
		return QueryPopulator.getOrderList(result, false);
	}

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<Order> getTotalUpalodedOrders(User user) {
	 * List<Order> orders = new ArrayList<Order>(); StringBuffer query = new
	 * StringBuffer( "from Order where status != 'E' ORDER BY id");
	 * if(user.getCompany().getType() == 1){ query.append(" and company = ?");
	 * Object[] queryParam = { user.getCompany()}; orders = (List<Order>)
	 * find(query.toString(), queryParam); }else{ orders = (List<Order>)
	 * find(query.toString()); } if (orders != null && orders.size() > 0) {
	 * return orders; } return null; }
	 */

	@Override
	public List<Order> getExpriedOrders(User user) {
		List<Order> orders = new ArrayList<Order>();
		String query = "from Order where status = '"
				+ OrderStatus.EXPRIED.getStatus()
				+ "'  and company = ? ORDER BY id";
		Object[] queryParam = { user.getCompany() };
		orders = (List<Order>) find(query, queryParam);
		if (orders != null && orders.size() > 0) {
			return orders;
		}
		return null;
	}

	@Override
	public List<Order> getEsselerOrder(User user) {
		// TODO Auto-generated method stub
		List<Order> orders = new ArrayList<Order>();
		String query = "from Order where company = ?";
		Object[] queryParam = { user.getCompany() };
		orders = (List<Order>) find(query, queryParam);
		if (orders != null && orders.size() > 0) {
			return orders;
		}
		return null;
	}

	@Override
	public List<Order> getDeliveredOrders(User user) {
		List<Order> orders = new ArrayList<Order>();
		String query = "from Order where status = '"
				+ OrderStatus.COMPLETED.getStatus()
				+ "' and updateBy = ? ORDER BY id";
		Object[] queryParam = { user.getId() != null ? user.getId() : 0 };
		orders = (List<Order>) find(query, queryParam);
		if (orders != null && orders.size() > 0) {
			return orders;
		}
		return null;
	}

	@Override
	public List<Order> getOpenOrders(User user) {
		List<Order> orders = new ArrayList<Order>();
		String query = "from Order where status = '"
				+ OrderStatus.ONGOING.getStatus()
				+ "' and updateBy = ? ORDER BY id";
		Object[] queryParam = { user.getId() != null ? user.getId() : 0 };
		orders = (List<Order>) find(query, queryParam);
		if (orders != null && orders.size() > 0) {
			return orders;
		}
		return null;
	}

	@Override
	public Integer getHotOrders(User user) {
		String sql = " select count(order_no) from BID where status = '"
				+ BidStatus.ONGOING.getStatus()
				+ "' group by `ORDER_NO` having count(order_no) > "
				+ ApplicationConstants.HOT_ORDER_CHECK + " ";
		List<Object> result = getSQLQueryResult(sql);
		if (result != null && result.size() > 0) {
			int count = ((BigInteger) result.get(0)).intValue();
			return count;
		}
		return 0;
	}

	@Override
	public Order getOrder(String orderNo) {
		String query = "from Order where orderNo = ?";
		Object[] queryParam = { orderNo };
		List<Order> orders = (List<Order>) find(query, queryParam);
		if (orders != null && orders.size() > 0) {
			return orders.get(0);
		}
		return null;
	}

	@Override
	public Order getOrderById(String Id) {
		String query = "from Order where id = ?";
		Object[] queryParam = { Integer.parseInt(Id) };
		List<Order> orders = (List<Order>) find(query, queryParam);
		if (orders != null && orders.size() > 0) {
			return orders.get(0);
		}
		return null;
	}

	@Override
	public List<OrderWatch> getWatches(User user, GridRangeData range) {
		String sql = " from OrderWatch where userId = " + user.getId();
		Query q = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery(sql);
		q.setFirstResult(range.getFromRange()); // modify this to adjust paging
		q.setMaxResults(range.getRange());
		List<OrderWatch> watches = (List<OrderWatch>) q.list();
		return watches;
	}

	@Override
	public void addWatch(int[] selectedOrders, User user) {
		for (int odr : selectedOrders) {
			OrderWatch odrWatch = new OrderWatch(null, odr + "", user.getId());
			if (!checkWatch(odr, user.getId())) {
				createOrderWatch(odrWatch);
			}
		}

	}

	public boolean checkWatch(int orderId, int userId) {
		try {
			String sql = " from OrderWatch where userId = ? and orderNo =?";
			Object[] queryParam = { userId, orderId + "" };
			List<OrderWatch> watches = (List<OrderWatch>) find(sql, queryParam);
			if (watches != null && watches.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Order> getOrderByZipCode(String zipcode) {

		List<Order> list = null;
		Transaction tx = null;
		try {
			SessionFactory sfactory = hibernateTemplate.getSessionFactory();
			Session session = sfactory.openSession();

			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Order.class);
			Criterion zipcodeCon = Restrictions.eq("destPin", 560090);
			crit.add(zipcodeCon);
			list = crit.list();
			System.out.println("sizeeeeeeee" + list.size());
			System.err.println("Sizeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
					+ list.size());
			System.err.println("Sizeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
					+ list.size());
			System.err.println("ssssssss" + list.get(0).getDestPin()
					+ "dddddddddd" + list.get(1).getDestPin());

			tx.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public List<Order> getTotalUpalodedOrders(User user) {
		List<Order> orders = new ArrayList<Order>();
		StringBuffer query = new StringBuffer("from Order ");
		if (user.getCompany().getType() == 1) {
			query.append(" where createBy = " + user.getId() + " ORDER BY id ");
		} else if (user.getCompany().getType() == 2
				|| user.getCompany().getType() == 3) {
			query.append(" ORDER BY id ");
		}
		orders = (List<Order>) find(query.toString());
		if (orders != null && orders.size() > 0) {
			return orders;
		}
		return null;
	}

	public String getFilterQuery(OrderFilter filter, StringBuffer sql) {
		if (filter.getFromZip() > 0) {
			sql.append(" and ODR.SOURCE_PIN = ").append(filter.getFromZip());
		}
		if (filter.getToZip() > 0) {
			sql.append(" and ODR.DEST_PIN = ").append(filter.getToZip());
		}
		if (filter.getOrderNo() != null && filter.getOrderNo().length() > 0) {
			sql.append(" and ODR.ORDER_NO = '").append(filter.getOrderNo())
					.append("'");
		}
		if (filter.getProductName() != null
				&& filter.getProductName().length() > 0) {
			sql.append(" and ODR.ITEM_SYMBOL = '")
					.append(filter.getProductName()).append("'");
		}
		if (filter.getShippingPriority() != null
				&& filter.getShippingPriority().length() > 0) {
			sql.append(" and ODR.PRIORITY = '")
					.append(filter.getShippingPriority()).append("'");
		}
		if (filter.getHighestAskPrice() > 0 && filter.getLowestAskPrice() > 0) {
			sql.append(" and ODR.ASK_RATE <= ")
					.append(filter.getHighestAskPrice())
					.append(" and ODR.ASK_RATE >= ")
					.append(filter.getLowestAskPrice());
		}
		return null;
	}

	public List<Order> getClosedOrders(User user, GridRangeData rangeData) {
		List<Order> orders = new ArrayList<Order>();
		String query = null;
		if (user.getCompany().getType() == ApplicationConstants.TYPE_USER_LOGISTICS) {
			query = "SELECT "
					+ SELECT_ORDER_COLUMNS_WITHOUT_COMPANY_COLUMNS
					+ " FROM PLTORDER ODR left join WINNERBID WB on WB.ORDER_NO = ODR.ORDER_NO where ODR.STATUS = '"
					+ OrderStatus.COMPLETED.getStatus() + "' AND WB.USER = "
					+ user.getId();
			// query = "from Order as order where order.status = '"
			// + OrderStatus.COMPLETED.getStatus() + "' ORDER BY id";
		} else {
			query = SELECT_ORDER_COLUMNS_WITHOUT_COMPANY_COLUMNS
					+ " FROM PLTORDER ODR left join WINNERBID WB on WB.ORDER_NO = ODR.ORDER_NO  where ODR.STATUS = '"
					+ OrderStatus.COMPLETED.getStatus()
					+ "'  and ODR.COMPANY_ID = " + user.getCompany().getId()
					+ " ORDER BY id";
		}
		List<Object> result = getSQLQueryResult(query.toString());
		return QueryPopulator.getOrderListWithoutCompany(result, true);
	}

	@Override
	public Order getOrderDetailsByNo(String orderNo) {
		List<Order> orderDetails = new ArrayList<Order>();
		String hql = "from Order where orderNo=?";
		orderDetails = (List<Order>) find(hql, new Object[] { orderNo });
		return orderDetails.get(0);
	}

	@Override
	public Boolean validateOrderNo(String orderNo, String userId) {

		String hql = "from OngoingBid where orderNo=? and bidderId=?";
		List<Bid> bidOrderNo = new ArrayList<Bid>();
		bidOrderNo = (List<Bid>) find(hql, new Object[] { orderNo, userId });
		if (bidOrderNo.size() > 0) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}

	}

	@Override
	public List<com.orderbid.beans.OrderStatus> getOrderStatusList() {
		String hql = "from OrderStatus";
		List<com.orderbid.beans.OrderStatus> orderStatusList = new ArrayList<com.orderbid.beans.OrderStatus>();
		orderStatusList = (List<com.orderbid.beans.OrderStatus>) find(hql);
		return orderStatusList;
	}

	@Override
	public Boolean updateOrderStatus(String orderNo, Integer orderStatus) {
		String hql = "update Order set orderStatus=? where orderNo=?";
		Session session = hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter(0, orderStatus);
		query.setParameter(1, orderNo);
		int result = query.executeUpdate();
		if (result > 0) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public List<Order> getAdminOrder() {
		// TODO Auto-generated method stub
		List<Order> orders = new ArrayList<Order>();
		String query = "from Order";

		orders = (List<Order>) find(query);
		if (orders != null && orders.size() > 0) {
			return orders;
		}
		return null;

	}

	@Override
	public List<Order> getCanelledOrder() {
		// TODO Auto-generated method stub

		List<OngoingBid> filteredOrdersBidedList = null;
		String query = null;
		query = "from OngoingBid";

		filteredOrdersBidedList = (List<OngoingBid>) find(query);
		System.err.println("=========inside the dao ===+"+filteredOrdersBidedList.size());
		System.err.println("=========inside the dao ===+"+filteredOrdersBidedList.size());
		System.err.println("=========inside the dao ===+"+filteredOrdersBidedList.size());
		
		List<Order> list = null;
		int count=0;
		for(int i=0;i<filteredOrdersBidedList.size();i++)
		{
			
			Transaction tx = null;
			try {
				
				SessionFactory sfactory = hibernateTemplate.getSessionFactory();
				Session session = sfactory.openSession();
				System.err.println("=-=========filteredOrdersBidedList.get(i).getOrderNo().trim()"+filteredOrdersBidedList.get(i).getOrderNo().trim());

				tx = session.beginTransaction();
				Criteria crit = session.createCriteria(Order.class);
				list = crit.list();
				for(int j=0;j<list.size();j++)
				{
					if(filteredOrdersBidedList.get(i).getOrderNo().equals(list.get(j).getOrderNo()))
							{
						count++;
							}
					
				}
				
				
				/*
				Criterion zipcodeCon = Restrictions.ne("orderNo", filteredOrdersBidedList.get(i).getOrderNo().trim());
				crit.add(zipcodeCon);
				list = crit.list();*/
				System.out.println("sizeeeeeeee" +count);
				System.err.println("Sizeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
						+ count);
				System.err.println("Sizeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
						+ count);
				
				tx.commit();
				session.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

			
			
		}
		
		System.err.println("==count"+count);
		return null;
	}

}
