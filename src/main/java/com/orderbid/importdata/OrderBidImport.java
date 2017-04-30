package com.orderbid.importdata;

import java.io.InputStream;
import java.util.List;

import com.orderbid.beans.User;
import com.orderbid.beans.vo.OrderVO;

public interface OrderBidImport {

	public void importOrders(String fileName);
	public void importOrder();
	public void importUpdatedOrders();
	List<OrderVO> importOrders(InputStream stream, User user);
}