package com.orderbid.beans.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.orderbid.beans.Bid;

public class BidVO {
	
	private Integer id;
	private String bidStatus;
	private BigDecimal bidAmt;
	private String orderNo;
	private String bidderId;
	private Timestamp bidTime;
	private int bidQty;
	
	public int getBidQty() {
		return bidQty;
	}

	public void setBidQty(int bidQty) {
		this.bidQty = bidQty;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getBidStatus() {
		return bidStatus;
	}
	
	public void setBidStatus(String bidStatus) {
		this.bidStatus = bidStatus;
	}
	
	//@Column(name = "BID_AMOUNT")
	public BigDecimal getBidAmt() {
		return bidAmt;
	}

	public void setBidAmt(BigDecimal bidAmt) {
		this.bidAmt = bidAmt;
	}

	//@Column(name = "ORDER_NO")
	public String getOrderNo() {
		return orderNo;
	}
	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	//@Column(name = "USER")
	public String getBidderId() {
		return bidderId;
	}
	
	public void setBidderId(String bidderId) {
		this.bidderId = bidderId;
	}
	
	//@Column(name = "BID_TIME")
	public Timestamp getBidTime() {
		return bidTime;
	}
	
	public void setBidTime(Timestamp bidTime) {
		this.bidTime = bidTime;
	}

	@Override
	public String toString() {
		return "BidVO [id=" + id + ", bidStatus=" + bidStatus + ", bidAmt="
				+ bidAmt + ", orderNo=" + orderNo + ", bidderId=" + bidderId
				+ ", bidTime=" + bidTime + "]";
	}

	
}
