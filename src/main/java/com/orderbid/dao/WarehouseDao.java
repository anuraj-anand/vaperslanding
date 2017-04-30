package com.orderbid.dao;

import java.util.List;

import com.orderbid.beans.Warehouse;

public interface WarehouseDao {
	public Integer createEntity(Warehouse warehouse);

	public List<Warehouse> noOfRegisteredWarehouse();
}