package com.kaynaak.rest.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SecureUserDetails extends User {

	private static final long serialVersionUID = -8423680127147042662L;
	private String userID;
    private String email;
    private String name;
    private String userType;


	public SecureUserDetails(com.kaynaak.rest.entity.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.email = user.getEmail();
        this.userID = String.valueOf(user.getId());
        this.name = user.getName();
        this.userType= user.getUserType();
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}

}
