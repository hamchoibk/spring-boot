package com.kaynaak.rest.entity;

public class ChangePassword {
	
	protected String currentPassword;
	protected String confirmCurrentPassword;
	protected String newPassword;
	protected String confirmNewPassword;
	
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getConfirmCurrentPassword() {
		return confirmCurrentPassword;
	}
	public void setConfirmCurrentPassword(String confirmCurrentPassword) {
		this.confirmCurrentPassword = confirmCurrentPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
}
