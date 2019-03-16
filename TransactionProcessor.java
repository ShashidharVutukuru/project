package assignment.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import assignment.exception.InvalidInputException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Transaction Processor class to process the transactions.
 * @author Shashidhar Vutukuru
 * Date : 03/16/2019
 *
 */
public class TransactionProcessor {

	/*
	 * main method to test the functionality by calling getFraudulentTransactions
	 * method.
	 */
	public static void main(String[] args) {

		List<String> list = new ArrayList<String>(
				Arrays.asList("10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 20.00",
						"10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 110.00",
						"20d7ce2f43e35fa57d1bbf8b1e3, 2014-04-29T13:15:54, 90.00"));

		List<String> creditCards = new ArrayList<>();
		try {
			creditCards = getFraudulentTransactions(list, "2014-04-29", 100.00);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		System.out.println(creditCards);
	}

	/**
	 * Returns a list of fraudulent cards that have exceeded the threshold limit.
	 * 
	 * @param List<String> transaction list of transactions
	 * @param String Date date on which we need check for the fraud transactions.
	 * @param double  threshold limit of the transaction.
	 * @return List<String> of fraudulent credit cards.
	 * @throws InvalidInputException If the input data is invalid.
		
		Assumptions:  
		 Date: Expecting the date format in the YYYY-MM-DD
		 Also each transaction entry is valid,with all the three elements(creditcardHash,date and amount).
		 
		 Run Time Complexity: Runs in O(n) as we need to go through the whole transaction list.
		 
	 */
	
	public static List<String> getFraudulentTransactions(final List<String> transactions, final String date,
			final double threshold) throws InvalidInputException {
		List<String> fraudulentCards = new ArrayList<String>();

		if (transactions == null || transactions.size() == 0) {
			return fraudulentCards;
		}
		
		if (date == null) {
			throw new InvalidInputException("Please enter a valid date");
		}
		
		Set<String> visitedCards = new HashSet<>();

		LocalDate tranDate = LocalDate.parse(date);
		Map<String, Double> transactionMap = new HashMap<>();
		List<String[]> transacList = transactions.stream().map(s -> s.split(",")).collect(Collectors.toList());
		
		for (String[] tranArr : transacList) {
			if (tranArr != null && tranArr.length > 2) {
				Transaction tr = new Transaction(tranArr[0].trim(),
						tranArr[1].trim().substring(0, tranArr[1].indexOf("T") - 1), Double.valueOf(tranArr[2].trim()));
				
				if (ChronoUnit.DAYS.between(tr.getDate(), tranDate) == 0) {
					String creditCard = tr.getCreditCardHash();
					Double trAmt = tr.getTransactionAmt();
				
					if (transactionMap.containsKey(creditCard)) {
						Double totalTransactAmt = transactionMap.get(creditCard);
						totalTransactAmt += trAmt;
						transactionMap.put(creditCard, totalTransactAmt);
						
						if (totalTransactAmt > threshold && !visitedCards.contains(creditCard)) {
							addCards(fraudulentCards, visitedCards, tr);
						}
					} else {
						transactionMap.put(creditCard, trAmt);
						if (trAmt > threshold && !visitedCards.contains(creditCard)) {
							addCards(fraudulentCards, visitedCards, tr);
						}
					}
				}
			}
		}
		return fraudulentCards;
	}

	/*
	 * Helper method to add the cards to the required data structures.
	 */
	private static void addCards(List<String> fraudulentCards, Set<String> visitedCards, Transaction tr) {
		visitedCards.add(tr.getCreditCardHash());
		fraudulentCards.add(tr.getCreditCardHash());
	}
}
