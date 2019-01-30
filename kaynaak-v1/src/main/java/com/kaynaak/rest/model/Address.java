package com.kaynaak.rest.model;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Wednesday, 10/3/2018 8:59 AM
 * Email: cuongnd@vega.com.vn
 * Project: mychef
 */
public class Address {

    //    private String addressID;
    private String streetAddress;
    private String addressLine2;
    private String city;
    private String zipCode;
    private String state;
    private String zip;

//    private float latitude;
//    private float longitude;
//    private String tag;

    public Address() {
    }

    public Address(Address address) {
        this.streetAddress = address.getStreetAddress();
        this.addressLine2 = address.getAddressLine2();
        this.city = address.getCity();
        this.zipCode = address.getZipCode();
        this.state = address.getState();
        this.zip = address.getZip();
//        this.latitude = address.getLatitude();
//        this.longitude = address.getLongitude();
//        this.tag = address.getTag();
    }

    //    public String getAddressID() {
//        return addressID;
//    }
//
//    public void setAddressID(String addressID) {
//        this.addressID = addressID;
//    }
//
    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    //    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public float getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(float latitude) {
//        this.latitude = latitude;
//    }
//
//    public float getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(float longitude) {
//        this.longitude = longitude;
//    }
//
//    public String getTag() {
//        return tag;
//    }

//    public void setTag(String tag) {
//        this.tag = tag;
//    }


    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Address mapData(Address address) {
        this.streetAddress = address.getStreetAddress();
        this.addressLine2 = address.getAddressLine2();
        this.city = address.getCity();
        this.zipCode = address.getZipCode();
        this.state = address.getState();
        this.zip = address.getZip();
//        this.latitude = address.getLatitude();
//        this.longitude = address.getLongitude();
//        this.tag = address.getTag();
        return this;
    }
}
