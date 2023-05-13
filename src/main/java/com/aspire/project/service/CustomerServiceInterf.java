package com.aspire.project.service;

import com.aspire.project.Model.Customer;

public interface CustomerServiceInterf {
	public Customer createCustomer(Customer customer);
	public Customer findByEmail(String email);
}
