package com.aspire.project.service;

import com.aspire.project.Model.Loan;
import com.aspire.project.dto.LoanApprovalRequest;

public interface AdminServiceInterf {
	Loan approveLoan(LoanApprovalRequest loanApprovalRequest);
}
