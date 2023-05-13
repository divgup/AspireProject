package com.aspire.project.serviceImpl;

import org.springframework.stereotype.Service;

import com.aspire.project.Model.Customer;
import com.aspire.project.repository.CustomerRepoInterf;
import com.aspire.project.service.CustomerServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CustomerServiceImpl implements CustomerServiceInterf {
	@Autowired
	CustomerRepoInterf customerRepoInterf;
	
	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepoInterf.save(customer);
	}
	@Override
	public Customer findByEmail(String email) {
		return customerRepoInterf.findByEmail(email);
	}
	
}
