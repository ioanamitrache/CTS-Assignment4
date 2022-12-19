package mitrache.ioana.g1092.assignment4;

import mitrache.ioana.g1092.assignment4.exceptions.AccountException;
import mitrache.ioana.g1092.assignment4.exceptions.InvalidIBANException;
import mitrache.ioana.g1092.assignment4.exceptions.NoAvailableFundsException;
import mitrache.ioana.g1092.assignment4.exceptions.NoServiceException;
import mitrache.ioana.g1092.assignment4.external.BankingInterface;
import mitrache.ioana.g1092.assignment4.external.PaymentsException;

public class Account {
	
	String iban;
	String owner;
	double balance;
	
	BankingInterface bankService;

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) throws InvalidIBANException, NoServiceException {
		if(this.bankService == null)
			throw new NoServiceException();
		if(bankService.isValidIBAN(iban))
			this.iban = iban;
		else
			throw new InvalidIBANException();
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setBankService(BankingInterface paymentService) {
		this.bankService = paymentService;
	}

	public Account(String iban, String owner, double balance, BankingInterface paymentService) throws InvalidIBANException, NoServiceException {
		this.setIban(iban);
		this.setOwner(owner);
		this.setBalance(balance);
	}
	
	
	public Account() {
		this.iban = "";
		this.owner = "";
		this.balance = 0.0;
		this.bankService = null;
	}

	public void payOnline(String destinationIBAN, double amount) throws NoAvailableFundsException, NoServiceException, InvalidIBANException, AccountException {
		if(this.balance < amount)
			throw new NoAvailableFundsException();
		
		if(this.bankService == null)
			throw new NoServiceException();
		
		if(!this.bankService.isValidIBAN(destinationIBAN))
			throw new InvalidIBANException();
		
		try {
			int result = this.bankService.payOnline(this.iban, destinationIBAN);
			
			switch(result) {
			case 1:
				this.balance -= amount;
				break;
			case 0:
				throw new NoServiceException();
			case -1:
				throw new AccountException();
			default:
				throw new UnsupportedOperationException();	
			}
			
		} catch (PaymentsException e) {
			throw new AccountException();
		}
	}
}
