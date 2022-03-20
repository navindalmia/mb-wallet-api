package com.mb.api.mb_wallet_api.user;

public class UserToken {
	private String token;

	/**
	 * @param userToken
	 */
	public UserToken(String userToken) {
		super();
		this.token = userToken;
	}

	public String getUserToken() {
		return token;
	}

	public void setUserToken(String userToken) {
		this.token = userToken;
	}

}
