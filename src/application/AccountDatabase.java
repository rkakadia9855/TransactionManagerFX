/**
 * This class represents the database of the bank where all the details about accounts is stored
 * @author John Juarez, Rudra Kakadia
 */
package application;
import java.text.DecimalFormat;

public class AccountDatabase {
    
  private Account[] accounts;
    
  private int size;
  
  /**
   * Initializes the accounts array and the size to 0
   */
  public AccountDatabase() {
    this.size = 0;
    accounts = new Account[5];
  }
    
  /**
   * Finds the account in the parameter in the bank database
   * @param account - the account that needs to be found
   * @return - the index of the account in the array if found, -1 otherwise.
   */
  private int find(Account account) {
    
    int i = 0;
    int index = -1;
    
    for(i = 0; i < size; i++) {
      if(accounts[i].equals(account)) {
        return i;
      }
    }
    
    return index;
  }
    
  /**
   * This is a helper method that grows the bank database by 5
   */
  private void grow() { 
    Account[] temp = new Account[accounts.length + 5];
    int i = 0;
    for(i = 0; i < size; i++) {
      temp[i] = accounts[i];
    }
    accounts = temp;
  }
    
  /**
   * This method is used to add an account to the account database
   * @param account - the account that needs to be added to the database
   * @return - true if account was added, false otherwise
   */
  public boolean add(Account account) { 
    
    boolean canAddAccount = true;
    int i = 0;
    
    for(i = 0; i < size; i++) {
      if(account.equals(accounts[i])) {
        canAddAccount = false;
        return canAddAccount;
      }
    }

    if(size < accounts.length) {
      accounts[size] = account;
      size++;
    }
    else {
      grow();
      accounts[size] = account;
      size++;
    }
    
    return canAddAccount;
  } 
    
  /**
   * Removes the account from the bank database
   * @param account - the account that needs to be removed from the database
   * @return - true if the account was removed, false otherwise
   */
  public boolean remove(Account account) {
    boolean accountRemoved = false;
    int i = 0;
    
    for(i = 0; i < size; i++) {
      if(account.equals(accounts[i])) {
        accountRemoved = true;
        accounts[i] = accounts[size - 1];
        accounts[size - 1] = null;
        size--;
        return accountRemoved;
      }
    }
    
    return accountRemoved;
  }
  
  /**
   * This method deposits some amount of money in the account that was passed as argument
   * @param account - the account to which the money should be deposited
   * @param amount - the amount that needs to be deposited
   * @return - true if the amount was deposited, false otherwise
   */
  public boolean deposit(Account account, double amount) { 
    boolean depositSuccessful = false;
    int accountIndex = find(account);
    if(accountIndex != -1) {
      accounts[accountIndex].credit(amount);
      depositSuccessful = true;
    }
    return depositSuccessful;
  }

  /**
   * Withdraws some amount of money from the account passed in the argument
   * @param account - the account from which the money should be withdrawn
   * @param amount - the amount that needs to be withdrawn
   * @return -  0: withdrawal successful, 1: insufficient funds, -1 account doesn’t exist
   */
  public int withdrawal(Account account, double amount) {
    int withdrawalStatus = 0;
    int accountIndex = find(account);
    if(accountIndex == -1)
      return accountIndex;
    else if(amount > accounts[accountIndex].getBalance()) {
      return 1;
    }
    else {
      accounts[accountIndex].debit(amount);
    }
    
    return withdrawalStatus;
  }
  
  /**
   * Calculates the interest that will be paid to this account
   * @param account - the account for which the interest should be calculated
   * @return - the amount of interest that needs to be paid to the customer
   */
  private double calcInterest(Account account) {
    double interest = 0.0;
    interest = account.monthlyInterest() * account.getBalance();
    return interest;
  }
  
  /**
   * Updates the balance of the account after adding interest, and deducting monthly fee
   * @param account - the account for which the balance needs to be updated
   * @return - the amount of money in the bank after adding interest and deducting fee
   */
  private double newBalance(Account account) {
    double updatedBalance = 0.0;
    updatedBalance = account.getBalance() + this.calcInterest(account);
    updatedBalance = updatedBalance - account.monthlyFee();
    account.setBalance(updatedBalance);
    return updatedBalance;
  }
    
  /**
   * Sorts the bank database array in ascending order of date opened
   */
  private void sortByDateOpen() { 
    int n = accounts.length; 
    
    for(int i = 0; i < n-1; i++) {
      int minIndex = i;
      for(int j = i+1; j < n; j++) {
        if(accounts[j] != null) {
          if(accounts[j].getDateOpen().compareTo(accounts[minIndex].getDateOpen()) < 0) {
            minIndex = j;
          }
        }
      }
      Account tempAcc = accounts[minIndex];
      accounts[minIndex] = accounts[i];
      accounts[i] = tempAcc;
    }
  } 
    
  /**
   * Sorts the bank database array in ascending order of last name
   */
  private void sortByLastName() { 
    int n = accounts.length; 
    
    
    for(int i = 0; i < n-1; i++) {
      int minIndex = i;
      for(int j = i+1; j < n; j++) {
        if(accounts[j] != null) {
          if(accounts[j].getHolder().getLName().
              compareTo(accounts[minIndex].getHolder().getLName()) < 0) {
            minIndex = j;
          }
        }
      }
      Account tempAcc = accounts[minIndex];
      accounts[minIndex] = accounts[i];
      accounts[i] = tempAcc;
    }
  } 
    
  /**
   * Prints the account statements in ascending order of date opened
   */
  public void printByDateOpen() {
    if(size != 0) {
      System.out.println("");
      System.out.println("--Printing statements by date opened--");
      sortByDateOpen();
      for(int i = 0; i < size; i++) {
        System.out.println("");
        System.out.println(accounts[i].toString());
        DecimalFormat formatter = new DecimalFormat("#,##0.00");
        System.out.println("-interest: $ "+ formatter.format(this.calcInterest(accounts[i])));
        DecimalFormat feeFormatter = new DecimalFormat("#,##0.00");
        System.out.println("-fee: $ "+ feeFormatter.format(accounts[i].monthlyFee()));
        DecimalFormat balanceFormatter = new DecimalFormat("#,##0.00");
        System.out.println("-new balance: $ "+ 
            balanceFormatter.format(this.newBalance(accounts[i])));
      }
      System.out.println("--end of printing--");
    }
    else {
      System.out.println("Database is empty.");
    }
  }
    
  /**
   * Prints the account statements in ascending order of last name
   */
  public void printByLastName() { 
    if(size != 0) {
      System.out.println("");
      System.out.println("--Printing statements by last name--");
      sortByLastName();
      for(int i = 0; i < size; i++) {
        System.out.println("");
        System.out.println(accounts[i].toString());
        DecimalFormat formatter = new DecimalFormat("#,##0.00");
        System.out.println("-interest: $ "+ formatter.format(this.calcInterest(accounts[i])));
        DecimalFormat feeFormatter = new DecimalFormat("#,##0.00");
        System.out.println("-fee: $ "+ feeFormatter.format(accounts[i].monthlyFee()));
        DecimalFormat balanceFormatter = new DecimalFormat("#,##0.00");
        System.out.println("-new balance: $ "+ 
            balanceFormatter.format(this.newBalance(accounts[i])));
      }
      System.out.println("--end of printing--");
    }
    else {
      System.out.println("Database is empty.");
    }
  }
    
  /**
   * Lists all the accounts in the bank database
   */
  public void printAccounts() { 
    if(size != 0) {
      System.out.println("--Listing accounts in the database--");
      for(int i = 0; i < size; i++) {
        System.out.println(accounts[i].toString());
      }
      System.out.println("--end of listing--");
    }
    else {
      System.out.println("Database is empty.");
    }
  }
}
