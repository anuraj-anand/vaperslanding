package com.orderbid.beans.vo;

import com.orderbid.beans.Company;

public class KycInfoVO {
	private int id;
	private String addressProof;
	private String cancelledCheque;
	private String 	idProof;
	private Company company;
   
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAddressProof() {
		return addressProof;
	}
	public void setAddressProof(String addressProof) {
		this.addressProof = addressProof;
	}
	
	public String getCancelledCheque() {
		return cancelledCheque;
	}
	public void setCancelledCheque(String cancelledCheque) {
		this.cancelledCheque = cancelledCheque;
	}
	
	public String getIdProof() {
		return idProof;
	}
	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
