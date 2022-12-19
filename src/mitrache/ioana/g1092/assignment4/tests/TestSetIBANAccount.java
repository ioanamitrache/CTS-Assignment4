package mitrache.ioana.g1092.assignment4.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import mitrache.ioana.g1092.assignment4.Account;
import mitrache.ioana.g1092.assignment4.BankingInterfaceStub;
import mitrache.ioana.g1092.assignment4.exceptions.AccountException;
import mitrache.ioana.g1092.assignment4.exceptions.InvalidIBANException;
import mitrache.ioana.g1092.assignment4.exceptions.NoAvailableFundsException;
import mitrache.ioana.g1092.assignment4.exceptions.NoServiceException;
import mitrache.ioana.g1092.assignment4.external.BankingInterface;
import mitrache.ioana.g1092.assignment4.external.PaymentsException;

public class TestSetIBANAccount {
	
	//test fixture
	Account account;
	BankingInterfaceStub bankingInterface;
	String iban = "RO12INGB0000000000000000";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		account = new Account();
	}

	@Test
	public void testSetIBANRight() throws InvalidIBANException, NoServiceException {
		bankingInterface = new BankingInterfaceStub();
		bankingInterface.setIsValidIBAN(true);
		
		account.setBankService(bankingInterface);
		account.setIban(iban);
		
		String accIBAN = account.getIban();
		
		assertEquals(iban, accIBAN);
	}
	
	@Category(CategoryImportant.class)
	@Test(expected = InvalidIBANException.class)
	public void TestIBANWrong() throws InvalidIBANException, NoServiceException {
		bankingInterface = new BankingInterfaceStub();
		bankingInterface.setIsValidIBAN(false);
		
		account.setBankService(bankingInterface);
		account.setIban(iban);
	}
	
	@Category(CategoryImportant.class)
	@Test(expected = NoServiceException.class)
	public void TestIBANReference() throws InvalidIBANException, NoServiceException {
		bankingInterface = null;
		account.setBankService(bankingInterface);
		account.setIban(iban);
	}

}
