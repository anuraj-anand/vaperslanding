package com.orderbid.beans;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BID_SESSIONS")
public class BiddingSession {

	private int id;
	private Time fromTime;
	private Time toTime;
	private String day;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "FROM_TIME" )
	public Time getFromTime() {
		return fromTime;
	}
	public void setFromTime(Time fromTime) {
		this.fromTime = fromTime;
	}
	
	@Column(name = "TO_TIME" )
	public Time getToTime() {
		return toTime;
	}
	public void setToTime(Time toTime) {
		this.toTime = toTime;
	}
	
	@Column(name = "DAY" )
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((fromTime == null) ? 0 : fromTime.hashCode());
		result = prime * result + id;
		result = prime * result + ((toTime == null) ? 0 : toTime.hashCode());
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
		BiddingSession other = (BiddingSession) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (fromTime == null) {
			if (other.fromTime != null)
				return false;
		} else if (!fromTime.equals(other.fromTime))
			return false;
		if (id != other.id)
			return false;
		if (toTime == null) {
			if (other.toTime != null)
				return false;
		} else if (!toTime.equals(other.toTime))
			return false;
		return true;
	}
}
