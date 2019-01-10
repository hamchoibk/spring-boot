/**
 * Created CoreResponse.java at 10:45:56 AM by hungvq
 * TODO
 */
package com.kaynaak.rest.transform;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author hungvq
 */
public class BaseResponse implements Serializable {

	private static final long serialVersionUID = -566238632352226194L;
	protected int code;

	@JsonProperty("message")
	protected String message;

	@JsonProperty("data")
	protected Object data;

	public BaseResponse() {
	}

	public BaseResponse(int code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public BaseResponse(String message, Object data) {
		this.message = message;
		this.data = data;
	}

	public BaseResponse(Object data) {
		this.data = data;
	}

	public BaseResponse(int code,String message) {
		this.code =code;
		this.message = message;
	}

	public BaseResponse(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
