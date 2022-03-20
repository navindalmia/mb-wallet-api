package com.mb.api.mb_wallet_api.user;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4577307804491517982L;
	private Integer userId;

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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + "]";
	}

}
