package com.kaynaak.rest.service.interfaces;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.kaynaak.rest.entity.User;
import com.kaynaak.rest.exception.CoreException;
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

    UserTokenState register(User user) throws CoreException;

    UserTokenState login(User user) throws CoreException;

    User getProfile();

    CustomUserDetails getCustomUserDetails();

    UserTokenState refreshToken(HttpServletRequest request) throws CoreException, Exception;
}
