package com.orderbid.dao;

import java.util.List;

import com.orderbid.beans.Bid;
import com.orderbid.beans.Order;

public interface EmailDao {
	//List for Bid(Logistic)
	public List<Bid>getAllBIDformail(String Status);
    //List for Order (Esseler)
	public List<Order>getAllOrderForMail(String OrderStatus);
	
	
}
