package com.kaynaak.rest.controller;

import java.util.HashMap;
import java.util.Map;

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

import com.kaynaak.rest.entity.User;
import com.kaynaak.rest.exception.CoreException;
import com.kaynaak.rest.model.UserTokenState;
import com.kaynaak.rest.service.interfaces.UserService;
import com.kaynaak.rest.transform.BaseResponse;
import com.kaynaak.rest.transform.ResponseFactory;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Friday, 9/28/2018 11:40 AM
 * Email: cuongnd@vega.com.vn
 * Project: mychef
 */
@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
    private Logger logger = LogManager.getLogger(getClass());
    
    @Autowired
    private UserService userService;
    
    @Autowired
    ResponseFactory responseFactory;
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse register(@RequestBody User user) {

        try {
            logger.info(" User: " + user.toString());
            Map<String, Object> map = new HashMap<>();
            map.put("user", this.userService.register(user));
            return new BaseResponse(map);
        } catch (CoreException e) {
            e.printStackTrace();
            return new BaseResponse(e.getMessage());
        } catch (Exception e) {
            logger.info(" exc controller");
            logger.info(e.getMessage(), e);
            return new BaseResponse( e.getMessage());
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity login(@RequestBody User user,HttpServletResponse response) {
    	ResponseEntity resp = null;
        try {
        	UserTokenState userTokenState = this.userService.login(user);
        	logger.info(" User: " + user.toString());
            Map<String, Object> map = new HashMap<>();
            map.put("user", userTokenState);
            return responseFactory.createResponseEntity(0, "Success", map);
        } catch (Throwable t) {
			resp = responseFactory.createErrorResponse(user,t);
        }
        return resp;
    }
}
