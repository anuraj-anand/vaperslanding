package com.orderbid.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Vap_Registration")
public class Vap_Registration {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name = "Vap_Registrationid")
	private int Vap_Registrationid;
	
	@Column(name = "Email")
	private String Email;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "lastmodifiedDate")
	private String lastmodifiedDate;
	public int getVap_Registrationid() {
		return Vap_Registrationid;
	}
	public void setVap_Registrationid(int vap_Registrationid) {
		Vap_Registrationid = vap_Registrationid;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastmodifiedDate() {
		return lastmodifiedDate;
	}
	public void setLastmodifiedDate(String lastmodifiedDate) {
		this.lastmodifiedDate = lastmodifiedDate;
	}
	public Vap_Registration() {
		super();
	}
	
	
	
	
	
	
}
