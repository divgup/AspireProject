package com.aspire.project.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspire.project.Status;
import com.aspire.project.Model.Loan;
import com.aspire.project.dto.LoanApprovalRequest;
import com.aspire.project.service.AdminServiceInterf;
import com.aspire.project.service.LoanServiceInterf;

@Service
public class AdminServiceImpl implements AdminServiceInterf {
	@Autowired
	LoanServiceInterf loanServiceInterf;
	@Override
	public Loan approveLoan(LoanApprovalRequest loanApprovalRequest) {
		// TODO Auto-generated method stub
		Optional<Loan> loan = loanServiceInterf.findById(loanApprovalRequest.getLoanId());
		if(loan.isPresent()) {
			loan.get().setLoanStatus(Status.APPROVED);
			//loan.setLoanStatus(Status.APPROVED);
			return loanServiceInterf.save(loan.get());
		}
		return null;
		
	}

}
