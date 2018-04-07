package entity;

/**
 * 
 * Every single transaction that gets paid, a unique ledger id gets created,
 * amount is tracked and updates sender,receiver details for the same
 */
public class Payment {

	Long ledgerId;
	Amount amount;
	Customer sender;
	Customer receiver;

	Payment() {

	}

	public Long getLedgerId() {
		return ledgerId;
	}

	public void setLedgerId(Long ledgerId) {
		this.ledgerId = ledgerId;
	}

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	public Customer getSender() {
		return sender;
	}

	public void setSender(Customer sender) {
		this.sender = sender;
	}

	public Customer getReceiver() {
		return receiver;
	}

	public void setReceiver(Customer receiver) {
		this.receiver = receiver;
	}
}
