/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaynaak.rest.transform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.kaynaak.rest.common.ErrorDetail;
import com.kaynaak.rest.exception.BLException;
import com.kaynaak.rest.util.ServiceMessages;
import com.kaynaak.rest.util.ValidationMessages;

/**
 *
 * @author Tom - cong.hoang at ascendcorp
 */

@Component
public class ResponseFactory {
	public static final String UNKNOW_ERROR_RESOURCE = "Error_1000_desc";
	public static final String ERROR_FORMAT_RESOURCE = "Error_%d_desc";

	public static final String BUSINESS_ERROR = "BE";
	public static final String VALIDATION_ERROR = "VE";
	public static final String SERVICE_ERROR = "SE";
	public static final String UNKNOWN_ERROR = "UE";
	public static final String VALIDATION_ERROR_DESC = "Validation Error";
	public static final String ERROR_DETAILS ="errorDetails";
	

	public static final int VALIDATION_ERROR_CODE = -2;

	@Autowired
	ServiceMessages serviceMessages;

	@Autowired
	ValidationMessages validationMessages;

	public ResponseEntity createResponseEntity(int code, String message, Object data) {
		return new ResponseEntity(new BaseResponse(code, message, data), HttpStatus.OK);
	}

	public ResponseEntity createErrorResponse(Object data, Throwable t) {
		BaseResponse baseResp = null;
		if ((t instanceof BLException)) {

			BLException appEx = (BLException) t;
			int errorCode = appEx.getErrorCode();
			if (appEx.getErrorCode() == VALIDATION_ERROR_CODE && appEx.isValidationFailed()) {

				List<ErrorDetail> errorDetails = appEx.getErrorDetails();
				for (ErrorDetail errorDetail : errorDetails) {
					if (errorDetail.getKey() != null) {
						errorDetail.setDesc(validationMessages.get(errorDetail.getKey()));
					}
				}
				Map<String, Object> map = new HashMap<>();
				map.put(ERROR_DETAILS, errorDetails);
				baseResp = new BaseResponse(errorCode, VALIDATION_ERROR_DESC, map);
				baseResp.setErrorType(VALIDATION_ERROR);

			} else {
				String errorMessage = String.format(ERROR_FORMAT_RESOURCE, errorCode);
				String message = serviceMessages.get(errorMessage);
				baseResp = new BaseResponse(errorCode, message);
				baseResp.setErrorType(BUSINESS_ERROR);
			}
			return new ResponseEntity(baseResp, HttpStatus.BAD_REQUEST);
		}

		String unknownError = serviceMessages.get(UNKNOW_ERROR_RESOURCE);
		baseResp = new BaseResponse(unknownError);
		baseResp.setErrorType(UNKNOWN_ERROR);
		return new ResponseEntity(baseResp, HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity createResponseEntity(int code, String message, Object data, HttpStatus httpStatus) {
		return new ResponseEntity(new BaseResponse(code, message, data), httpStatus);
	}

	public ResponseEntity createErrorResponse(int code, String message, Object data, Throwable t, HttpStatus httpStatus) {
		return new ResponseEntity(new BaseResponse(code, message, data), httpStatus);
	}
}
