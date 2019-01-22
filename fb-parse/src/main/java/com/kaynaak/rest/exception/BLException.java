package com.kaynaak.rest.exception;

import java.util.List;

import com.kaynaak.rest.common.ErrorDetail;

public class BLException extends CoreException {

	private static final long serialVersionUID = 4335436198768153617L;
	protected boolean validationFailed;
	protected List<ErrorDetail> errorDetails;
	
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
	
	public List<ErrorDetail> getErrorDetails() {
		return errorDetails;
	}
	public void setErrorDetails(List<ErrorDetail> errorDetails) {
		this.errorDetails = errorDetails;
	}
}
