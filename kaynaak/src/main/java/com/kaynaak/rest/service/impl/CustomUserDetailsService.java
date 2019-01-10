package com.kaynaak.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kaynaak.rest.entity.User;
import com.kaynaak.rest.model.Authority;
import com.kaynaak.rest.model.CustomUserDetails;
import com.kaynaak.rest.model.UserRoleName;
import com.kaynaak.rest.repository.UserRepository;

/**
 * Created by fan.jin on 2016-10-31.
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    protected final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      
    	//Optional<User> optionalUser = userRepository.findById(email);
    	//User user= optionalUser.get();
    	User user= userRepository.findByEmail(email);
       /* user.setUserId("1222");
        user.setEmail("midoreigh@gmail.com");
        user.setPassword(passwordEncoder.encode("midoreigh@gmail.com"));
        user.setName("Nguyen Van Ha");
        */
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with email '%s'.", email));
        } else {
            List<Authority> authorities = new ArrayList<>();
//            if (user.getType().trim().equals(UserRoleConstant.CHEF_ROLE)) {
//                authorities.add(new Authority(UserRoleName.ROLE_CHEF));
//            } else {
//                authorities.add(new Authority(UserRoleName.ROLE_CHEF));
//            }
            authorities.add(new Authority(UserRoleName.ROLE_CHEF));
            CustomUserDetails customUserDetails = new CustomUserDetails(user, authorities);
            return customUserDetails;
        }
    }

    public void changePassword(String oldPassword, String newPassword) {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();

        if (authenticationManager != null) {
            LOGGER.debug("Re-authenticating user '" + username + "' for password change request.");

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
        } else {
            LOGGER.debug("No authentication manager set. can't change Password!");

            return;
        }

        LOGGER.debug("Changing password for user '" + username + "'");

        User user = (User) loadUserByUsername(username);

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

    }
}
