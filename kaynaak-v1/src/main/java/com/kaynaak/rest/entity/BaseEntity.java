package com.kaynaak.rest.entity;

import java.util.Date;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Monday, 10/15/2018 10:53 AM
 * Email: cuongnd@vega.com.vn
 * Project: mychef
 */
public class BaseEntity {

    private Date createdOn;
    private String createdBy;
    private Date modifiedOn;
    private String modifiedBy;

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
