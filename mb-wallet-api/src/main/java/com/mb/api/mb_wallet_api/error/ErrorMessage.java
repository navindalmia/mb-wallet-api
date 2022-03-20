package com.mb.api.mb_wallet_api.error;

public class ErrorMessage {

	private String errorCode ;
	private String errorMessage;
	
	/**
	 * @param errorCode
	 * @param errorMessage
	 */
	public ErrorMessage(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
