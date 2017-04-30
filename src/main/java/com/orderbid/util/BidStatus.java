package com.orderbid.util;

public enum BidStatus {
	EXPRIED("E"), ONGOING("A"), WINNER("W"), COMPLETED("C");
	
	private String status;
	
	private BidStatus(String status){
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
