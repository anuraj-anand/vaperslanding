package com.orderbid.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.orderbid.beans.Order;
import com.orderbid.beans.Warehouse;
import com.orderbid.dao.WarehouseDao;

@Repository("WarehouseDao")
@Transactional
public class WarehouseDaoImpl extends BaseDaoImpl implements WarehouseDao {

	public WarehouseDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Integer createEntity(Warehouse warehouse) {
		return (Integer) super.createEntity(warehouse);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Warehouse> noOfRegisteredWarehouse() {
		List<Warehouse> wareHouse = new ArrayList<Warehouse>();
		String query = "select DISTINCT wh from Warehouse wh";
		wareHouse = (List<Warehouse>) find(query);
		if (wareHouse != null && wareHouse.size() > 0) {
			return wareHouse;
		}
		return null;
	}

}
