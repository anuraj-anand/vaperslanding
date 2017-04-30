package com.orderbid.dao;

import java.util.List;

import com.orderbid.beans.Logisitcs;

public interface LogisitcsDao {
	public Integer createEntity(Logisitcs logisitcs);

	public List<Logisitcs> noOfRegisteredLogisitcs();
}
