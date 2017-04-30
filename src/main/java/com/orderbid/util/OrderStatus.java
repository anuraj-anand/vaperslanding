package com.orderbid.util;

public enum OrderStatus {
	EXPRIED("E"), ONGOING("A"), COMPLETED("C"), DRAFT("D");
	
	private String status;
	
	private OrderStatus(String status){
		this.setStatus(status);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return getStatus();
	}
}
