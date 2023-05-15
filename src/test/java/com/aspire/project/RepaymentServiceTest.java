package com.aspire.project;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.BDDMockito.given;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import com.aspire.project.Model.Customer;
import com.aspire.project.dto.RepaymentRequest;
import com.aspire.project.Model.Repayment;
import com.aspire.project.repository.RepaymentRepoInterf;
import com.aspire.project.serviceImpl.LoanServiceImpl;
import com.aspire.project.serviceImpl.RepaymentServiceImpl;
import com.aspire.project.Model.Loan;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RepaymentServiceTest {
	@Mock
	RepaymentRepoInterf repaymentRepository;
	@InjectMocks
    private RepaymentServiceImpl repayService;
	
	private Loan loan;
	private Repayment repayment;
	private List<Repayment> ListOfRepayments;
	@Before
    public void setup(){
		
	    Customer customer = new Customer("aditya","mntr123@gmail.com");
	    int n = 3;
        loan = loan.builder()
                .id(1)
                .loanTerm(n)
                .amount(10)
                .loanStatus(Status.PENDING)
                .customer(customer)
                .build();
        ListOfRepayments = new ArrayList<>();
        for(int i = 0 ; i < n ; i++) {
	       Repayment repayment = Repayment.builder()
	                .id(1)
	                .actualAmount(10/n)
	                .repaymentnumber(i)
	                .paidAmount(0.0)
	                .paymentstatus(Status.PENDING)
	                .loan(loan)
	                .date(new Date(new java.util.Date().getTime()))
	                .build();
	       //System.out.println("check here "+repayment);
	       ListOfRepayments.add(repayment);
        }
    }
	@Test
	public void givenRepaymentListObjectWhenSaveRepaymentThenReturnRepaymentListObject() {
		
		given(repaymentRepository.saveAll(ListOfRepayments)).willReturn(ListOfRepayments);
		
		List<Repayment> list = repayService.saveAll(ListOfRepayments);
		
		assertThat(list).isNotNull();

	}
	
}
