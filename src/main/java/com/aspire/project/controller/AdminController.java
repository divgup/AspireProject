package com.aspire.project.controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aspire.project.service.AdminServiceInterf;
import com.aspire.project.Model.Loan;
import com.aspire.project.dto.LoanApprovalRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminServiceInterf adminServiceInterf;
	@PutMapping("/approve")
	public ResponseEntity approveLoan(@RequestBody LoanApprovalRequest loanApprovalRequest) {
		
		Loan loan = adminServiceInterf.approveLoan(loanApprovalRequest);
		if(loan==null)
			return new ResponseEntity("Loan with id = "+ loanApprovalRequest.getLoanId() + " doesn't exist",HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity("Loan with id = "+ loanApprovalRequest.getLoanId() + " approved",HttpStatus.OK);
	}
}
