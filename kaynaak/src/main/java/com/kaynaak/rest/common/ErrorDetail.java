package com.kaynaak.rest.common;

import java.io.Serializable;

public class ErrorDetail implements Serializable {
	private static final long serialVersionUID = -1205416164461320827L;

	protected String key;
	protected String fieldname;
	protected String desc;

	public ErrorDetail() {
	}

	public ErrorDetail(String fieldname, String errorDescription) {
		this.fieldname = fieldname;
		this.desc = errorDescription;
	}

	public ErrorDetail(String errorKey, String fieldname, String errorDescription) {
		this.key = errorKey;
		this.fieldname = errorKey;
		this.desc = errorDescription;
	}

	public String getFieldname() {
		return fieldname;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
