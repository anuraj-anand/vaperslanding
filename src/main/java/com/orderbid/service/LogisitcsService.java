package com.orderbid.service;

import java.util.List;

import com.orderbid.beans.Logisitcs;

public interface LogisitcsService {
	public Integer createEntity(Logisitcs logisitcs);
	public List<Logisitcs> noOfRegisteredLogisitcs();
}