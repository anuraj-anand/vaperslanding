package com.orderbid.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_ms_status")
public class OrderStatus {
private Integer id;
private String orderStatus;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "ID")
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}

@Column(name = "ORDER_STATUS")
public String getOrderStatus() {
	return orderStatus;
}
public void setOrderStatus(String orderStatus) {
	this.orderStatus = orderStatus;
}
}
