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
import com.aspire.project.dto.RepaymentRequest;
import com.aspire.project.exception.RepaymentServiceException;
import com.aspire.project.service.LoanServiceInterf;

@RestController
@RequestMapping("/repayment")
public class RepaymentController {
	@Autowired
	RepaymentServiceInterf repaymentserviceInterf;
	@PutMapping("/repay/{loanId}")
	public ResponseEntity makeRepayment(@PathVariable(value = "loanId") int loanId,@RequestBody RepaymentRequest repayment) {
		try {
			repaymentserviceInterf.repayLoan(loanId,repayment);
		}catch(RepaymentServiceException e){
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity("Repayment for loanId = "+ loanId + " success",HttpStatus.OK);
	}
	@PostMapping("/create/{loanId}")
	public ResponseEntity createRepayment(@PathVariable(value = "loanId") int loanId) {
		try {
			repaymentserviceInterf.create(loanId);
		}
		catch(RuntimeException e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity("Repayment for loanId = "+ loanId + " created",HttpStatus.OK);
	}
}
