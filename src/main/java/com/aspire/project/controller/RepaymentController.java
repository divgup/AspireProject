package com.aspire.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aspire.project.service.RepaymentServiceInterf;
import com.aspire.project.Model.Repayment;
import com.aspire.project.dto.CreateRepaymentRequest;
import com.aspire.project.dto.RepaymentRequest;
import com.aspire.project.exception.RepaymentServiceException;
import com.aspire.project.service.LoanServiceInterf;

@RestController
@RequestMapping("/repayment")
public class RepaymentController {
	@Autowired
	RepaymentServiceInterf repaymentserviceInterf;

	@PostMapping("/create")
	public ResponseEntity createRepayment(@RequestBody CreateRepaymentRequest createRepayment) {
		try {
			repaymentserviceInterf.create(createRepayment);
		}
		catch(RuntimeException e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity("Repayment for loanId = "+ createRepayment.getLoanId() + " created",HttpStatus.OK);
	}
	@PutMapping("/pay")
	public ResponseEntity makeRepayment(@RequestBody RepaymentRequest repayment) {
		try {
			repaymentserviceInterf.repayLoan(repayment);
		}catch(RepaymentServiceException e){
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity("Repayment for loanId = "+ repayment.getLoanId() + " success",HttpStatus.OK);
	}
}
