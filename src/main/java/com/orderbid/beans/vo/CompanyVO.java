package com.orderbid.beans.vo;


public class CompanyVO {
	
	private Integer id;
	private String name;
	private Integer type;
	private Integer active;
	private boolean activeBool;
	private String kycFlag;
	private Integer creationDatetime;
	private Integer updatedDatetime;
	
	public Integer getCreationDatetime() {
		return creationDatetime;
	}
	public void setCreationDatetime(Integer creationDatetime) {
		this.creationDatetime = creationDatetime;
	}
	public Integer getUpdatedDatetime() {
		return updatedDatetime;
	}
	public void setUpdatedDatetime(Integer updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}
	public String getKycFlag() {
		return kycFlag;
	}
	public void setKycFlag(String kycFlag) {
		this.kycFlag = kycFlag;
	}
	public boolean isActiveBool() {
		return activeBool;
	}
	public void setActiveBool(boolean activeBool) {
		this.activeBool = activeBool;
	}
	private String typeStr;
	
	public String getTypeStr() {
		return typeStr;
	}
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", type=" + type + ", active="+active+", typeStr="+typeStr+"]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyVO other = (CompanyVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
