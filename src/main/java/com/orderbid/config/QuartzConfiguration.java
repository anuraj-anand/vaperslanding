package com.orderbid.config;
  
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.orderbid.jobs.TriggerUtil;
import com.orderbid.jobs.WinnerBidJob;
import com.orderbid.jobs.WinningBidSimpleTrigger;
  
@Configuration 
@ComponentScan("com.orderbid.jobs") 
public class QuartzConfiguration {
	
	public static SchedulerFactoryBean scheduler;
	
	@Bean
	public JobDetailFactoryBean jobDetailFactoryBean(){ 	
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setJobClass(WinnerBidJob.class);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", "WinnerBId jobbeandetail");
		map.put(WinnerBidJob.COUNT, 1);
		factory.setJobDataAsMap(map);
		factory.setGroup("Bid_Group");
		factory.setName("WinnerBid_Job");
		return factory;
	}
	@Bean
	public CronTriggerFactoryBean cronTriggerFactoryBean(){
		CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
		stFactory.setJobDetail(jobDetailFactoryBean().getObject());
		stFactory.setStartDelay(1000);
		stFactory.setName("Trigger_WinnerBid_Job");
		stFactory.setGroup("Bid_Group");
		stFactory.setCronExpression("0 0/2 * 1/1 * ? *");//	0 0 0/6 1/1 * ? *
		return stFactory;
	}
	
	private static JobDetailFactoryBean jobDetailFactoryBean1(){ 	
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
	
	private static SimpleTriggerFactoryBean simpleWinningTrigger(){
		SimpleTriggerFactoryBean stFactory = new SimpleTriggerFactoryBean();
		
		stFactory.setJobDetail(jobDetailFactoryBean1().getObject());
	
		stFactory.setStartDelay(1000);
		stFactory.setBeanName("test");
		stFactory.setRepeatCount(1);
		//stFactory.setStartTime(Date.parse(System.currentTimeMillis());
		stFactory.setName("Trigger_WinnerBidOrderNo_Job");
		stFactory.setGroup("Bid_Group");
		
		return stFactory;
	}
	
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		if(scheduler == null)
			scheduler = new SchedulerFactoryBean();
		//scheduler.setTriggers(cronTriggerFactoryBean().getObject());
		//scheduler.setTriggers(simpleWinningTrigger().getObject());
		if(TriggerUtil.getScheduelr()==null){
			//TriggerUtil.setScheduelr(scheduler);
			//TriggerUtil.triggerWiningBidForOrder();
		}
		
		return scheduler;
	}
	
	
	
}  
 