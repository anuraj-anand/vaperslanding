package com.orderbid.importdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import com.orderbid.beans.User;
import com.orderbid.beans.vo.OrderVO;

public class OrderBidXLSImporter extends OrderBidExcelImpoter {

	public OrderBidXLSImporter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void importOrders(String fileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void importOrder() {
		// TODO Auto-generated method stub

	}

	@Override
	public void importUpdatedOrders() {
		// TODO Auto-generated method stub

	}
	
	public void readXls(File inputFile) {
		try {
			// Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(
					inputFile));
			// Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);
			// Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			//for (Row row : sheet) {
				readExcelFormats(rowIterator);	
			//}
			workbook.cloneSheet(0);
			workbook.close();
		}

		catch (FileNotFoundException e) {
			System.err.println("Exception" + e.getMessage());
		} catch (IOException e) {
			System.err.println("Exception" + e.getMessage());
		}
	}


	
	public static void main(String[] args) {
		// File inputFile = new File("input.xls");
		OrderBidXLSImporter obj = new OrderBidXLSImporter();
		File inputFile2 = new File("Book.xlsx");
		obj.readXls(inputFile2);
	}

	@Override
	public List<OrderVO> importOrders(InputStream stream, User user) {
		// TODO Auto-generated method stub
		return null;
	}
}