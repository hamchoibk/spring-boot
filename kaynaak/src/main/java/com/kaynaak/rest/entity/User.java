package com.kaynaak.rest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/* 
id
department_id
name
email
phone
user_name
password
user_type
otp
status
 */

@Entity
@Table(name = "Customer")
public class User {

	@Id
	@Column(name = "id")
	private String userId;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "";
	}
}
