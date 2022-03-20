package com.mb.api.mb_wallet_api.user;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4733991939051820742L;
	
	private Integer userId;
	private String token;

	/**
	 * @param userId
	 */

	public User() {
		super();

	}
	public User(Integer userId) {
		super();
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", token=" + token + "]";
	}

}
