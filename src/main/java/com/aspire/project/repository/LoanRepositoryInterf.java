package com.aspire.project.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aspire.project.Status;
import com.aspire.project.Model.Loan;

public interface LoanRepositoryInterf extends JpaRepository<Loan,Integer>{
	Optional<Loan> findByLoanStatus(Status status);
	
}
