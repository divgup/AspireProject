package com.aspire.project.service;
import com.aspire.project.Model.Repayment;
import com.aspire.project.dto.CreateRepaymentRequest;

import com.aspire.project.dto.RepaymentRequest;
import com.aspire.project.Model.Loan;

public interface RepaymentServiceInterf {
	String create(CreateRepaymentRequest createRepayment);
	String repayLoan(RepaymentRequest repayment);
}
