/**
 * Created CoreException.java at 11:12:18 AM by hungvq
 * TODO
 */
package com.kaynaak.rest.exception;

/**
 * @author hungvq
 *
 */
public class CoreException extends Exception {

	private static final long serialVersionUID = 1L;

	protected int errorCode;
	protected String errorMessage;

	public CoreException() {
	}

	public CoreException(String message) {
		super(message);
	}

	public CoreException(String message, Throwable cause) {
		super(message, cause);
	}

	public CoreException(int errorCode) {
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
