package com.aspire.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspire.project.Model.Loan;
import com.aspire.project.dto.LoanCreateRequest;
import com.aspire.project.service.LoanServiceInterf;


@RestController
public class LoanController {
	@Autowired
	LoanServiceInterf loanserviceInterf;
	@PostMapping("/request")
	public ResponseEntity requestLoan(@RequestBody @Valid LoanCreateRequest createLoan) {
		return new ResponseEntity(loanserviceInterf.create(createLoan),HttpStatus.CREATED);
	}
	@GetMapping("/customer/loans")
	public ResponseEntity getLoansByCustomerPhone(@RequestParam("Email") String email) {
		return new ResponseEntity(loanserviceInterf.getLoansByCustomerEmail(email), HttpStatus.OK);
	}
}
