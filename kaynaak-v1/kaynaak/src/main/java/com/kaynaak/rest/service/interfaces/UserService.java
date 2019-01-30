package com.kaynaak.rest.service.interfaces;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kaynaak.rest.entity.ChangePassword;
import com.kaynaak.rest.entity.User;
import com.kaynaak.rest.exception.BLException;
import com.kaynaak.rest.model.SecureUserDetails;
import com.kaynaak.rest.model.UserTokenState;

/**
 * Created by fan.jin on 2016-10-15.
 */
public interface UserService {
    User findById(String id);
    
    User findById(Integer id);
    
    User findByUsername(String username);

    List<User> findAll();

    User register(User user) throws BLException;

    UserTokenState login(User user) throws BLException;

    User getProfile();

    SecureUserDetails getCustomUserDetails();

    UserTokenState refreshToken(HttpServletRequest request) throws BLException, Exception;
    
    User changePassword(ChangePassword changepassword) throws BLException;
    
    User resetPasswordInit(ChangePassword changepassword) throws BLException;
    
    User resetPassword(ChangePassword changepassword) throws BLException;
    
    User activeUser(User user) throws BLException;
}
