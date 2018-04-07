package entity;

public class Customer {
	long id;
	long ledgerId;
	Amount transactionAmount;
	double trustLineBalance;
	
	Customer(){
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getLedgerId() {
		return ledgerId;
	}

	public void setLedgerId(long ledgerId) {
		this.ledgerId = ledgerId;
	}

	public Amount getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Amount transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public double getTrustLineBalance() {
		return trustLineBalance;
	}

	public void setTrustLineBalance(double trustLineBalance) {
		this.trustLineBalance = trustLineBalance;
	}
	
}
