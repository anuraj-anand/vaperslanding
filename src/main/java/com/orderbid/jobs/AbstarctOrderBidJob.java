package com.orderbid.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.orderbid.service.BidService;
import com.orderbid.service.OrderService;

public abstract class AbstarctOrderBidJob extends QuartzJobBean  {
	
	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public BidService getBidService() {
		return bidService;
	}

	public void setBidService(BidService bidService) {
		this.bidService = bidService;
	}

	
	@Autowired
	protected OrderService orderService;
	
	@Autowired
	protected BidService bidService;
	
	

}
