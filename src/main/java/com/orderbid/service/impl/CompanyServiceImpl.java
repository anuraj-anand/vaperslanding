package com.orderbid.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderbid.beans.Company;
import com.orderbid.beans.vo.CompanyVO;
import com.orderbid.dao.CompanyDao;
import com.orderbid.service.CompanyService;
import com.orderbid.util.BeansVOConverter;

@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	CompanyDao companyDao;
	
	@Override
	public Integer createEntity(Company company) {
		return companyDao.createCompany(company);
	}
	
	public List<CompanyVO> getCompanies(){
		return BeansVOConverter.getCompanyVOList(companyDao.getCompanies());
	}
	
	public void updateCompanyStatus(Company company){
		companyDao.updateCompanyStatus(company);
	}
}