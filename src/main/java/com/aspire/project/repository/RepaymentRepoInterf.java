package com.aspire.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aspire.project.Status;
import com.aspire.project.Model.Repayment;

public interface RepaymentRepoInterf extends JpaRepository<Repayment,Integer>{
	@Query(value="select sum(c.paid_amount) from repayment c where c.loan_id=?1",nativeQuery=true)
	int getTotalAmountPaid(int loanId);
	
	@Query(value="select sum(c.actual_amount) from repayment c where c.loan_id=?1 and c.repaymentnumber <=?2",nativeQuery=true)	
	int getTotalAmountToBePaid(int loanId,int repaynum);
	
	@Query(value="select sum(c.actual_amount) from repayment c where c.loan_id=?1",nativeQuery=true)	
	int getLoanAmount(int loanId);
	
	//Repayment findAllByLoanIdEqualsAndPaymentstatusEquals(String status);
	
	Repayment findTopByLoanIdEqualsAndPaymentstatusEquals(int loanId,Status pending);

	List<Repayment> findAllByLoanIdEqualsAndPaymentstatusEquals(int loanId, Status pending);
}
