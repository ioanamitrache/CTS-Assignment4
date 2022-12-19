package mitrache.ioana.g1092.assignment4.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import mitrache.ioana.g1092.assignment4.Account;
import mitrache.ioana.g1092.assignment4.BankingInterfaceStub;
import mitrache.ioana.g1092.assignment4.exceptions.AccountException;
import mitrache.ioana.g1092.assignment4.exceptions.InvalidIBANException;
import mitrache.ioana.g1092.assignment4.exceptions.NoAvailableFundsException;
import mitrache.ioana.g1092.assignment4.exceptions.NoServiceException;

public class TestPayOnlineAccount {

	//test fixture
	Account account;
	BankingInterfaceStub bankingInterface;
	String destIBAN = "RO13INGB0000000000000001";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		account = new Account();
	}

	@Test
	public void testPayOnlineRight() throws InvalidIBANException, NoServiceException, NoAvailableFundsException, AccountException {
		double balance = 2567;
		account.setBalance(balance);
		
		bankingInterface = new BankingInterfaceStub();
		bankingInterface.setIsValidIBAN(true);
		bankingInterface.setPayOnline(1);
		bankingInterface.setSourceIBANValidity(true);
		bankingInterface.setDestinationIBANValidity(true);
		
		account.setBankService(bankingInterface);
		
		double amount = 250.99;
		account.payOnline(destIBAN, amount);
		
		double result = account.getBalance();
		double expectedResult = 2316.01;
		
		assertEquals(expectedResult, result, 0);
	}
	
	@Category(CategoryImportant.class)
	@Test(expected = NoAvailableFundsException.class)
	public void testPayOnlineWrongBalance() throws NoAvailableFundsException, NoServiceException, InvalidIBANException, AccountException {
		double balance = 200;
		account.setBalance(balance);
		
		bankingInterface = new BankingInterfaceStub();
		bankingInterface.setIsValidIBAN(true);
		bankingInterface.setPayOnline(1);
		bankingInterface.setSourceIBANValidity(true);
		bankingInterface.setDestinationIBANValidity(true);
		
		account.setBankService(bankingInterface);
		
		double amount = 255;
		account.payOnline(destIBAN, amount);
	}
	
	@Category(CategoryImportant.class)
	@Test(expected = NoServiceException.class)
	public void testPayOnlineReference() throws NoAvailableFundsException, NoServiceException, InvalidIBANException, AccountException {
		double balance = 200;
		account.setBalance(balance);
		
		bankingInterface = null;
		account.setBankService(bankingInterface);
		
		double amount = 43.76;
		account.payOnline(destIBAN, amount);
	}
	
	@Category(CategoryImportant.class)
	@Test(expected  = InvalidIBANException.class)
	public void testPayOnlineWrongDestinationIBAN() throws NoAvailableFundsException, NoServiceException, InvalidIBANException, AccountException {
		double balance = 2567;
		account.setBalance(balance);
		
		bankingInterface = new BankingInterfaceStub();
		bankingInterface.setIsValidIBAN(false);
		bankingInterface.setSourceIBANValidity(true);
		bankingInterface.setDestinationIBANValidity(false);
		
		account.setBankService(bankingInterface);
		
		double amount = 250.99;
		account.payOnline(destIBAN, amount);
	}
	
	@Category(CategoryImportant.class)
	@Test (expected = NoServiceException.class)
	public void testPayOnlineUnprocessedPayment() throws NoAvailableFundsException, NoServiceException, InvalidIBANException, AccountException {
		double balance = 2567;
		account.setBalance(balance);
		
		bankingInterface = new BankingInterfaceStub();
		bankingInterface.setIsValidIBAN(true);
		bankingInterface.setPayOnline(0);
		bankingInterface.setSourceIBANValidity(true);
		bankingInterface.setDestinationIBANValidity(true);
		
		account.setBankService(bankingInterface);
		
		double amount = 250.99;
		account.payOnline(destIBAN, amount);
	}
	
	@Category(CategoryImportant.class)
	@Test(expected = AccountException.class)
	public void testPayOnlineRejectedPayment() throws NoAvailableFundsException, NoServiceException, InvalidIBANException, AccountException {
		double balance = 2567;
		account.setBalance(balance);
		
		bankingInterface = new BankingInterfaceStub();
		bankingInterface.setIsValidIBAN(true);
		bankingInterface.setPayOnline(-1);
		bankingInterface.setSourceIBANValidity(true);
		bankingInterface.setDestinationIBANValidity(true);
		
		account.setBankService(bankingInterface);
		
		double amount = 250.99;
		account.payOnline(destIBAN, amount);
	}
	
	@Category(CategoryImportant.class)
	@Test(expected = UnsupportedOperationException.class)
	public void testPayOnlineDefaultException() throws NoAvailableFundsException, NoServiceException, InvalidIBANException, AccountException {
		double balance = 2567;
		account.setBalance(balance);
		
		bankingInterface = new BankingInterfaceStub();
		bankingInterface.setIsValidIBAN(true);
		bankingInterface.setPayOnline(-2);
		bankingInterface.setSourceIBANValidity(true);
		bankingInterface.setDestinationIBANValidity(true);
		
		account.setBankService(bankingInterface);
		
		double amount = 250.99;
		account.payOnline(destIBAN, amount);
	}
	
	@Category(CategoryImportant.class)
	@Test(expected = AccountException.class)
	public void testPayOnlineWrongSourceIBAN() throws NoAvailableFundsException, NoServiceException, InvalidIBANException, AccountException {
		double balance = 2567;
		account.setBalance(balance);
		
		bankingInterface = new BankingInterfaceStub();
		bankingInterface.setIsValidIBAN(true);
		bankingInterface.setPayOnline(-2);
		bankingInterface.setSourceIBANValidity(false);
		bankingInterface.setDestinationIBANValidity(true);
		
		account.setBankService(bankingInterface);
		
		double amount = 250.99;
		account.payOnline(destIBAN, amount);
	}

}
