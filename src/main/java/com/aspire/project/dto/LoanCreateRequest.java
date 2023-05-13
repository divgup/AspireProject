package com.aspire.project.dto;

import com.aspire.project.Status;
import com.aspire.project.Model.Customer;
import com.aspire.project.Model.Loan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LoanCreateRequest {
	
	private int loanTerm;
	
	private double amount; 
	private Customer customer;
	public Loan toLoan() {
		return Loan.builder().amount(amount).loanTerm(loanTerm).customer(customer).build();
	}
}

