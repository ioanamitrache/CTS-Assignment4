package mitrache.ioana.g1092.assignment4;

import mitrache.ioana.g1092.assignment4.external.BankingInterface;
import mitrache.ioana.g1092.assignment4.external.PaymentsException;

public class BankingInterfaceStub implements BankingInterface{
	
	private boolean returnIsValidIBAN;
	private int returnPayOnline;
	private boolean isValidSourceIBAN;
	private boolean isValidDestinationIBAN;
	
	public void setIsValidIBAN(boolean returnIsValid) {
		this.returnIsValidIBAN = returnIsValid;
	}
	
	public void setPayOnline(int setPay) {
		this.returnPayOnline = setPay;
	}
	
	public void setSourceIBANValidity(boolean isValidSourceIBAN) {
		this.isValidSourceIBAN = isValidSourceIBAN;
	}
	public void setDestinationIBANValidity(boolean isValidDestinationIBAN) {
		this.isValidDestinationIBAN = isValidDestinationIBAN;
	}
	
	@Override
	public boolean isValidIBAN(String iban) {
		return returnIsValidIBAN;
	}

	@Override
	public int payOnline(String sourceIBAN, String destinationIBAN) throws PaymentsException {
		if(!isValidDestinationIBAN || !isValidSourceIBAN)
			throw new PaymentsException();
		return returnPayOnline;
	}

}
