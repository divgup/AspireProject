package com.aspire.project.service;
import com.aspire.project.Model.Repayment;
import com.aspire.project.dto.CreateRepaymentRequest;

import com.aspire.project.dto.RepaymentRequest;

import java.util.List;

import com.aspire.project.Model.Loan;

public interface RepaymentServiceInterf {
	String create(CreateRepaymentRequest createRepayment);
	List<Repayment> saveAll(List<Repayment> repaymentList);
	String repayLoan(RepaymentRequest repayment);
}
