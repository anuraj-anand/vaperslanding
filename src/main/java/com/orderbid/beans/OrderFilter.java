package com.orderbid.beans;

public class OrderFilter {
	private int fromZip;
	private int toZip;
	private String orderNo;
	private String shippingPriority;
	private String productName;	
	private double highestAskPrice;
	private double lowestAskPrice;
	public int getFromZip() {
		return fromZip;
	}
	public void setFromZip(int fromZip) {
		this.fromZip = fromZip;
	}
	public int getToZip() {
		return toZip;
	}
	public void setToZip(int toZip) {
		this.toZip = toZip;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getShippingPriority() {
		return shippingPriority;
	}
	public void setShippingPriority(String shippingPriority) {
		this.shippingPriority = shippingPriority;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getHighestAskPrice() {
		return highestAskPrice;
	}
	public void setHighestAskPrice(double highestAskPrice) {
		this.highestAskPrice = highestAskPrice;
	}
	public double getLowestAskPrice() {
		return lowestAskPrice;
	}
	public void setLowestAskPrice(double lowestAskPrice) {
		this.lowestAskPrice = lowestAskPrice;
	}
}
