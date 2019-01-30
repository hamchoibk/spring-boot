package com.kaynaak.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by fan.jin on 2016-11-03.
 */

public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = -4913684387944148484L;
	UserRoleName name;

    public Authority(UserRoleName name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name.name();
    }

    public void setName(UserRoleName name) {
        this.name = name;
    }

    @JsonIgnore
    public UserRoleName getName() {
        return name;
    }

}
