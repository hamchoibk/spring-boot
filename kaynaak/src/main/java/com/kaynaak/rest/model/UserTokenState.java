package com.kaynaak.rest.model;

/**
 * Created by fan.jin on 2016-10-17.
 */
public class UserTokenState {
    private String name;
    private String access_token;
    private long expires_in;

    public UserTokenState() {
    }

    public UserTokenState(String access_token, long expires_in) {
        this.access_token = access_token;
        this.expires_in = expires_in;
    }

    public UserTokenState(String name, String access_token, Long expires_in) {
        this.name = name;
        this.access_token = access_token;
        this.expires_in = expires_in;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }
}