package com.orderbid.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BID")
public class OngoingBid implements Bid {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String bidStatus;
	private int bidQty;
	private BigDecimal bidAmt;
	private String orderNo;
	private String bidderId;
	private Timestamp bidTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "STATUS")
	public String getBidStatus() {
		return bidStatus;
	}
	
	public void setBidStatus(String bidStatus) {
		this.bidStatus = bidStatus;
	}
	
	@Column(name = "BID_AMOUNT")
	public BigDecimal getBidAmt() {
		return bidAmt;
	}

	public void setBidAmt(BigDecimal bidAmt) {
		this.bidAmt = bidAmt;
	}

	@Column(name = "ORDER_NO")
	public String getOrderNo() {
		return orderNo;
	}
	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Column(name = "USER")
	public String getBidderId() {
		return bidderId;
	}
	
	public void setBidderId(String bidderId) {
		this.bidderId = bidderId;
	}
	
	@Column(name = "BID_TIME")
	public Timestamp getBidTime() {
		return bidTime;
	}
	
	public void setBidTime(Timestamp bidTime) {
		this.bidTime = bidTime;
	}
	
	@Column(name = "BID_QTY")
	public int getBidQty() {
		return bidQty;
	}

	public void setBidQty(int bidQty) {
		this.bidQty = bidQty;
	}

}
