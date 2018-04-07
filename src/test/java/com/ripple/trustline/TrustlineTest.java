package com.ripple.trustline;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;






import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import app.App;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.BorrowerController;
import entity.Amount;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(BorrowerController.class)
@ContextConfiguration(classes={App.class})
public class TrustlineTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private BorrowerController borrowerController;

	@Before
	public  void setUp() {
		this.mvc = MockMvcBuilders.standaloneSetup(borrowerController).build();

	}

	@Test
	public void testSimpleSend() throws Exception {
		// Testing a simple send between Bob and Alice - Bob sends 10 and Alice receives 10. Trustlinebalance =-10 for Bob
		 //CREATE
		Amount amountToSend = new Amount(10,0);
		byte[] amountBytes = (byte[])toJson(amountToSend);
		 mvc.perform(
	                post("/borrower/payment")
	                        .accept(MediaType.APPLICATION_JSON)
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(amountBytes))
	                .andExpect(status().isOk());
	}

	@Test
	public void reverseSend() throws Exception {
		mvc.perform(
                get("/borrower/transaction")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
	}

	@Test
	public void retrieveTrustlineBalance() throws Exception {
		mvc.perform(
                get("/borrower/transaction")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
	}
	

	@Test
	public void paymentFailure() throws Exception {
		mvc.perform(
                get("/lender/transaction")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
	}

	@Test
	public void borrowerPayment() throws Exception {
		mvc.perform(
                get("/borrower/payment")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

	}
	
	 private byte[] toJson(Object r) throws Exception {
	        ObjectMapper map = new ObjectMapper();
	        return map.writeValueAsString(r).getBytes();
	    }

}
