package com.orderbid.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;

public interface Bid {

	void setId(Integer id);

	Integer getId();

	void setBidTime(Timestamp bidTime);

	Timestamp getBidTime();

	void setBidderId(String bidderId);

	String getBidderId();

	void setOrderNo(String orderNo);

	String getOrderNo();

	void setBidAmt(BigDecimal bidAmt);

	BigDecimal getBidAmt();

	void setBidStatus(String bidStatus);

	String getBidStatus();

}
