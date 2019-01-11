package com.kaynaak.rest.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.kaynaak.rest.common.MessageCodeDefinition;
import com.kaynaak.rest.common.ErrorDetail;
import com.kaynaak.rest.entity.User;
import com.kaynaak.rest.exception.BLException;
import com.kaynaak.rest.exception.CoreException;
import com.kaynaak.rest.model.CustomUserDetails;
import com.kaynaak.rest.model.UserTokenState;
import com.kaynaak.rest.repository.UserRepository;
import com.kaynaak.rest.security.TokenHelper;
import com.kaynaak.rest.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TokenHelper tokenHelper;

    @Value("${jwt.expires_in}")
    private int EXPIRES_IN;

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    public User findByUsername(String email) throws UsernameNotFoundException {
        User u = userRepository.findByEmail(email);
        return u;
    }

    @Override
    public User findById(String userId) throws AccessDeniedException {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.isPresent() ? optionalUser.get() : null;
    }

    @Override
    public User findById(Integer userId) throws AccessDeniedException {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.isPresent() ? optionalUser.get() : null;
    }

    
    public List<User> findAll() throws AccessDeniedException {
        List<User> result = userRepository.findAll();
        return result;
    }

    @Override
    public UserTokenState register(User user) throws CoreException {

        if (StringUtils.isEmpty(user.getEmail())) {
            throw new CoreException(MessageCodeDefinition.EMAIL_NOT_EMPTY_CODE);
        }

        if (StringUtils.isEmpty(user.getName())) {
            throw new CoreException(MessageCodeDefinition.NAME_NOT_EMPTY_CODE);
        }

        if (StringUtils.isEmpty(user.getPassword())) {
            throw new CoreException(MessageCodeDefinition.PASSWORD_NOT_EMPTY_CODE);
        }

//        if (StringUtils.isEmpty(user.getType()) || (!user.getType().equals(UserRoleConstant.CHEF_ROLE) && !user.getType().equals(UserRoleConstant.CUSTOMER_ROLE))) {
//            throw new CoreException(MessageCodeDefinition.USER_TYPE_INVALID_CODE, MessageCodeDefinition.USER_TYPE_INVALID_MESSAGE);
//        }
        User dbUser = userRepository.findByEmail(user.getEmail());

        if (dbUser != null) {
            throw new CoreException(MessageCodeDefinition.EMAIL_EXIST_CODE);
        }
        String password = user.getPassword();
       // Customer customer = new Customer();
       // customer.setCustomerName(user.getName());
       // user.setCustomer(customer);
        user.setPassword(this.passwordEncoder.encode(password));
        this.userRepository.save(user);

        User login = new User();
        login.setEmail(user.getEmail());
        login.setPassword(password);
        return login(login);
    }

    @Override
    public UserTokenState login(User user) throws BLException {
    	
    	List<ErrorDetail> errors =  validateLogin( user);
    	setValidationResult(errors);
    
        Authentication authentication = null;
        try {
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
            authentication = authenticationManager.authenticate(authToken);
        } catch (Exception e) {
            throw new BLException(MessageCodeDefinition.INVALID_CREDENT);
        }

        // Inject into security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // token creation
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        
        System.out.println(customUserDetails.getUserID() + "_" + customUserDetails.getUsername());
        String jws = tokenHelper.generateToken(customUserDetails.getUsername());
        // Return the token
        return new UserTokenState(customUserDetails.getName(), jws, (long) EXPIRES_IN);
    }

	public  List<ErrorDetail> validateLogin(User user) {
		List<ErrorDetail> errors = new ArrayList<>();
		if (user.getUsername() == null || org.apache.commons.lang.StringUtils.isBlank(user.getUsername())) {
			errors.add(new ErrorDetail("username", "User Name not null"));
		}
		if (user.getPassword() == null || org.apache.commons.lang.StringUtils.isBlank(user.getPassword())) {
			errors.add(new ErrorDetail("password", "Password not null"));
		}
		return errors;
	}
	
	private void setValidationResult(List<ErrorDetail> list) throws BLException {
		
		if(list != null  && list.size() >0) {
			BLException ble = new BLException(-2);
			ble.setValidationFailed(Boolean.TRUE.booleanValue());
			if(ble.getErrorDetails() == null) {
				ble.errorDetails = new  HashMap<String, Object>();
				ble.errorDetails.put("errorDetails", list);
			}
			throw ble;
		}
		
	}
    @Override
    public User getProfile() {
    	Integer id = Integer.valueOf(getCustomUserDetails().getUserID());
        return this.findById(id);
    }

    @Override
    public CustomUserDetails getCustomUserDetails() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetails;
    }

    @Override
    public UserTokenState refreshToken(HttpServletRequest request) throws Exception {
        String authToken = tokenHelper.getToken(request);

        if (authToken != null) {
            // TODO check user password last update
            String refreshedToken = tokenHelper.refreshToken(authToken);
            CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return new UserTokenState(customUserDetails.getName(), refreshedToken, (long) EXPIRES_IN);

        } else {
            throw new Exception("Token is not empty.");
        }
    }


}
