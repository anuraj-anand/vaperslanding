package com.orderbid.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.orderbid.beans.Bid;
import com.orderbid.beans.Company;
import com.orderbid.beans.OngoingBid;
import com.orderbid.beans.Order;
import com.orderbid.beans.WinnerBid;
import com.orderbid.jobs.WinningBidSimpleTrigger;

public class QueryPopulator {

	/**
	 * ID, UUID, ORDER_NO, DESTINATION, SOURCE, QUANTITY, ORDER_DATE,
	 * DELIVERY_DATE, PRIORITY, STATUS, ADDITIONAL_FIELDS, LAST_MODIFIEDTIME,
	 * ORDER_BID_EXPIRYTIME, PINCODE
	 * 
	 * @return
	 */
	public static List<Order> getOrderList(List result, boolean isCountable) {
		List<Order> lstResult = new ArrayList<Order>();
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			int index = 0;
			Object[] orderData = (Object[]) iterator.next();
			Order order = new Order();
			if (orderData[index] != null) {
				order.setId((Integer) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setUuid((String) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setOrderNo((String) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setDestAddress((String) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setSourceAddress((String) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setAskQuantity((Integer) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setOrderDate((Timestamp) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setDeliveryDate((Timestamp) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setPriority((String) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setStatus((String) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setAdditionalFields((String) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setLastModifiedDate((Timestamp) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setOrdeBidExpiryTime((Timestamp) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setSourcePin((Integer) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setDestPin((Integer) orderData[index++]);
			} else {
				index++;
			}

			Company com = new Company();
			if (orderData[index] != null) {
				com.setId((Integer) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				com.setName((String) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				com.setType((Integer) orderData[index++]);
				order.setCompany(com);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setAskRate(BigDecimal.valueOf((Double) orderData[index++]));
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setWeight(((BigInteger) orderData[index++]).longValue());
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setItemSymbol((String) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setShipmentDate((Timestamp) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null && isCountable) {
				if (orderData[index] != null)
					order.setRowCount(((BigInteger) orderData[index++]).intValue());
			} else {
				index++;
			}
			lstResult.add(order);
		}

		return lstResult;
	}

	/**
	 * o.ID, o.BID_AMOUNT, o.BID_TIME, o.USER, o.ORDER_NO, o.STATUS
	 * 
	 * @param result
	 * @return
	 */
	public static List<Bid> getOrderBidList(List result) {
		List<Bid> lstResult = new ArrayList<Bid>();
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			Object[] orderData = (Object[]) iterator.next();
			Bid bid = new OngoingBid();
			bid.setId((Integer) orderData[0]);
			bid.setBidAmt((BigDecimal) orderData[1]);
			bid.setBidTime((Timestamp) orderData[2]);
			bid.setBidderId((String) orderData[3]);
			bid.setOrderNo((String) orderData[4]);
			bid.setBidStatus((String) orderData[5]);
			lstResult.add(bid);
		}

		return lstResult;
	}
	
	/**
	 * o.ID, o.BID_AMOUNT, o.BID_TIME, o.USER, o.ORDER_NO, o.STATUS
	 * 
	 * @param result
	 * @return
	 */
	public static Bid getOrderBid(List result) {
		List<Bid> lstResult = new ArrayList<Bid>();
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			Object[] orderData = (Object[]) iterator.next();
			Bid bid = new OngoingBid();
			bid.setId((Integer) orderData[0]);
			bid.setBidAmt((BigDecimal) orderData[1]);
			bid.setBidTime((Timestamp) orderData[2]);
			bid.setBidderId((String) orderData[3]);
			bid.setOrderNo((String) orderData[4]);
			bid.setBidStatus((String) orderData[5]);
			return bid;
		}
		return null;

		
	}

	public static List<Order> getOrderListWithoutCompany(List result, boolean isCountable) {
		List<Order> lstResult = new ArrayList<Order>();
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			int index = 0;
			Object[] orderData = (Object[]) iterator.next();
			Order order = new Order();
			if (orderData[index] != null) {
				order.setId((Integer) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setUuid((String) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setOrderNo((String) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setDestAddress((String) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setSourceAddress((String) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setAskQuantity((Integer) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setOrderDate((Timestamp) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setDeliveryDate((Timestamp) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setPriority((String) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setStatus((String) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setAdditionalFields((String) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setLastModifiedDate((Timestamp) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setOrdeBidExpiryTime((Timestamp) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setSourcePin((Integer) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setDestPin((Integer) orderData[index++]);
			} else {
				index++;
			}

			if (orderData[index] != null) {
				order.setAskRate(BigDecimal.valueOf((Double) orderData[index++]));
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setWeight(((BigInteger) orderData[index++]).longValue());
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setItemSymbol((String) orderData[index++]);
			} else {
				index++;
			}
			if (orderData[index] != null) {
				order.setShipmentDate((Timestamp) orderData[index++]);
			} else {
				index++;
			}
			/*if (orderData[index] != null && isCountable) {
				if (orderData[index] != null)
					order.setRowCount(((BigInteger) orderData[index++]).intValue());
			} else {
				index++;
			}*/
			lstResult.add(order);
		}

		return lstResult;
	}
}
