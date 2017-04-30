package com.orderbid.jobs;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.orderbid.service.impl.BidServiceImpl;
import com.orderbid.util.ApplicationContextHolder;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Configurable
public class WinnerBidJob extends AbstarctOrderBidJob {

	public static final String COUNT = "count";
	private String name;

	@Override
	protected void executeInternal(JobExecutionContext ctx)
			throws JobExecutionException {
		JobDataMap dataMap = ctx.getJobDetail().getJobDataMap();
		int cnt = dataMap.getInt(COUNT);
		JobKey jobKey = ctx.getJobDetail().getKey();
		System.out.println(jobKey + ": " + name + ": " + cnt);
//		if(bidService == null)
//			bidService = ApplicationContextHolder.getContext().getBean(BidServiceImpl.class);
//		bidService.creatWiningBid();
//		bidService.updateCompletedBidStatus();
		cnt++;
		dataMap.put(COUNT, cnt);
		//TriggerUtil.triggerWiningBidForOrder();
		//ScheduledExcuteWnningService.scheduleWinnigBidThread();
	}

	public void setName(String name) {
		this.name = name;
	}

}
