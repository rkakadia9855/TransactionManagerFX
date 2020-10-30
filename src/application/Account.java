/**
 * This class represents the general account entity. It is extended by three specific types of 
 * accounts: checking, savings, and money market
 * @author John Juarez, Rudra Kakadia
 */
package application;
import java.text.DecimalFormat;

public abstract class Account {
  
    private Profile holder;
    private double balance;
    private Date dateOpen;
    
    /**
     * Initializes the Account object
     * @param holder - the profile object that represents a person opening an account
     * @param balance - the amount that the person initially deposits into the bank
     * @param dateOpen - the date when the account is being opened
     */
    public Account(Profile holder, double balance,Date dateOpen) {
        this.holder = holder;
        this.balance = balance;
        this.dateOpen = dateOpen;
    }
    
    /**
     * This function returns the balance in this account
     * @return the balance of the account
     */
    public double getBalance() {
        return balance;
    }
    
    /**
     * This function adjusts/changes the amount money in the account
     * @param balance - the new balance that the account should have
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    /**
     * a getter method to get the person who holds/owns this bank account
     * @return the profile object
     */
    public Profile getHolder() {
      return this.holder;
    }
    
    /**
     * getter method to get the date when the account was opened
     * @return the date on which the account was opened
     */
    public Date getDateOpen() {
      return this.dateOpen;
    }
    
    /**
     * This method decreases the balance in this account by the specified amount
     * @param amount - the amount that needs to be debited from the account
     */
    public void debit(double amount) { 
        balance -= amount;
    }
    
    /**
     * This method increases the balance in this account by the specified amount
     * @param amount - the amount that needs to be credited into this account
     */
    public void credit(double amount) {
        balance += amount;
    }
    
    /**
     * Builds the string representation of this account
     * @return - the string representation of this account
     */
    public String toString() { 
        
        String name = holder.toString();
        
        String date = dateOpen.toString();
        
        DecimalFormat formatter = new DecimalFormat("#,##0.00");
        
        String account = name + "* $" + formatter.format(balance) + "*" + date;
        
        return account;
    }

    /**
     * Checks if the account in the argument is the same as this account, that is 
     * the same person holds both the account
     * @param account - the account that needs to be compared with this account
     * @return - true if the accounts are same, false otherwise
     */
    public boolean equals(Account account) {
      if(this.holder.equals(account.getHolder()))
        return true;
      else 
        return false;
    }
    
    /**
     * abstract method that returns the monthly interest according to account type
     * @return - the monthly interest that the account holder receives
     */
    public abstract double monthlyInterest();
    
    /**
     * abstract method that returns the monthly fee according to account type
     * @return - the fee that the account holder pays
     */
    public abstract double monthlyFee();
  
}
