package com.aspire.project.controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aspire.project.service.AdminServiceInterf;
import com.aspire.project.Model.Loan;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminServiceInterf adminServiceInterf;
	@PutMapping("/approve/{Id}")
	public ResponseEntity approveLoan(@PathVariable("Id") int loanId) {
		
		Loan loan = adminServiceInterf.approveLoan(loanId);
		return new ResponseEntity("Loan with id = "+ loanId + " approved",HttpStatus.OK);
	}
}
