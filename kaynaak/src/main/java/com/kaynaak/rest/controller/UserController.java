package com.kaynaak.rest.controller;

import javax.servlet.http.HttpServletRequest;

import com.kaynaak.rest.common.ResponseDescription;
import com.kaynaak.rest.service.interfaces.UserService;
import com.kaynaak.rest.transform.CoreResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Friday, 9/28/2018 9:10 PM
 * Email: cuongnd@vega.com.vn
 * Project: mychef
 */
@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public CoreResponse getProfile() {

        try {
            return new CoreResponse(this.userService.getProfile());
        } catch (Exception e) {
            logger.info(" exc controller");
            logger.info(e.getMessage(), e);
            return new CoreResponse(ResponseDescription.ERROR_STATUS, e.getMessage());
        }
    }

    @RequestMapping(value = "/refresh_token", method = RequestMethod.GET)
    @ResponseBody
    public CoreResponse refreshToken(HttpServletRequest request) {

        try {
            return new CoreResponse(this.userService.refreshToken(request));
        } catch (Exception e) {
            logger.info(" exc controller");
            logger.info(e.getMessage(), e);
            return new CoreResponse(ResponseDescription.ERROR_STATUS, e.getMessage());
        }
    }
}
