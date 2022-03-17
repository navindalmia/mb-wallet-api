package com.mb.api.mb_wallet_api.userWallet;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserBalance {
	@JsonIgnore
	private Integer userID;
	private BigDecimal currentBalance;
	private String currency;
	
	
	
	/**
	 * @param userID
	 * @param currentBalance
	 * @param currency
	 */
	public UserBalance(Integer userID) {
		super();
		this.userID = userID;
		this.currentBalance = new BigDecimal(99999999); // to be fetched from Properties file
		this.currency = "GBP"; // to be fetched from Properties file
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@Override
	public String toString() {
		return "UserBalance [userID=" + userID + ", currentBalance=" + currentBalance + ", currency=" + currency + "]";
	}
	
	
}
