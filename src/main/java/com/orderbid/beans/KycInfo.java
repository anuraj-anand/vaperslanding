package com.orderbid.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "KYC_DOCUMENTS")
public class KycInfo {
	private int id;
	private String addressProof;
	private String cancelledCheque;
	private String 	idProof;
	private Company company;
	
   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "ADDRESS_PROOF")
	public String getAddressProof() {
		return addressProof;
	}
	public void setAddressProof(String addressProof) {
		this.addressProof = addressProof;
	}
	
	@Column(name = "CANCELLED_CHEQUE")
	public String getCancelledCheque() {
		return cancelledCheque;
	}
	public void setCancelledCheque(String cancelledCheque) {
		this.cancelledCheque = cancelledCheque;
	}
	
	@Column(name = "ID_PROOF")
	public String getIdProof() {
		return idProof;
	}
	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}

	@ManyToOne
	@JoinColumn(name = "COMPANY_ID", nullable = false)
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
