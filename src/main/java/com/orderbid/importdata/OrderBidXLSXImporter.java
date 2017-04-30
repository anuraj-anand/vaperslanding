package com.orderbid.importdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.orderbid.beans.User;
import com.orderbid.beans.vo.OrderVO;
import com.orderbid.service.OrderService;
import com.orderbid.service.impl.OrderServiceImpl;

public class OrderBidXLSXImporter extends OrderBidExcelImpoter {
	
	OrderService orderService;

	public OrderBidXLSXImporter() {
		// TODO Auto-generated constructor stub
	}

	public OrderBidXLSXImporter(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@Override
	public void importOrders(String fileName) {
		List<OrderVO> lstOrders = readXlsx(new File(fileName));
		if(orderService == null)
			orderService = new OrderServiceImpl();
		orderService.importOrders(lstOrders);
		
	}
	
	@Override
	public List<OrderVO> importOrders(InputStream stream, User user) {
		return readXlsx(stream);
	}
	
	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@Override
	public void importOrder() {
		// TODO Auto-generated method stub

	}

	@Override
	public void importUpdatedOrders() {
		// TODO Auto-generated method stub

	}
	
	public List<OrderVO> readXlsx(File inputFile) {
		List<OrderVO> lstOrders = null;
		// Get the workbook instance for XLSX file
		XSSFWorkbook wb = null;
		try {
			wb = new XSSFWorkbook(new FileInputStream(inputFile));
			// Get first sheet from the workbook
			XSSFSheet sheet = wb.getSheetAt(0);
			// Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			//for (Row row : sheet) {
				lstOrders = readExcelFormats(rowIterator);
			//}
			wb.cloneSheet(0);
			wb.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstOrders;
	}
	
	public List<OrderVO> readXlsx(InputStream inputFile) {
		List<OrderVO> lstOrders = null;
		// Get the workbook instance for XLSX file
		XSSFWorkbook wb = null;
		try {
			wb = new XSSFWorkbook(inputFile);
			// Get first sheet from the workbook
			XSSFSheet sheet = wb.getSheetAt(0);
			// Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			//for (Row row : sheet) {
				lstOrders = readExcelFormats(rowIterator);
			//}
			wb.cloneSheet(0);
			wb.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstOrders;
	}

	public static void main(String[] args) {
		// File inputFile = new File("input.xls");
		OrderBidXLSXImporter obj = new OrderBidXLSXImporter();
		File inputFile2 = new File("Book.xlsx");
		// readXls(inputFile);
		obj.readXlsx(inputFile2);
	}


}