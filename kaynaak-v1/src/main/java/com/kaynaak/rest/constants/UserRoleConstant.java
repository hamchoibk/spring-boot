package com.kaynaak.rest.constants;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Friday, 9/28/2018 11:37 AM
 * Email: cuongnd@vega.com.vn
 * Project: mychef
 */
public class UserRoleConstant {

    public static String CUSTOMER_ROLE = "Customer";
    public static String ADMIN_ROLE = "Admin";
    public enum USER_STATUS {
        ACTIVE("active"), INACTIVE("inactive");
        
        String status;

        private USER_STATUS(String status) {
            this.status = status;
        }
        
        public String getStatus() {
            return status;
        }
    }

}
