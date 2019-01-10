/**
 * Created CoreResponse.java at 10:45:56 AM by hungvq
 * TODO
 */
package com.kaynaak.rest.transform;

import java.io.Serializable;

/**
 * @author hungvq
 */
public class CoreResponse implements Serializable {

    private static final long serialVersionUID = -566238632352226194L;
    private boolean success;
    private String message;
    private Object data;

    public CoreResponse() {
    }

    public CoreResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public CoreResponse(Object data) {
        this.success = true;
        this.data = data;
    }

    public CoreResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
