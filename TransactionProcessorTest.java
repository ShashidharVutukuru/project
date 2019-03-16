package assignment.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import assignment.exception.InvalidInputException;
import assignment.java.TransactionProcessor;

public class TransactionProcessorTest {
	private List<String> transactionList;

	@Before
	public void setup() {

		transactionList = new ArrayList<String>(Arrays.asList("10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 20.00",
				"10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 110.00",
				"20d7ce2f43e35fa57d1bbf8b1e3, 2014-04-29T13:15:54, 190.00", ", 2014-04-29T13:15:54, 90.00"));

	}

	/*
	 * Below test is to check, given valid data, the getFraudulentTransactions method returns data which is not NULL.
	 */
	@Test
	public void TestCheckForFraudulentTransactionsForNotNull() throws InvalidInputException {
		List<String> creditCards = TransactionProcessor.getFraudulentTransactions(transactionList, "2014-04-29", 100.00);
		assertNotNull(creditCards);
	}

	/*
	 * Below test is to check,if getFraudulentTransactions returns valid set of credit cards which are marked fraud.
	 */
	
	@Test
	public void TestCheckForFraudulentTransactionsWithValidData() throws InvalidInputException {
		List<String> creditCards = TransactionProcessor.getFraudulentTransactions(transactionList, "2014-04-29", 100.00);
		List<String> expectedList = new ArrayList<String>();
		expectedList.add("10d7ce2f43e35fa57d1bbf8b1e2");
		expectedList.add("20d7ce2f43e35fa57d1bbf8b1e3");
		assertEquals(creditCards, expectedList);
	}

	/*
	 * Below test is to check if the threshold limit functionality is working. Given
	 * a larger threshold limit, it should return an empty List.
	 */
	@Test
	public void TestCheckThresholdLimitForFraudulentTransactionsForEmpty() throws InvalidInputException {
		List<String> creditCards = TransactionProcessor.getFraudulentTransactions(transactionList, "2014-04-29", 70000.00);
		assertEquals(creditCards.size(), 0);
	}

	/*
	 * Below test is to check,if getFraudulentTransactions method throws
	 * InvalidInputException when the transaction list is empty.
	 */
	@Test(expected = InvalidInputException.class)
	public void TestInValidInputException() throws InvalidInputException {
		TransactionProcessor.getFraudulentTransactions(transactionList, null, 100.00);
	}

	@After
	public void tearDown() {
		transactionList = null;
	}
}
