package com.orderbid.util;

public enum OrderPriority {
	HIGH("H"), MEDIUM("M"), LOW("L");
	
	private String status;
	
	private OrderPriority(String status){
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
