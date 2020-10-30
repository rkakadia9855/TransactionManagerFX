/**
 * This class is a child class of the Account class. It represents all the Savings bank account
 * @author John Juarez, Rudra Kakadia
 */
package application;
public class Savings extends Account {
  
  private boolean isLoyal;

  /**
   * The constructor calls the super constructor to intialize the account holder, balance, and 
   * dateOpen fields
   * @param holder - the profile object that represents a person opening an account
   * @param balance - the amount that the person initially deposits into the bank
   * @param dateOpen - the date when the account is being opened
   */
	public Savings(Profile holder, double balance, Date dateOpen) {
		super(holder, balance, dateOpen);
		
	}

	/**
	 * checks if the account holder is a loyal customer
	 * @return true if loyal, false otherwise
	 */
	public boolean isLoyal() {
		return isLoyal;
	}
	
	/**
	 * Changes the value of isloyal to specified value
	 * @param isLoyal - the new value
	 */
	public void setLoyal(boolean isLoyal) {
		this.isLoyal = isLoyal;
	}

	@Override
	/**
     * Calculates the monthly interest rate for this account type
     * @return - the amount of interest for this account type
     */
	public double monthlyInterest() {
		double interest = 0;
		
		if(isLoyal) {
			interest = 0.0035/12;
		}else {
			interest = 0.0025/12;
		}
		return interest;
	}

	@Override
	/**
     * Calculates the monthly fee that the account holder needs to pay for this account
     * @return - the amount that the account holder needs to pay
     */
	public double monthlyFee() {
		double fee = 0;
		if(super.getBalance() < 300) {
			fee = 5;
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
      if((account instanceof Savings) && super.equals(account))
        return true;
      else 
        return false;
    }
	
	@Override
	/**
     * Builds the string representation of this account
     * @return - the string representation of this account
     */
    public String toString() { 
     
      String account = "*Savings*" + super.toString();
      if(this.isLoyal()) {
        account = account + "*special Savings account*";
      }
      
      return account;
    }

}
