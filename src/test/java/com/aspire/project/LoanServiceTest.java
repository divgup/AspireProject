package com.aspire.project;
import static org.mockito.Mockito.*;

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
import com.aspire.project.Model.Loan;
import com.aspire.project.repository.CustomerRepoInterf;
import com.aspire.project.repository.LoanRepositoryInterf;
import com.aspire.project.serviceImpl.LoanServiceImpl;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoanServiceTest {

	@Mock
	LoanRepositoryInterf loanRepository;
	@InjectMocks
    private LoanServiceImpl loanService;
	
	@Mock 
	CustomerRepoInterf customerService;
	private Loan loan;
	
	@Before
    public void setup(){

	    Customer customer = new Customer("aditya","mntr123@gmail.com");
        loan = loan.builder()
                .id(1)
                .loanTerm(3)
                .amount(10)
                .loanStatus(Status.PENDING)
                .customer(customer)
                .build();
    }
	@Test
	public void givenLoanObjectWhenSaveLoanThenReturnLoanObject(){
        // given - precondition or setup
//        given(loanRepository.findById(loan.getId()))
//                .willReturn(Optional.empty());

        given(loanRepository.save(loan)).willReturn(loan);

        //System.out.println(loanRepository);
        //System.out.println(loanService);

        // when -  action or the behaviour that we are going test
        Loan savedLoan = loanService.save(loan);

        System.out.println(savedLoan);
        // then - verify the output
        assertThat(savedLoan).isNotNull();
    }
//
	@Test
    public void givenLoanIdWhenGetLoanByIdThenReturnLoanObject(){
        // given
        given(loanRepository.findById(1)).willReturn(Optional.of(loan));

        // when
        Loan savedLoan = loanService.findById(loan.getId()).get();

//        // then
        assertThat(savedLoan).isNotNull();

    }
	
	
}
