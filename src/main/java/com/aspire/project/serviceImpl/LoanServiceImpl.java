package com.aspire.project.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspire.project.Status;
import com.aspire.project.Model.Customer;
import com.aspire.project.Model.Loan;
import com.aspire.project.dto.LoanCreateRequest;
import com.aspire.project.repository.LoanRepositoryInterf;
import com.aspire.project.service.CustomerServiceInterf;
import com.aspire.project.service.LoanServiceInterf;
import com.aspire.project.service.RepaymentServiceInterf;

@Service
public class LoanServiceImpl implements LoanServiceInterf{
	@Autowired
	LoanRepositoryInterf loanRepositoryInterf;
	@Autowired 
	CustomerServiceInterf customerServiceInterf;
//	@Autowired
//	RepaymentServiceInterf repaymentserviceInterf;
	@Override
	public Loan create(LoanCreateRequest loanrequest) {
		
//		return new ResponseEntity("Loan With this ID Already Exist!",HttpStatus.BAD_REQUEST);
		Loan loan = loanrequest.toLoan();
		int tenure = loan.getLoanTerm();
		int amt = loan.getAmount();
		Customer customer = loan.getCustomer();		
		//System.out.println("email is "+customer.getEmail());
		Customer CustomerFromDb = customerServiceInterf.findByEmail(customer.getEmail());

		if(CustomerFromDb == null) {
			CustomerFromDb = customerServiceInterf.createCustomer(customer);
		}
		loan.setCustomer(CustomerFromDb);
		loan.setLoanStatus(Status.PENDING);
		loanRepositoryInterf.save(loan);
//		repaymentserviceInterf.create(tenure,amt,loan);
		return loan;
	}
	@Override
	public Optional<Loan> findById(int loanId){
		return loanRepositoryInterf.findById(loanId);
	}
	@Override
	public Loan save(Loan loan) {
		return loanRepositoryInterf.save(loan);
	}
	@Override
	public List<Loan> getLoansByCustomerEmail(String email) {
		//System.out.println(email);
		Customer customer = customerServiceInterf.findByEmail(email);
		return customer.getLoanList();
//		return loanRepositoryInterf.findById(PhoneNum);
	}
}
