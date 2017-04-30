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
@Table(name = "PICKUP_ADDRESS")
public class PickupInfo {
		private Integer id;
		private String addressLine1;
		private String addressLine2;
		private String city;
		private String pinCode;
		private Company company;
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "ID")
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		
		@Column(name = "ADDRESS_LINE_1")
		public String getAddressLine1() {
			return addressLine1;
		}
		public void setAddressLine1(String addressLine1) {
			this.addressLine1 = addressLine1;
		}
		
		@Column(name = "ADDRESS_LINE_2")
		public String getAddressLine2() {
			return addressLine2;
		}
		public void setAddressLine2(String addressLine2) {
			this.addressLine2 = addressLine2;
		}
		
		@Column(name = "PICKUP_CITY")
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		
		@Column(name = "PIN_CODE")
		public String getPinCode() {
			return pinCode;
		}
		public void setPinCode(String pinCode) {
			this.pinCode = pinCode;
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
