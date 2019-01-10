/**
 * Created CoreException.java at 11:12:18 AM by hungvq
 * TODO
 */
package com.kaynaak.rest.exception;

/**
 * @author hungvq
 *
 */
public class AppException extends Exception {

	private static final long serialVersionUID = 1L;

	private int errorCode;
	private String errorMessage;

	public AppException() {
	}

	public AppException(String message) {
		super(message);
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
