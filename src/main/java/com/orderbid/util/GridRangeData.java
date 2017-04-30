package com.orderbid.util;

import com.orderbid.beans.OrderFilter;

public class GridRangeData {

	private int fromRange;
	private int range;
	public static final int defaultRange = 10;
	
	private OrderFilter filter;

	public OrderFilter getFilter() {
		return filter;
	}

	public void setFilter(OrderFilter filter) {
		this.filter = filter;
	}

	public GridRangeData() {
		this.fromRange = 0;
		this.range = defaultRange;
	}

	public GridRangeData(int frmRange, int maxRange){
		this.fromRange = frmRange;
		this.range = maxRange;
	}
	
	public int getFromRange() {
		return fromRange;
	}

	public void setFromRange(int fromRange) {
		this.fromRange = fromRange;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int maxRange) {
		this.range = maxRange;
	}

	public int getRangeDifference(){
		return this.range - (this.fromRange);
	}
}
