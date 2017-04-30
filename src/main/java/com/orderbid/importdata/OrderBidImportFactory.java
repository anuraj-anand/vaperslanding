package com.orderbid.importdata;


public class OrderBidImportFactory {

	public OrderBidImportFactory() {
		// TODO Auto-generated constructor stub
	}

	public static OrderBidImport getOrderBidImporter(String fileExtention){
		switch (fileExtention.toLowerCase()) {
		case "csv":
			return new OrderBidCSVImporter();
		case "xls":
			return new OrderBidXLSImporter();
		case "xlsx":
			return new OrderBidXLSXImporter();
			//return new OrderBidAppContext().getContext().getBean(OrderBidXLSXImporter.class);
		default:
			return null;
		}
	}
}