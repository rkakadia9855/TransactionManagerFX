/**
 * This class is a child class of the Account class. It represents all the Money market
 *  bank account
 * @author John Juarez, Rudra Kakadia
 */
package application;
public class MoneyMarket extends Account {
  
  private int withdrawals;

     /**
     * The constructor calls the super constructor to intialize the account holder, balance, and 
     * dateOpen fields
     * @param holder - the profile object that represents a person opening an account
     * @param balance - the amount that the person initially deposits into the bank
     * @param dateOpen - the date when the account is being opened
     */
	public MoneyMarket(Profile holder, double balance, Date dateOpen) {
		super(holder, balance, dateOpen);
		this.withdrawals = 0;
	}
	
	/**
	 * Getter for the number of withdrawals that the user of this account had made for this account
	 * @return - the number of times money was withdrawn from this account
	 */
	public int getWithdrawals() {
		return withdrawals;
	}
	
	/**
     * setter for the number of withdrawals that the user of this account had made for this account
     * @param withdrawals - the new number of withdrawals
     */
	public void setWithdrawals(int withdrawals) {
		this.withdrawals = withdrawals;
	}
	
	@Override
	/**
	 * Decreases the balance in this account by specified amount
	 * @param amount - the amount that will be deducted
	 */
	public void debit(double amount) { 
      super.setBalance((super.getBalance() - amount));
      this.withdrawals++;
    } 

	@Override
	/**
	 * calculates the monthly interest rate for this account type
	 * @return - the amount of interest rate calculated
	 */
	public double monthlyInterest() {
		double interest = 0.0065/12;
		return interest;
	}

	@Override
	/**
	 * calculates the monthly fee that the user needs to pay for this account
	 * @return - the amount that needs to be paid
	 */
	public double monthlyFee() {
		double fee = 0;
		if(super.getBalance() < 2500 || withdrawals > 6 ) {
			fee = 12;
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
      if((account instanceof MoneyMarket) && super.equals(account))
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
      String account = "*Money Market*"+ super.toString() + "*";
      account = account + this.getWithdrawals() + " withdrawals*";
      
      return account;
    }

}
