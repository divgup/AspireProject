package com.aspire.project.service;
import com.aspire.project.Model.Repayment;
import com.aspire.project.dto.RepaymentRequest;
import com.aspire.project.Model.Loan;

public interface RepaymentServiceInterf {
	String create(int loanId);
	String repayLoan(int loanId, RepaymentRequest repayment);
}
