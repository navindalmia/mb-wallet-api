package com.mb.api.mb_wallet_api.userWallet;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserTransaction implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = -6298815492179376021L;

//	@JsonIgnore
	private Integer userId;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private Date date;
	private String description;
	private BigDecimal amount;
	private String currency;

	/**
	 * 
	 */
	public UserTransaction() {
		super();
	}

	/**
	 * @param userId
	 * @param date
	 * @param description
	 * @param amount
	 * @param currency
	 */
	public UserTransaction(Integer userId, Date date, String description, BigDecimal amount, String currency) {
		super();
		this.userId = userId;
		this.date = date;
		this.description = description;
		this.amount = amount;
		this.currency = currency;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "UserTransaction [date=" + date + ", description=" + description + ", amount=" + amount + ", currency="
				+ currency + "]";
	}

}
