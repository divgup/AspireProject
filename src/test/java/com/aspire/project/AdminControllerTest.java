package com.aspire.project;

import static org.junit.Assert.assertEquals;

import org.junit.Before;

import com.aspire.project.dto.LoanApprovalRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.aspire.project.Model.Customer;
import com.aspire.project.Model.Loan;

public class AdminControllerTest extends ProjectApplicationTests{
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		super.setUp();
	}
	@Test
	  public void approveLoan() throws Exception {
	      String uri = "/admin/approve";
	      LoanApprovalRequest loanApprovalReq = new LoanApprovalRequest();
	      loanApprovalReq.setLoanId(1);
	      String inputJson = super.mapToJson(loanApprovalReq);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
	              .contentType(MediaType.APPLICATION_JSON_VALUE)
	              .content(inputJson)).andReturn();
	
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	}
}
