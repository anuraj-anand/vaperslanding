package com.orderbid.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderbid.beans.Bid;
import com.orderbid.beans.Order;
import com.orderbid.dao.EmailDao;
import com.orderbid.service.EmailService;
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired EmailDao emaildao;
	
	
	@Override
	public List<Bid> getAllBIDformail(String Status) {
		// TODO Auto-generated method stub
		return emaildao.getAllBIDformail(Status);
	}

	@Override
	public List<Order> getAllOrderForMail(String OrderStatus) {
		// TODO Auto-generated method stub
		return emaildao.getAllOrderForMail(OrderStatus);
	}

}
