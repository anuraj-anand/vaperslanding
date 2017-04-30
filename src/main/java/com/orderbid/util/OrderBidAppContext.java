package com.orderbid.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class OrderBidAppContext implements ApplicationContextAware {

	ApplicationContext context;
	
	public OrderBidAppContext() {
		// TODO Auto-generated constructor stub
	}

	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.context = arg0;
	}

}
