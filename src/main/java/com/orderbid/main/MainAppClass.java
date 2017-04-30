package com.orderbid.main;

import org.springframework.beans.factory.annotation.Autowired;

import com.orderbid.importdata.OrderBidImportFactory;
import com.orderbid.service.OrderService;

public class MainAppClass {

	@Autowired
	OrderService orderService;
	
	public MainAppClass() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			System.err.println("Test");
			new OrderBidImportFactory().getOrderBidImporter("xlsx").importOrders("Book.xlsx");
			
	}

}
