package assignment.java;
import java.time.LocalDate;

/**
 * Class to hold the transaction details
 * @author Shashidhar Vutukuru
 * Date : 03/16/2019
 *
 */
public class Transaction {
	private String creditCardHash;
    LocalDate date;

	private double transactionAmt;

	public Transaction(String creditCardHash, String date, double transactionAmt) {
		super();
		this.creditCardHash = creditCardHash;
		this.date =  LocalDate.parse(date);
		this.transactionAmt = transactionAmt;
	}

	
	public String getCreditCardHash() {
		return creditCardHash;
	}

	public void setCreditCardHash(String creditCardHash) {
		this.creditCardHash = creditCardHash;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(String date1) {
		this.date = LocalDate.parse(date1);
	}

	public double getTransactionAmt() {
		return transactionAmt;
	}

	public void setTransactionAmt(double transactionAmt) {
		this.transactionAmt = transactionAmt;
	}


	@Override
	public String toString() {
		return "Transaction [creditCardHash=" + creditCardHash + ", date=" + date + ", transactionAmt="
				+ transactionAmt + "]";
	}

	
}
