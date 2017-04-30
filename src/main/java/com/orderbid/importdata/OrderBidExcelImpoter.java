package com.orderbid.importdata;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

import com.orderbid.beans.vo.OrderVO;
import com.orderbid.util.OrderStatus;

public abstract class OrderBidExcelImpoter implements OrderBidImport {

	public OrderBidExcelImpoter() {
		// TODO Auto-generated constructor stub
	}

	public List<OrderVO> readExcelFormats(Iterator<Row> rowIterator) {
		List<OrderVO> lstOdr = new ArrayList<OrderVO>();
		try {
			int rowCouner = 0;
			Row row;
			Cell cell;
			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				int cellCounter = 0;
				++rowCouner;
				if (rowCouner < 2)
					continue;

				// For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();

				OrderVO odr = new OrderVO();

				while (cellIterator.hasNext()) {
					cell = cellIterator.next();
					++cellCounter;
					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_BOOLEAN:
						// System.out.print("\t\t\t\t\t\t\t\t"+cell.getBooleanCellValue());
						setOrderData(cellCounter, cell.getBooleanCellValue(),
								odr);
						break;
					case Cell.CELL_TYPE_NUMERIC:
						// System.out.print("\t\t\t\t\t\t\t\t"+cell.getNumericCellValue());
						setOrderData(cellCounter, cell.getNumericCellValue(),
								odr);
						break;
					case Cell.CELL_TYPE_STRING:
						// System.out.print("\t\t\t\t\t\t\t\t"+cell.getStringCellValue());
						setOrderData(cellCounter, cell.getStringCellValue(),
								odr);
						break;
					case Cell.CELL_TYPE_BLANK:
						// System.out.print(" ");
						break;
					case Cell.CELL_TYPE_FORMULA:
						// System.out.print("\t\t\t\t\t\t\t\t"+cell.getNumericCellValue());
						setOrderData(cellCounter, cell.getNumericCellValue(),
								odr);
						// System.err.println(cell);
						break;
					default:
						System.out.println(cell);
					}
				}
				if (odr.getOrderNo() == null)
					continue;
				odr.setStatus(OrderStatus.ONGOING.getStatus());
				lstOdr.add(odr);
				System.err.println(odr);

			}
		} catch (Exception e) {
			System.err.println("Exception :" + e.getMessage());
			e.printStackTrace();
		}
		return lstOdr;
	}

	public OrderVO setOrderData(int key, Object data, OrderVO odr) {
		// ID-UUID-ORDER_NO-DESTINATION-SOURCE-QUANTITY-ORDER_DATE-DELIVERY_DATE-PRIORITY-STATUS-ADDITIONAL_FIELDS
		java.util.Calendar cal = null;
		try {
			switch (key) {
			case 1:
				odr.setItemSymbol(data.toString());
//				odr.setUuid(data.toString());
				break;
			case 2:
				odr.setOrderNo(data.toString());
				break;
			case 3:
				//odr.setSourceAddress(data.toString());
				if(data instanceof Double){
					cal = DateUtil.getJavaCalendar((Double)data);
					odr.setShipmentDate(cal.getTime());
				}else
					odr.setShipmentDate(new Date(Long.parseLong(data.toString())));
				break;
			case 4:	
				//odr.setSourcePin((int)Double.parseDouble(data.toString()));
				if(data instanceof Double){
					cal = DateUtil.getJavaCalendar((Double)data);
					odr.setDeliveryDate(cal.getTime());
				}else
					odr.setDeliveryDate(new Date(Long.parseLong(data.toString())));
				break;
			case 5:
				//odr.setDestAddress(data.toString());
				odr.setWeight((long)Double.parseDouble(data.toString()));
				break;
			case 6:
				//odr.setDestPin((int)Double.parseDouble(data.toString()));
				odr.setSourcePin((int)Double.parseDouble(data.toString()));
				break;
			case 7:
				/*if(data instanceof Double){
					cal = DateUtil.getJavaCalendar((Double)data);
					odr.setDeliveryDate(cal.getTime());
				}else
					odr.setDeliveryDate(new Date(Long.parseLong(data.toString())));*/
				odr.setDestPin((int)Double.parseDouble(data.toString()));
				break;
			case 8:
				if(data instanceof Double){
					odr.setAskQuantity((int)Double.parseDouble(data.toString()));
				}else
					
					odr.setAskQuantity(Integer.parseInt(data.toString()));
				break;
			case 9:
				odr.setAskRate(new BigDecimal(data.toString()));
				break;
			case 10:
				//odr.setPriority(data.toString());
				odr.setUuid(data.toString());
				break;
			case 11:
				//odr.setAskQuantity(Integer.parseInt(data.toString()));
				odr.setDestAddress(data.toString());
				break;
			case 12:
				//odr.setWeight((long)Double.parseDouble(data.toString()));
				odr.setSourceAddress(data.toString());
				break;
			case 13:
				//odr.setAdditionalFields(data.toString());
				odr.setPriority(data.toString());
				break;
			case 14:
				//odr.setAdditionalFields(data.toString());
				odr.setStatus(data.toString());
				break;
			case 15:
				odr.setAdditionalFields(data.toString());
				break;	
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return odr;
	}


}
