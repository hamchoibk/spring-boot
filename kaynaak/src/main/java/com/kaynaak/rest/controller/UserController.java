package com.kaynaak.rest.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kaynaak.rest.entity.ChangePassword;
import com.kaynaak.rest.entity.User;
import com.kaynaak.rest.service.interfaces.UserService;
import com.kaynaak.rest.transform.BaseResponse;
import com.kaynaak.rest.transform.ResponseFactory;

/**
 * Author: Nguyen Duc Cuong Create date: Friday, 9/28/2018 9:10 PM Email:
 * cuongnd@vega.com.vn Project: mychef
 */
@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	private Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private UserService userService;

	@Autowired
	ResponseFactory responseFactory;

	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse getProfile() {
		try {
			return new BaseResponse(this.userService.getProfile());
		} catch (Exception e) {
			logger.info(" exc controller");
			logger.info(e.getMessage(), e);
			return new BaseResponse(e.getMessage());
		}
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity changePassword(@RequestBody ChangePassword changePassword, HttpServletResponse response) {
		ResponseEntity resp = null;
		try {
			User user = this.userService.changePassword(changePassword);
			Map<String, Object> map = new HashMap<>();
			return responseFactory.createResponseEntity(0, "Success", map);
		} catch (Throwable t) {
			resp = responseFactory.createErrorResponse(changePassword, t);
		}
		return resp;
	}

	@RequestMapping(value = "/refresh_token", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse refreshToken(HttpServletRequest request) {

		try {
			return new BaseResponse(this.userService.refreshToken(request));
		} catch (Exception e) {
			logger.info(" exc controller");
			logger.info(e.getMessage(), e);
			return new BaseResponse(e.getMessage());
		}
	}
}
