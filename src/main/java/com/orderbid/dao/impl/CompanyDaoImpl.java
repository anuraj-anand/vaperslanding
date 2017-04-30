package com.orderbid.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.orderbid.beans.Company;
import com.orderbid.dao.CompanyDao;

@Repository("CompanyDao")
@Transactional
public class CompanyDaoImpl  extends BaseDaoImpl implements CompanyDao {

	@Override
	public Integer createCompany(Company company) {
		return  (Integer)super.createEntity(company);
	}

	@Override
	public List<Company> getCompanies(){
		List<Company> companies = new ArrayList<Company>();
		String query = "from Company";
		companies = (List<Company>) find(query);
		if (companies != null && companies.size() > 0) {
			return companies;
		}
		return null;
	}
	
	public void updateCompanyStatus(Company company){
		super.updateEntity(company);
	}
}