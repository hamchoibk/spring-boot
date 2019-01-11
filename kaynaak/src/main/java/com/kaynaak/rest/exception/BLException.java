package com.kaynaak.rest.exception;

import java.util.HashMap;

public class BLException extends CoreException {

	private static final long serialVersionUID = 4335436198768153617L;
	public boolean validationFailed;
	public HashMap<String, Object> errorDetails;

	public BLException() {

	}
	public BLException(int errorCode) {
		this.errorCode = errorCode;
	}

	public boolean isValidationFailed() {
		return validationFailed;
	}

	public void setValidationFailed(boolean validationFailed) {
		this.validationFailed = validationFailed;
	}

	public HashMap<String, Object> getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(HashMap<String, Object> errorDetails) {
		this.errorDetails = errorDetails;
	}

}
