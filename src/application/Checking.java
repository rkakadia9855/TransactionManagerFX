/**
 * This class is a child class of the Account class. It represents all the Checking bank account
 * @author John Juarez, Rudra Kakadia
 */
package application;
public class Checking extends Account {
  
  private boolean directDeposit;
	
    /**
     * The constructor calls the super constructor to intialize the account holder, balance, and 
     * dateOpen fields
     * @param holder - the profile object that represents a person opening an account
     * @param balance - the amount that the person initially deposits into the bank
     * @param dateOpen - the date when the account is being opened
     */
	public Checking(Profile holder, double balance, Date dateOpen) {
		super(holder, balance, dateOpen);
	}
	
	/**
	 * Checks if direct deposit has been setup for this account
	 * @return true if direct deposit is setup, false otherwise
	 */
	public boolean isDirectDeposit() {
		return directDeposit;
	}
	
	/**
	 * Sets the direct deposit to the passed value
	 * @param directDeposit - whether or not the direct deposit should be setup for this account
	 */
	public void setDirectDeposit(boolean directDeposit) {
		this.directDeposit = directDeposit;
	}

	@Override 
	/**
	 * Calculates the monthly interest rate for this account type
	 * @return - the amount of interest for this account type
	 */
	public double monthlyInterest() {
		double interest = 0.0005/12;
		return interest;
	}

	@Override
	/**
	 * Calculates the monthly fee that the account holder needs to pay for this account
	 * @return - the amount that the account holder needs to pay
	 */
	public double monthlyFee() {
		double fee = 0;
		if(super.getBalance() < 1500) {
		  if(!directDeposit) {
		    fee = 25;
		  }
		  else {
		    fee = 0;
		  }
		}
		return fee;
	}
	
	@Override
	/**
     * Checks if the account in the argument is the same as this account, that is 
     * the same person holds both the account and the account are of same type
     * @param account - the account that needs to be compared with this account
     * @return - true if the accounts are same, false otherwise
     */
	public boolean equals(Account account) {
      if((account instanceof Checking) && super.equals(account)) {
        return true;
      }
      else {
        return false;
      }
    }
	
	@Override
	/**
     * Builds the string representation of this account
     * @return - the string representation of this account
     */
	public String toString() { 

      String account = "*Checking*"+ super.toString();
      if(this.isDirectDeposit()) {
        account = account + "*direct deposit account*";
      }
      
      return account;
  }

}
