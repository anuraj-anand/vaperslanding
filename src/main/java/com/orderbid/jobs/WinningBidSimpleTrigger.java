package com.orderbid.jobs;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import com.orderbid.service.OrderService;
import com.orderbid.service.impl.BidServiceImpl;
import com.orderbid.service.impl.OrderServiceImpl;
import com.orderbid.util.ApplicationContextHolder;
import com.orderbid.util.OrderStatus;

public class WinningBidSimpleTrigger extends AbstarctOrderBidJob implements Runnable {

	public static final String ORDER_NO = "orderNo";
	private String orderNo;
	
	public WinningBidSimpleTrigger(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String orderNo = dataMap.getString(ORDER_NO);
		
//		if(bidService == null)
//			bidService = ApplicationContextHolder.getContext().getBean(BidServiceImpl.class);
//		bidService.creatWiningBid(orderNo);
//		bidService.updateCompletedBidStatus();
		System.err.println("Order No : "+orderNo);
		
		
	}
	
	@Override
	public void run() {
		if(bidService == null)
			bidService = ApplicationContextHolder.getContext().getBean(BidServiceImpl.class);
		bidService.creatWiningBid(orderNo);
		bidService.updateCompletedBidStatus();
//		orderService.updateOrders();
		if(orderService == null)
			orderService = ApplicationContextHolder.getContext().getBean(OrderServiceImpl.class);
		orderService.updateOrder(orderNo, OrderStatus.COMPLETED);
		System.out.println("######## Order No : "+orderNo+" : Completed #########");
		
	}

}
