package com.kaynaak.rest.constants;

/**
 * Author: Nguyen Duc Cuong Create date: Friday, 9/14/2018 4:05 PM Email:
 * cuongnd@vega.com.vn Project: frontend_api
 */
public class MessageCodeDefinition {

	public static final String INTERNAL_SERVER_ERROR = "500";

	public static final int EMAIL_NOT_EMPTY_CODE = 1001; // Email is not empty.
	public static final int PASSWORD_NOT_EMPTY_CODE = 1002; // Password is not empty.
	public static final int USER_TYPE_INVALID_CODE = 1003;
	public static final int EMAIL_EXIST_CODE = 1004; // Email is exist.
	public static final int NAME_NOT_EMPTY_CODE = 1005; // Name is not empty.
	public static final int EMAIL_OR_PASSWORD_NOT_RIGHT_CODE = 1006;
	public static final int INVALID_CREDENT = 1007;
	public static final int INVALID_USER_NAME = 1008;
        public static final int INVALID_OTP_ACTIVE_USER = 1009;
        public static final int DEPARTMENT_ID_NOT_EMPTY = 1010;
        public static final int USER_TYPE_EMPTY = 1011;
        public static final int USERNAME_EMPTY = 1012;
        public static final int STATUS_EMPTY = 1013;
        public static final int COMMON_ERROR = 1014;
        
	public static final String EMAIL_NOT_EMPTY_MESSAGE = "Email is not empty.";
	public static final String PASSWORD_NOT_EMPTY_MESSAGE = "Password is not empty.";
	public static final String USER_TYPE_INVALID_MESSAGE = "User type is invalid.";
	public static final String EMAIL_EXIST_MESSAGE = "Email is exist."; // Username is exist.
	public static final String NAME_NOT_EMPTY_MESSAGE = "Name is not empty.";
	public static final String EMAIL_OR_PASSWORD_NOT_RIGHT_MESSAGE = "Email or password is not right.";
        public static final String INVALID_OTP_FOR_ACTIVE_USER = "OTP is not correct";

}
