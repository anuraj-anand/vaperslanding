package com.orderbid.beans;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RateCard generated by hbm2java
 */
@Entity
@Table(name = "TARIFF")
public class Tariff implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer pinCode;
	private BigDecimal charges;
	private String area;
	private Integer companyId;
	private Integer cardType;

	public Tariff() {
	}

	public Tariff(Integer id, Integer rateCardId, Integer pinCode,
			BigDecimal charges, String area) {
		this.id = id;
		this.pinCode = pinCode;
		this.charges = charges;
		this.area = area;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "PINCODE")
	public Integer getPinCode() {
		return this.pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}

	@Column(name = "CHARGE")
	public BigDecimal getCharges() {
		return this.charges;
	}

	public void setCharges(BigDecimal charges) {
		this.charges = charges;
	}
	
	@Column(name = "AREA")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	@Column(name = "COMPANY_ID")
	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	
	@Column(name = "CARD_TYPE")
	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tariff other = (Tariff) obj;
		if (charges == null) {
			if (other.charges != null)
				return false;
		} else if (!charges.equals(other.charges))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pinCode == null) {
			if (other.pinCode != null)
				return false;
		} else if (!pinCode.equals(other.pinCode))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((charges == null) ? 0 : charges.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pinCode == null) ? 0 : pinCode.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return "Tariff [id=" + id +", pinCode="
				+ pinCode + ", charges=" + charges + "]";
	}

}
