package com.orderbid.jobs;

import java.util.HashMap;
import java.util.Map;

import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.orderbid.config.QuartzConfiguration;

public class TriggerUtil {
	
	protected static SchedulerFactoryBean scheduler; 
	private static Scheduler scheduler2;
	
	{
		//Scheduler = new Sched
		
	}
	
	public static void creatWinningBidSimpleTrigger(){
		//SchedulerFactoryBean scheduler = QuartzConfiguration.scheduler;
		
		
		//scheduler.scheduleJob(job, trigger);
		
	}

	public static SchedulerFactoryBean getScheduelr() {
		return scheduler;
	}

	public static void setScheduelr(SchedulerFactoryBean scheduelr) {
		TriggerUtil.scheduler = scheduelr;
	}
	
	@Bean
	private static JobDetailFactoryBean jobDetailFactoryBean(){ 	
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setJobClass(WinningBidSimpleTrigger.class);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", "WinnerBId jobbeandetail");
		map.put(WinningBidSimpleTrigger.ORDER_NO, 19999);
		factory.setJobDataAsMap(map);
		factory.setGroup("Bid_Group");
		factory.setName("WinnerBid_Job1");
		return factory;
	}
	
	@Bean
	private static SimpleTriggerFactoryBean simpleWinningTrigger(){
		SimpleTriggerFactoryBean stFactory = new SimpleTriggerFactoryBean();
		
		stFactory.setJobDetail(jobDetailFactoryBean().getObject());
	
		//stFactory.setStartDelay(1000);
		stFactory.setName("Trigger_WinnerBidOrderNo_Job");
		stFactory.setGroup("Bid_Group");
		
		return stFactory;
	}
	
	
	public static void triggerWiningBidForOrder(){
		scheduler.setTriggers(simpleWinningTrigger().getObject());
	}
	
}
