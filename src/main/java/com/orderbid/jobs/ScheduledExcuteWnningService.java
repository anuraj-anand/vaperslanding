package com.orderbid.jobs;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExcuteWnningService {

	private static final ScheduledExecutorService scheduler =
		     Executors.newScheduledThreadPool(1);
	
	public static void scheduleWinnigBidThread(String orderNo){
		scheduler.schedule(new WinningBidSimpleTrigger(orderNo), 1, TimeUnit.MINUTES);
		
	}
}
