package entity;

/**
 * 
 * Keeps track of the amount exchanged between borrower and lender
 */
public class Amount {

	private double balance;
	private double trustLineAmt;
	private double moneySent;
	private double moneyReceived;

	public Amount(){
		
	}
	
	public Amount(double moneySent, double moneyReceived) {
		super();
		this.moneySent = moneySent;
		this.moneyReceived = moneyReceived;
	}

	public double getMoneySent() {
		return moneySent;
	}

	public void setMoneySent(double moneySent) {
		this.moneySent = moneySent;
	}

	public double getMoneyReceived() {
		return moneyReceived;
	}

	public void setMoneyReceived(double moneyReceived) {
		this.moneyReceived = moneyReceived;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getTrustLineAmt() {
		return trustLineAmt;
	}

	public void setTrustLineAmt(double trustLineAmt) {
		this.trustLineAmt = trustLineAmt;
	}

}
