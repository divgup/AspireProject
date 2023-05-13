package com.aspire.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aspire.project.Model.Customer;

public interface CustomerRepoInterf extends JpaRepository<Customer,Integer>{
	//@Query(value="select * from customer a where a.email=:email",nativeQuery=true)
	public Customer findByEmail(String email);
}
