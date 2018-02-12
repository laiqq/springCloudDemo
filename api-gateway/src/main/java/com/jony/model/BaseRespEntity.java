package com.jony.model;


public class BaseRespEntity<E> {
	/**
	 * return result is success or fail
	 */
	private String resultCode;
	/**
	 * this is the entity of return data
	 */
	private E resultData;
	/**
	 * this field  value when resultCode is fail
	 */
	private String errorCode;

	public BaseRespEntity() {
	}

	public BaseRespEntity(String resultCode) {
		this.resultCode = resultCode;
	}

	public BaseRespEntity(String resultCode, E resultData) {
		this.resultCode = resultCode;
		this.resultData = resultData;
	}

	public BaseRespEntity(String resultCode, String errorCode, String errorMessage) {
		this.resultCode = resultCode;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public BaseRespEntity(String resultCode, E resultData, String errorCode, String errorMessage) {
		this.resultCode = resultCode;
		this.resultData = resultData;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	/**
	 * return the message when resultCode is fail
	 */




	private String errorMessage;
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public E getResultData() {
		return resultData;
	}
	public void setResultData(E resultData) {
		this.resultData = resultData;
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

