package com.orderbid.beans;

import java.util.List;

import com.orderbid.beans.vo.OrderVO;

public class OrderGrid {

	private int totalCount;
	
	private List<OrderVO> orders;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<OrderVO> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderVO> orders) {
		
		this.orders = orders;
		this.totalCount = (orders != null && orders.size() > 0) ? orders.get(0).getRowCount() : 0;
	}

	@Override
	public String toString() {
		return "OrderGrid [totalCount=" + totalCount + ", orders=" + orders
				+ "]";
	}
	
}
