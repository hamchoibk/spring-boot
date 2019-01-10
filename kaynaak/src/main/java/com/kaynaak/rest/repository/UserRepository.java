package com.kaynaak.rest.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kaynaak.rest.entity.User;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Friday, 9/28/2018 11:22 AM
 * Email: cuongnd@vega.com.vn
 * Project: mychef
 */
//select * from Customer where Email='nvhabk2@gmail.com'
public interface UserRepository extends JpaRepository<User, String> {
	//@Param("id") 
	@Query("SELECT t FROM User t WHERE t.email= :email")
    User findByEmail(@Param("email") String email);
}
