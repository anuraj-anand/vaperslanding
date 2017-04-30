package com.orderbid.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderbid.beans.Logisitcs;
import com.orderbid.dao.LogisitcsDao;
import com.orderbid.service.LogisitcsService;

@Service
public class LogisitcsServiceImpl implements LogisitcsService {

	@Autowired
	LogisitcsDao logisitcsDao;
	
	public LogisitcsServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Integer createEntity(Logisitcs logisitcs) {
		return logisitcsDao.createEntity(logisitcs);
	}

	@Override
	public List<Logisitcs> noOfRegisteredLogisitcs() {

		return logisitcsDao.noOfRegisteredLogisitcs();
	}

}
