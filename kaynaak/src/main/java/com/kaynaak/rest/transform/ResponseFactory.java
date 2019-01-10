/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaynaak.rest.transform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.kaynaak.rest.exception.AppException;
import com.kaynaak.rest.util.Messages;

/**
 *
 * @author Tom - cong.hoang at ascendcorp
 */

@Component
public class ResponseFactory {
	public static final String UNKNOW_ERROR_RESOURCE = "Error_1000_desc";
	public static final String ERROR_FORMAT_RESOURCE = "Error_%d_desc";

	@Autowired
	Messages messages;

	public ResponseEntity createResponseEntity(int code, String message, Object data) {
		return new ResponseEntity(new BaseResponse(code, message, data), HttpStatus.OK);
	}

	public ResponseEntity createErrorResponse(Object data, Throwable t) {
		if ((t instanceof AppException)) {
			AppException appEx = (AppException) t;
			int errorCode = appEx.getErrorCode();
			String errorMessage = String.format(ERROR_FORMAT_RESOURCE, errorCode);
			String message = messages.get(errorMessage);
			BaseResponse baseResp = new BaseResponse(errorCode,message);
			return new ResponseEntity(baseResp, HttpStatus.BAD_REQUEST);
		}

		String unknownError = messages.get(UNKNOW_ERROR_RESOURCE);
		return new ResponseEntity(new BaseResponse(unknownError), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity createResponseEntity(int code, String message, Object data, HttpStatus httpStatus) {
		return new ResponseEntity(new BaseResponse(code, message, data), httpStatus);
	}

	public ResponseEntity createErrorResponse(int code, String message, Object data, Throwable t,
			HttpStatus httpStatus) {

		System.out.println(t.getClass());
		return new ResponseEntity(new BaseResponse(code, message, data), httpStatus);
	}
}
