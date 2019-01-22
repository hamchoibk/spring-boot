package com.kaynaak.rest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

import com.kaynaak.rest.common.ErrorDetail;
import com.kaynaak.rest.common.TokenHelper;
import com.kaynaak.rest.constants.MessageCodeDefinition;
import com.kaynaak.rest.constants.ValidationFieldConstants;
import com.kaynaak.rest.entity.ChangePassword;
import com.kaynaak.rest.entity.User;
import com.kaynaak.rest.exception.BLException;
import com.kaynaak.rest.mail.EmailService;
import com.kaynaak.rest.model.SecureUserDetails;
import com.kaynaak.rest.model.UserTokenState;
import com.kaynaak.rest.repository.UserRepository;
import com.kaynaak.rest.service.interfaces.UserService;
import com.kaynaak.rest.util.Md5Util;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

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
	private SecureUserDetailsService userDetailsService;
	
	@Autowired
	private EmailService emailservice;

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
		return userRepository.findById(userId);
		//Optional<User> optionalUser = userRepository.findById(userId);
		//return optionalUser.isPresent() ? optionalUser.get() : null;
	}

	public List<User> findAll() throws AccessDeniedException {
		List<User> result = userRepository.findAll();
		return result;
	}

	@Override
	public UserTokenState register(User user) throws BLException {

		if (StringUtils.isEmpty(user.getEmail())) {
			throw new BLException(MessageCodeDefinition.EMAIL_NOT_EMPTY_CODE);
		}

		if (StringUtils.isEmpty(user.getName())) {
			throw new BLException(MessageCodeDefinition.NAME_NOT_EMPTY_CODE);
		}

		if (StringUtils.isEmpty(user.getPassword())) {
			throw new BLException(MessageCodeDefinition.PASSWORD_NOT_EMPTY_CODE);
		}

//        if (StringUtils.isEmpty(user.getType()) || (!user.getType().equals(UserRoleConstant.CHEF_ROLE) && !user.getType().equals(UserRoleConstant.CUSTOMER_ROLE))) {
//            throw new CoreException(MessageCodeDefinition.USER_TYPE_INVALID_CODE, MessageCodeDefinition.USER_TYPE_INVALID_MESSAGE);
//        }
		User dbUser = userRepository.findByEmail(user.getEmail());

		if (dbUser != null) {
			throw new BLException(MessageCodeDefinition.EMAIL_EXIST_CODE);
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

		//emailservice.sendSimpleMessage("nvhabk@gmail.com", "haha", "ada");
		List<ErrorDetail> errors = validateLogin(user);
		super.setValidationResult(errors);

		Authentication authentication = null;
		try {
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getUsername(),
					user.getPassword());
			authentication = authenticationManager.authenticate(authToken);
		} catch (Exception e) {
			throw new BLException(MessageCodeDefinition.INVALID_CREDENT);
		}

		// Inject into security context
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// token creation
		SecureUserDetails customUserDetails = (SecureUserDetails) authentication.getPrincipal();

		// System.out.println(customUserDetails.getUserID() + "_" +
		// customUserDetails.getUsername());
		String jws = tokenHelper.generateToken(customUserDetails.getUsername());
		// Return the token
		return new UserTokenState(customUserDetails.getName(),customUserDetails.getUserType(), jws, (long) EXPIRES_IN);
	}
	
	@Override
	public User getProfile() {
		Integer id = Integer.valueOf(getCustomUserDetails().getUserID());
		return this.findById(id);
	}

	@Override
	public SecureUserDetails getCustomUserDetails() {
		return (SecureUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	@Override
	public UserTokenState refreshToken(HttpServletRequest request) throws Exception {
		String authToken = tokenHelper.getToken(request);

		if (authToken != null) {
			// TODO check user password last update
			String refreshedToken = tokenHelper.refreshToken(authToken);
			SecureUserDetails customUserDetails = (SecureUserDetails) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
			return new UserTokenState(customUserDetails.getName(), refreshedToken, (long) EXPIRES_IN);

		} else {
			throw new Exception("Token is not empty.");
		}
	}

	@Override
	public User changePassword(ChangePassword changepassword) throws BLException {
		List<ErrorDetail> errors = new ArrayList<>();
		SecureUserDetails  userDetails = getCustomUserDetails();
		String currentPwdDataBase = userDetails.getPassword();
		if( null ==changepassword
			||null == changepassword.getCurrentPassword()
			||null == changepassword.getNewPassword()
			||null == changepassword.getConfirmNewPassword()) {
			errors.add(new ErrorDetail(ValidationFieldConstants.CURRENT_PASSWORD,ValidationFieldConstants.REQUIRED_INFOMATION));
		} else {
			String md5Current =Md5Util.md5Hash(changepassword.getCurrentPassword());
			if(!md5Current.equals(currentPwdDataBase)) {
				errors.add(new ErrorDetail(ValidationFieldConstants.INVALID_CURRENT_PASSWORD,ValidationFieldConstants.INVALID_CURRENT_PASSWORD));
			}
			
			if(!changepassword.getNewPassword().equals(changepassword.getConfirmNewPassword())) {
				errors.add(new ErrorDetail(ValidationFieldConstants.CONFIRM_NEW_PASSWORD,ValidationFieldConstants.NOMATCH_PASSWORD_CONFIRMPASSWORD));
			}
			if(changepassword.getNewPassword().equals(changepassword.getCurrentPassword())) {
				errors.add(new ErrorDetail(ValidationFieldConstants.NEW_PASSWORD,ValidationFieldConstants.INVALID_NEW_PASSWORD));
			}
		}
		super.setValidationResult(errors);	
		
		Integer id = Integer.valueOf(userDetails.getUserID());
		User userFromDb = userRepository.findById(id);
		userFromDb.setPassword(passwordEncoder.encode(changepassword.getNewPassword()));
		userRepository.save(userFromDb);
		
		//System.out.println(id);
		return userFromDb;
	}
	
	@Override
	public User resetPasswordInit(ChangePassword changepassword) throws BLException {
		Random ran = new Random();
		int otp = ran.nextInt(99999) + 100000;
		
		User userFromDb = userRepository.findByEmail(changepassword.getEmail());
		if(userFromDb == null) {
			throw new BLException(1100);
		}
		
		userFromDb.setOtp(String.valueOf(otp));
		userRepository.save(userFromDb);
	
		String baseURL="test";
		 String subject = "Forgot password!";
        String content = "Hi "+userFromDb.getName()
        +", \r\n\r\nWe have got a request to recover your password. OTP for create a new password is, "
        +otp+". Please click on the below link and create a new password.\r\n"
        +baseURL
        +"RecoverPassword\r\n\r\nThanks,\r\nStudentmgt Team";
        
        emailservice.sendSimpleMessage(changepassword.getEmail(), subject, content);

		return userFromDb;
	}

	@Override
	public User resetPassword(ChangePassword changepassword) throws BLException {
		User userFromDb = userRepository.findByEmail(changepassword.getEmail());
		if(userFromDb == null) {
			throw new BLException(1100);
		}
		if(null == changepassword.getOtp() || !changepassword.getOtp().equals(userFromDb.getOtp())) {
			throw new BLException(1101);
		}
		
		if(null == changepassword.getNewPassword()) {
			throw new BLException(1101);
		}
		userFromDb.setOtp("");
		userFromDb.setPassword(passwordEncoder.encode(changepassword.getNewPassword()));
		
		
		userRepository.save(userFromDb);
		return userFromDb;
	}

	
	public List<ErrorDetail> validateLogin(User user) {
		List<ErrorDetail> errors = new ArrayList<>();
		if (user.getUsername() == null || StringUtils.isEmpty(user.getUsername())) {
			errors.add(new ErrorDetail(ValidationFieldConstants.USER_NAME, ValidationFieldConstants.NOT_NULL_USER_NAME_KEY));
		}
		if (user.getPassword() == null ||StringUtils.isEmpty(user.getPassword())) {
			errors.add(new ErrorDetail(ValidationFieldConstants.PASSWORD, ValidationFieldConstants.NOT_NULL_PASSWORD_KEY));
		}
		return errors;
	}
}
