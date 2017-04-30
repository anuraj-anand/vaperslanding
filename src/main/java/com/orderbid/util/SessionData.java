package com.orderbid.util;

import java.io.Serializable;
import com.orderbid.beans.User;

/**
 * This class will session date like User object
 * 
 * @author KsMAC
 *
 */
public class SessionData implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user;

	public SessionData() {
		this.user = null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "SessionData [user=" + user + "]";
	}

}
