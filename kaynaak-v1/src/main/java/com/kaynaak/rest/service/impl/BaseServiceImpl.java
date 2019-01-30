package com.kaynaak.rest.service.impl;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

import com.kaynaak.rest.common.ErrorDetail;
import com.kaynaak.rest.exception.BLException;
import com.kaynaak.rest.model.SecureUserDetails;

public class BaseServiceImpl {

	public void setValidationResult(List<ErrorDetail> list) throws BLException {
		if (list != null && list.size() > 0) {
			BLException ble = new BLException(-2);
			ble.setValidationFailed(Boolean.TRUE.booleanValue());
			ble.setErrorDetails(list);
			throw ble;
		}
	}
	
	public SecureUserDetails getSecureUserDetails() {
		return (SecureUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
