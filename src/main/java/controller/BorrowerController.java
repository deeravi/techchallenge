package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import service.TrustlineService;
import entity.Amount;
import entity.PaymentType;

@RestController
public class BorrowerController {

	Amount existingBalance;
	TrustlineService borrowerTrustLine = new TrustlineService();

	/**
	 * pays lender, updates trustline balance and existing balance
	 * @param amountSend
	 * @return
	 */
	@RequestMapping(value = "/borrower/payment", method = RequestMethod.POST)
	public ResponseEntity<Amount> sendPayment(@RequestBody Amount amountSend) {
		if (amountSend != null) {
			existingBalance = borrowerTrustLine.updateTrustLine(
					existingBalance, amountSend, PaymentType.SEND);
		}
		return new ResponseEntity<Amount>(existingBalance, HttpStatus.OK);
	}

	/**
	 * Get the current borrower transaction details
	 * @return
	 */
	@RequestMapping("/borrower/transaction")
	public Amount getTransactionDetails() {
		return existingBalance;
	}

	/**
	 * receives payment from the lender,updates trustline and existing balance
	 * @return updated Amount
	 */
	@RequestMapping("/borrower/payment")
	public ResponseEntity<Amount> receivePayment() {
		final String uri = "http://localhost:8081/lender/transaction";

		RestTemplate restTemplate = new RestTemplate();
		Amount amount = restTemplate.getForObject(uri, Amount.class);
		existingBalance.setTrustLineAmt(existingBalance.getTrustLineAmt()
				+ amount.getMoneySent());
		// employee.setAmountSent(amount);
		Amount amountReceived = existingBalance;
		return new ResponseEntity<Amount>(amountReceived, HttpStatus.OK);
	}

}
