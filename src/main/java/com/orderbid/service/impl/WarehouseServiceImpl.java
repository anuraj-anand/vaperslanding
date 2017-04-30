package com.orderbid.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderbid.beans.Warehouse;
import com.orderbid.dao.WarehouseDao;
import com.orderbid.service.WarehouseService;

@Service
public class WarehouseServiceImpl implements WarehouseService {

	@Autowired
	WarehouseDao warehouseDao;
	
	public WarehouseServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Integer createEntity(Warehouse warehouse) {
		return  warehouseDao.createEntity(warehouse);
	}

	@Override
	public List<Warehouse> noOfRegisteredWarehouse() {
		return warehouseDao.noOfRegisteredWarehouse();
	}

}
