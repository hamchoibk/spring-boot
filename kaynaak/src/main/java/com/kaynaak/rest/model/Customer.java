package com.kaynaak.rest.model;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Thursday, 12/6/2018 1:42 PM
 * Email: cuongnd@vega.com.vn
 * Project: rest
 */
public class Customer {

    private String customerName;
    private Address address;

    public Customer() {
    }

    public Customer(String customerName, Address address) {
        this.customerName = customerName;
        this.address = address;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
