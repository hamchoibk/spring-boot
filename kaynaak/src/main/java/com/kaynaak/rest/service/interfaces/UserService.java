package com.kaynaak.rest.service.interfaces;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kaynaak.rest.entity.ChangePassword;
import com.kaynaak.rest.entity.User;
import com.kaynaak.rest.exception.BLException;
import com.kaynaak.rest.model.CustomUserDetails;
import com.kaynaak.rest.model.UserTokenState;

/**
 * Created by fan.jin on 2016-10-15.
 */
public interface UserService {
    User findById(String id);
    
    User findById(Integer id);
    
    User findByUsername(String username);

    List<User> findAll();

    UserTokenState register(User user) throws BLException;

    UserTokenState login(User user) throws BLException;

    User getProfile();

    CustomUserDetails getCustomUserDetails();

    UserTokenState refreshToken(HttpServletRequest request) throws BLException, Exception;
    
    User changePassword(ChangePassword changepassword) throws BLException;
}
