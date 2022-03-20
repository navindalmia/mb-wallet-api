package com.mb.api.mb_wallet_api.userWallet;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mb.api.mb_wallet_api.utility.Utility;

public class UserBalance implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8716480332204692544L;
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
		this.currentBalance = new BigDecimal(Utility.getProperty("preset.amount"));
		this.currency = Utility.getProperty("preset.currency");
	}

	/**
	 * @param userID
	 * @param currentBalance
	 * @param currency
	 */
	public UserBalance(Integer userID, BigDecimal currentBalance, String currency) {
		super();
		this.userID = userID;
		this.currentBalance = currentBalance;
		this.currency = currency;
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
