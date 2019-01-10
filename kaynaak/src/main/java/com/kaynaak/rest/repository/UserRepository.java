package com.kaynaak.rest.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kaynaak.rest.entity.User;

/*
 
serigne	22c168816e65c8f94c850c5f8023a494
1	c4ca4238a0b923820dcc509a6f75849b
serigne34	93b37c816633ef206ab4e4b47a043f23
Sovan	c3f6f597334141d33ba81173d0149cee
serigneD	a11095d321055dbaeec019498287c6e8

 */
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
	
	@Query("SELECT t FROM User t WHERE t.username= :username")
	List<User> findByUserName(@Param("username") String username);

	Optional<User> findById(Integer userId);
}
