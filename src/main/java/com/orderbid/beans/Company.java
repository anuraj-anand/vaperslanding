package com.orderbid.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "COMPANY")
public class Company implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Integer type;
	private Integer active;
	private String kycFlag;
	private Integer creationDatetime;
	private Integer updatedDatetime;
	
	@Column(name="UPDATED_DATETIME")
	public Integer getUpdatedDatetime() {
		return updatedDatetime;
	}
	public void setUpdatedDatetime(Integer updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}
	@Column(name="CREATED_DATETIME")
	public Integer getCreationDatetime() {
		return creationDatetime;
	}
	public void setCreationDatetime(Integer creationDatetime) {
		this.creationDatetime = creationDatetime;
	}
	private Set<User> users = new HashSet<User>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id")	
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
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
		Company other = (Company) obj;
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
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "TYPE")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	@Column(name = "ACTIVE")
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	
	@Column(name = "FLAG")
	public String getKycFlag() {
		return kycFlag;
	}
	public void setKycFlag(String kycFlag) {
		this.kycFlag = kycFlag;
	}
	
	
	
}
