package com.orderbid.service;

import java.util.List;

import com.orderbid.beans.Company;
import com.orderbid.beans.vo.CompanyVO;

public interface CompanyService {
	public Integer createEntity(Company company);
	List<CompanyVO> getCompanies();
	void updateCompanyStatus(Company company);
}