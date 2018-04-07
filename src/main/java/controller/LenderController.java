package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import service.TrustlineService;
import entity.Amount;
import entity.PaymentType;

/**
 * Rest controller for lender
 * 
 *
 */
@RestController
public class LenderController {
	Amount existingLenderBalance;
	TrustlineService lenderTrustlineService;
	
	/**
	 * receives payment from the borrower,updates trustline and existing balance
	 * @return updated Amount
	 */
	@RequestMapping("/lender/payment")
	public Amount receivePayment() {
		final String uri = "http://localhost:8080/borrower/transaction";

		RestTemplate restTemplate = new RestTemplate();
		Amount result = restTemplate.getForObject(uri, Amount.class);

		existingLenderBalance = lenderTrustlineService.updateTrustLine(existingLenderBalance,result, PaymentType.RECEIVE);

		return existingLenderBalance;
	}
	/**
	 * pays borrower, updates trustline balance and existing balance
	 * @param amountSend
	 * @return
	 */
	@RequestMapping("/lender/payment")
	public ResponseEntity<Amount> sendPayment(@RequestBody Amount amountSend) {
	    if(amountSend != null){
	    	existingLenderBalance = lenderTrustlineService.updateTrustLine(existingLenderBalance,amountSend,PaymentType.SEND);
	    }
	    return new ResponseEntity<Amount>(amountSend, HttpStatus.OK);
	}

	/**
	 * Gets the current transaction details
	 * @return
	 */
	@RequestMapping("/lender/transaction")
	public Amount getTransaction(){
		return existingLenderBalance;
	}
}
