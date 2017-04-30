package com.orderbid.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.orderbid.beans.Logisitcs;
import com.orderbid.beans.Warehouse;
import com.orderbid.dao.LogisitcsDao;

@Repository("LogisitcsDao")
@Transactional
public class LogisitcsDaoImpl extends BaseDaoImpl implements LogisitcsDao {

	public LogisitcsDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Integer createEntity(Logisitcs logisitcs) {
		return  (Integer)super.createEntity(logisitcs);
	}

	@Override
	public List<Logisitcs> noOfRegisteredLogisitcs() {
		List<Logisitcs> wareHouse = new ArrayList<Logisitcs>();
		String query = "select DISTINCT lg from Logisitcs lg";
		wareHouse = (List<Logisitcs>) find(query);
		if (wareHouse != null && wareHouse.size() > 0) {
			return wareHouse;
		}
		return null;
	}

}
