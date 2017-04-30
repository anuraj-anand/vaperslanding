package com.orderbid.dao;

import java.util.List;

import com.orderbid.beans.Company;

public interface CompanyDao {
	Integer createCompany(Company company);
	List<Company> getCompanies();
	void updateCompanyStatus(Company company);
}