package com.aspire.project;

import static org.junit.Assert.assertEquals;
import com.aspire.project.dto.CreateRepaymentRequest;
import com.aspire.project.dto.RepaymentRequest;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.aspire.project.Model.Customer;
import com.aspire.project.Model.Loan;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RepaymentContollerTest extends ProjectApplicationTests{
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		super.setUp();
	}
	//JUnit test for checking generated repayment for given loan Id
	@Test
	public void createRepayment() throws Exception {
	      String uri = "/repayment/create";
	      CreateRepaymentRequest repaymentReq = new CreateRepaymentRequest();
	      repaymentReq.setLoanId(1);
	      String inputJson = super.mapToJson(repaymentReq);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	              .contentType(MediaType.APPLICATION_JSON_VALUE)
	              .content(inputJson)).andReturn();
	
	      int status = mvcResult.getResponse().getStatus();
	      System.out.println("Prior response "+mvcResult.getResponse().getContentAsString());
	      
	      assertEquals(200, status);
	}
	
	//JUnit test for checking nth repayment is successful or not for given loan Id
	@Test
	public void repayLoan() throws Exception {
	      String uri = "/repayment/pay";
	      RepaymentRequest repaymentReq = new RepaymentRequest();
	      repaymentReq.setLoanId(1);
	      repaymentReq.setAmount(5);	      
	      String inputJson = super.mapToJson(repaymentReq);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
	              .contentType(MediaType.APPLICATION_JSON_VALUE)
	              .content(inputJson)).andReturn();
	
	      int status = mvcResult.getResponse().getStatus();
	      System.out.println("Response Generated "+mvcResult.getResponse().getContentAsString());
	      assertEquals(200, status);
	}
}
