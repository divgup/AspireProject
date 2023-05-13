package com.aspire.project.serviceImpl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspire.project.repository.RepaymentRepoInterf;
import com.aspire.project.service.LoanServiceInterf;
import com.aspire.project.service.RepaymentServiceInterf;
import com.aspire.project.Status;
import com.aspire.project.Model.Loan;
import com.aspire.project.Model.Repayment;
import com.aspire.project.dto.RepaymentRequest;
import com.aspire.project.exception.LoanServiceException;
import com.aspire.project.exception.RepaymentServiceException;

@Service
public class RepaymentServiceImpl implements RepaymentServiceInterf {
	@Autowired
	RepaymentRepoInterf repaymentRepoInterf;
	@Autowired
	LoanServiceInterf loanServiceInterf;
	public String create(int loanId) {
		Optional<Loan> loan= loanServiceInterf.findById(loanId);
		if(loan.get()==null) {
			throw new LoanServiceException("Loan with id doesn't exist");
		}
		int amount = loan.get().getAmount();
		int loanTerm = loan.get().getLoanTerm();
		Date todaysDate = new Date(new java.util.Date().getTime());
		Date futureDate=todaysDate;
		List<Repayment> repayments = new ArrayList<>();
		for(int i = 0 ; i < loanTerm ; i++)
		{
			futureDate=this.addDays(futureDate, 7);
			Repayment repayment = Repayment.builder()
					.actualAmount(amount*1.0/loanTerm).repaymentnumber(i+1).loan(loan.get()).paymentstatus(Status.PENDING).paidAmount(0.0).date(futureDate)
					.build();
			repayments.add(repayment);
		}
		repaymentRepoInterf.saveAll(repayments);
		return "Repayment for loan id= "+loanId+ "created";
	}
	public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
    }
	public String repayLoan(int loanId, RepaymentRequest repayment) {
		Repayment repay = repaymentRepoInterf.findTopByLoanIdEqualsAndPaymentstatusEquals(loanId,Status.PENDING);
		if(repay==null) {
			System.out.println("No need to repay again");
			throw new RepaymentServiceException("No need to repay again, loan already paid !");
		}
		int amount = repayment.getAmount();
		Optional<Loan> loan = loanServiceInterf.findById(loanId);
		int loanAmount = repaymentRepoInterf.getLoanAmount(loanId);
		int repaynum = repay.getRepaymentnumber();
		int amount_paid = repaymentRepoInterf.getTotalAmountPaid(loanId);
		int amount_to_be_paid = repaymentRepoInterf.getTotalAmountToBePaid(loanId,repaynum);
		int amount_remaining = amount_to_be_paid-amount_paid;
		//System.out.println("amountToBePaid is " +amount_to_be_paid);
		int total_amount_paid = amount+amount_paid;
		int validAmount = loanAmount-total_amount_paid;
		System.out.println("paid amount = "+amount_paid);
		if(repay!=null) {
			if(amount >= amount_remaining && total_amount_paid <=loanAmount) {
				repay.setPaidAmount(amount);
				repay.setPaymentstatus(Status.PAID);
				repaymentRepoInterf.save(repay);
				if(amount+amount_paid==loanAmount) {
					List<Repayment> remRepayments = repaymentRepoInterf.findAllByLoanIdEqualsAndPaymentstatusEquals(loanId,Status.PENDING);
					for(int i = 0 ; i < remRepayments.size() ; i++) {
						Repayment r = remRepayments.get(i);
						r.setPaymentstatus(Status.PAID);
						repaymentRepoInterf.save(r);
					}
					loan.get().setLoanStatus(Status.PAID);
					loanServiceInterf.save(loan.get());
				}
				repaymentRepoInterf.save(repay);
			}
			else if(amount+amount_paid > loanAmount) {
				
				throw new RepaymentServiceException("Please enter valid repayment. Repayment amount cannot be greater than "+validAmount);
			}
			else {
				throw new RepaymentServiceException("Please repay amount greater than "+ amount_remaining);			
			}
		}
		
		return "Repayment for loan id= "+loanId+ " successful";
		
	}

}
