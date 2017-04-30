package com.orderbid.service;

import java.util.List;

import com.orderbid.beans.Warehouse;

public interface WarehouseService {
	public Integer createEntity(Warehouse warehouse);
	public List<Warehouse> noOfRegisteredWarehouse();
}
