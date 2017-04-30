package com.orderbid.service;

import java.util.List;

import com.orderbid.beans.Bid;
import com.orderbid.beans.Order;

public interface EmailService {
	//List for Bid(Logistic)
		public List<Bid>getAllBIDformail(String Status);
	    //List for Order (Esseler)
		public List<Order>getAllOrderForMail(String OrderStatus);
}
