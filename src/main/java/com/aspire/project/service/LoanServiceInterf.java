package com.aspire.project.service;

import java.util.List;
import java.util.Optional;

import com.aspire.project.Status;
import com.aspire.project.Model.Loan;
import com.aspire.project.dto.LoanCreateRequest;

public interface LoanServiceInterf {

	Loan create(LoanCreateRequest loancreate);

	//Optional<Loan> findByLoanStatus(Status status);

	Loan save(Loan loan);

	Optional<Loan> findById(int loanId);
	List<Loan> getLoansByCustomerEmail(String email);
}
