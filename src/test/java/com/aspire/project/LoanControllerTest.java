//package com.aspire.project;
//
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//

//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//
//public class LoanTest extends AbstractTest {
//    @Override
//    @Before
//    public void setUp() {
//        super.setUp();
//    }
//
//
//    @Test
//    public void addLoan() throws Exception {
//        String uri = "/create";
//        Loan loan = new Loan();
//        Customer customer = new Customer("aditya","mntr123@gmail.com");
//        loan.setLoanStatus(Status.PENDING);
//        loan.setAmount(10);
//        loan.setLoanTerm(3);
//        loan.setCustomer(customer);
//
//        String inputJson = super.mapToJson(loan);
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(inputJson)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(201, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        System.out.println(content);
//        //assertEquals(content, "{\"id\":1,\"amount\":\"10\",\"email\":\"adityamntr@gmail.com\",\"destination\":\"Africa\",\"travellerCount\":6,\"budget\":423}");
//    }
//
//}

//package com.aspire.project;
//
//import com.aspire.project.Model.Loan;
//import com.aspire.project.Model.Customer;
//import com.aspire.project.Status;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//

//import static org.junit.Assert.assertNotNull;
//
//public class LoanTest extends AbstractTest{
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    private HttpHeaders headers = new HttpHeaders();
//
//    @Before
//    public void setUp() {
//        headers.setContentType(MediaType.APPLICATION_JSON);
//    }
//
//    @Test
//    public void addLoan() throws Exception {
//        String uri = "/create";
//        Loan loan = new Loan();
//        Customer customer = new Customer("aditya","mntr123@gmail.com");
//        loan.setLoanStatus(Status.PENDING);
//        loan.setAmount(10);
//        loan.setLoanTerm(3);
//        loan.setCustomer(customer);
//
//        HttpEntity<Loan> entity = new HttpEntity<>(loan, headers);
//        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort(uri), HttpMethod.POST, entity, String.class);
//
//        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
//        assertNotNull(actual);
//        assertEquals(response.getStatusCodeValue(), 201);
//    }
//
//    private String createURLWithPort(String uri) {
//        return "http://localhost:" + port + uri;
//    }
//}
package com.aspire.project;
import static org.junit.Assert.assertEquals;
import com.aspire.project.Model.Loan;
import com.aspire.project.Model.Customer;
import org.springframework.http.MediaType;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class LoanControllerTest extends ProjectApplicationTests{


	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		super.setUp();
	}
	
	@Test
	  public void addLoan() throws Exception {
	      String uri = "/loan/request";
	      Loan loan = new Loan();
	      Customer customer = new Customer("aditya","mntr123@gmail.com");
	      loan.setLoanStatus(Status.PENDING);
	      loan.setAmount(10);
	      loan.setLoanTerm(3);
	      loan.setCustomer(customer);
	      String inputJson = super.mapToJson(loan);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	              .contentType(MediaType.APPLICATION_JSON_VALUE)
	              .content(inputJson)).andReturn();
	
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(201, status);
	}
	@Test
	  public void getCustomerLoans() throws Exception {
	      String uri = "/loan/customer/all";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	              .contentType(MediaType.APPLICATION_JSON_VALUE)
	              .param("email", "mntr123@gmail.com")
	              ).andReturn();
	
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	}


}
