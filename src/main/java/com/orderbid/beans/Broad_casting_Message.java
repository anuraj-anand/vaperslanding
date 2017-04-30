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
@Table(name = "Broad_casting_Message")
public class Broad_casting_Message {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Bcm_ID")
	private int Bcm_ID;
	
	@Column(name = "Bmessage")
	private String Bmessage;
	
	@Column(name = "Username")
	private String Username;
	
	@Column(name = "Last_Modified_Time")
	private String Last_Modified_Time;
	
	@Column(name = "Last_Modified_User")
	private String Last_Modified_User;
	
	@ManyToOne
	@JoinColumn(name = "Company_Id", nullable = false)
	private Company Company_Id;
	
	
	
	
	public int getBcm_ID() {
		return Bcm_ID;
	}
	public void setBcm_ID(int bcm_ID) {
		Bcm_ID = bcm_ID;
	}
	
	
	
	
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	
	public String getBmessage() {
		return Bmessage;
	}
	public void setBmessage(String bmessage) {
		Bmessage = bmessage;
	}
	
	
	

	public String getLast_Modified_Time() {
		return Last_Modified_Time;
	}
	public void setLast_Modified_Time(String last_Modified_Time) {
		Last_Modified_Time = last_Modified_Time;
	}
	
	
	public String getLast_Modified_User() {
		return Last_Modified_User;
	}
	public void setLast_Modified_User(String last_Modified_User) {
		Last_Modified_User = last_Modified_User;
	}
	
	
	
	public Company getCompany_Id() {
		return Company_Id;
	}
	public void setCompany_Id(Company company_Id) {
		Company_Id = company_Id;
	}
	

	
	
}
