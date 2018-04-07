package service;

import entity.Amount;
import entity.PaymentType;

/**
 * Updates trustline balance for every sender, receiver
 *
 */
public class TrustlineService {
	
	public Amount updateTrustLine(Amount existingBalance,Amount transactionAmount, PaymentType type) {

		switch (type) {
		case RECEIVE:
			if (existingBalance == null) {
				existingBalance = new Amount();
				existingBalance.setBalance(transactionAmount.getMoneySent());
			}

			else {
				existingBalance.setBalance(existingBalance.getBalance() + transactionAmount.getMoneySent());
			}
			
			existingBalance.setTrustLineAmt(existingBalance.getTrustLineAmt()
					+ transactionAmount.getMoneySent());
			existingBalance.setMoneySent(transactionAmount.getMoneySent());
			return existingBalance;
		case SEND:
			if (existingBalance == null) {
				existingBalance = new Amount();
				existingBalance.setBalance(-transactionAmount.getMoneySent());
			}

			else {
				existingBalance.setBalance(existingBalance.getBalance() - transactionAmount.getMoneySent());
			}
			existingBalance.setMoneySent(transactionAmount.getMoneySent());
			existingBalance.setTrustLineAmt(existingBalance.getTrustLineAmt()
					- transactionAmount.getMoneySent());

			return existingBalance;
		}
		
		return transactionAmount;
	}

}
