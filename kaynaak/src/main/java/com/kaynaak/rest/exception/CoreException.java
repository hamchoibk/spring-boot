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
	private String errorCode;
	
	public CoreException(String errCode, String message) {
		super(message);
		this.setErrorCode(errCode);
		
	}
	
	public CoreException(String errCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errCode;
	}
	/**
	 * @return the error_code
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * @param error_code the error_code to set
	 */
	public void setErrorCode(String error_code) {
		this.errorCode = error_code;
	}
}
