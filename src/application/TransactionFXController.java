package application;

import java.text.DecimalFormat;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;

public class TransactionFXController {
	
	AccountDatabase db = new AccountDatabase();

    @FXML
    private TextField lastName;

    @FXML
    private TextField firstName;

    @FXML
    private TextField balance;

    @FXML
    private DatePicker dateOpen;

    @FXML
    private RadioButton checking;

    @FXML
    private ToggleGroup accountType;

    @FXML
    private RadioButton savings;

    @FXML
    private RadioButton moneymarket;

    @FXML
    private Button openAccount;

    @FXML
    private CheckBox directDeposit;

    @FXML
    private CheckBox loyalCustomer;

    @FXML
    private Button closeAccount;

    @FXML
    private Button clear;

    @FXML
    private TextField lastName2;

    @FXML
    private TextField firstName2;

    @FXML
    private TextField amount;

    @FXML
    private RadioButton checking2;

    @FXML
    private ToggleGroup accountType2;

    @FXML
    private RadioButton savings2;

    @FXML
    private RadioButton moneymarket2;

    @FXML
    private Button deposit;

    @FXML
    private Button withdraw;

    @FXML
    private TextArea outputConsole;

    @FXML
    void checkingSelected(ActionEvent event) {
      directDeposit.setDisable(false);
      loyalCustomer.setDisable(true);
    }

    @FXML
    void clearClicked(ActionEvent event) {
    	firstName.clear();
    	lastName.clear();
    	dateOpen.getEditor().clear();
    	balance.clear();
    	checking.setSelected(false);
    	savings.setSelected(false);
    	moneymarket.setSelected(false);
    	directDeposit.setSelected(false);
    	loyalCustomer.setSelected(false);
    }

    @FXML
    void closeAccountClicked(ActionEvent event) {
    	boolean validDataEntered = true;
    	
    	if(firstName.getText().equals("")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("Null value entered.");
            alert.setContentText("First Name cannot be left null.");
            alert.showAndWait();
            validDataEntered = false;
          }
          if(lastName.getText().equals("")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("Null value entered.");
            alert.setContentText("Last Name cannot be left null.");
            alert.showAndWait();
            validDataEntered = false;
          }
          
          if(validDataEntered) {
        	  if(checking.isSelected()) {
        		  String fname = firstName.getText();
    			  String lname = lastName.getText();
    			  Profile holder = new Profile(fname, lname);
    			  boolean accountRemoved = db.remove(new Checking(holder,0.0,null));
    			  
    			  if(!accountRemoved) {
    		          outputConsole.appendText("Account does not exist." + "\n");
    		        }
    		        else {
    		          outputConsole.appendText("Account closed and removed from the database." + "\n");
    		        }
        	  } else if(savings.isSelected()) {
        		  String fname = firstName.getText();
    			  String lname = lastName.getText();
    			  Profile holder = new Profile(fname, lname);
    			  boolean accountRemoved = db.remove(new Savings(holder,0.0,null));
    			  
    			  if(!accountRemoved) {
    		          outputConsole.appendText("Account does not exist." + "\n");
    		        }
    		        else {
    		          outputConsole.appendText("Account closed and removed from the database." + "\n");
    		        }
        	  } else if (moneymarket.isSelected()) {
        		  String fname = firstName.getText();
    			  String lname = lastName.getText();
    			  Profile holder = new Profile(fname, lname);
    			  boolean accountRemoved = db.remove(new MoneyMarket(holder,0.0,null));
    			  
    			  if(!accountRemoved) {
    		          outputConsole.appendText("Account does not exist." + "\n");
    		        }
    		        else {
    		          outputConsole.appendText("Account closed and removed from the database." + "\n");
    		        }
        	  }
          }
    }

    @FXML
    void moneymarketSelected(ActionEvent event) {
      directDeposit.setDisable(true);
      loyalCustomer.setDisable(true);
    }
    

    @FXML
    void openAccountClicked(ActionEvent event) {
      boolean validDataEntered = true;
      double initialBalance = 0;
     // AccountDatabase db = new AccountDatabase();
      try {
          initialBalance = Double.parseDouble(balance.getText()); 
          if(initialBalance < 0) {
            throw new Exception("Cannot enter negative value in the balance field.");
          }
      }
      //Show the error message with a pop-up window.
      catch (NumberFormatException e) {
          Alert alert = new Alert(AlertType.WARNING);
          alert.setTitle("Warning!!");
          alert.setHeaderText("Non numeric data has been entered.");
          alert.setContentText("Please enter integers only in the textbox for balance.");
          alert.showAndWait();
          validDataEntered = false;
      }
      catch (Exception e) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("Negative value entered.");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
        validDataEntered = false;
      }
      LocalDate date = dateOpen.getValue();
      String[] values;
      Date dateOpen = null;
      if(date != null) { 
      	 values = date.toString().split("-");
      	 int year = Integer.parseInt(values[0]);
         int month = Integer.parseInt(values[1]);
         int day = Integer.parseInt(values[2]);
         dateOpen = new Date (month,day,year);
         if(!dateOpen.isValid()) {
           validDataEntered = false;
         }
      }
      else {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("Null value entered");
        alert.setContentText("Date cannot be left null.");
        alert.showAndWait();
        validDataEntered = false;
      }
        
      // we can use the isValid method of our transactionmanager date class to validate the date
      if(firstName.getText().equals("")) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("Null value entered.");
        alert.setContentText("First Name cannot be left null.");
        alert.showAndWait();
        validDataEntered = false;
      }
      if(lastName.getText().equals("")) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("Null value entered.");
        alert.setContentText("Last Name cannot be left null.");
        alert.showAndWait();
        validDataEntered = false;
      }
      if(!checking.isSelected() && !savings.isSelected() && !moneymarket.isSelected()) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("Account type unclear.");
        alert.setContentText("Please select the type of account you would like to open.");
        alert.showAndWait();
        validDataEntered = false;
      }
      //AccountDatabase db = new AccountDatabase();
      if(validDataEntered) {
        // do further processing
    	  
    	  if(checking.isSelected()) {
    		  if(directDeposit.isSelected()) {
    			  String fname = firstName.getText();
    			  String lname = lastName.getText();
    			  Profile holder = new Profile(fname, lname);
    			  Checking temp = new Checking(holder,initialBalance,dateOpen);
    			  temp.setDirectDeposit(true);
    			  
    			  if(db.add(temp)) {
    				  outputConsole.appendText("Account added to Database" + "\n");
    			  }else {
    				  outputConsole.appendText("Account is already in Database" + "\n");
    			  }
    			  
    			 
    		  }else{
    			  String fname = firstName.getText();
    			  String lname = lastName.getText();
    			  Profile holder = new Profile(fname, lname);
    			  Checking temp = new Checking(holder,initialBalance,dateOpen);
    			 
    			  temp.setDirectDeposit(false);
    			  
    			  if(db.add(temp)) {
    				  outputConsole.appendText("Account added to Database" + "\n");
    			  }else {
    				  outputConsole.appendText("Account is already in Database" + "\n");
    			  }  
    		  }
    	  } else if(savings.isSelected()) {
    		  if(loyalCustomer.isSelected()) {
    			  String fname = firstName.getText();
    			  String lname = lastName.getText();
    			  Profile holder = new Profile(fname, lname);
    			  Savings temp = new Savings(holder,initialBalance,dateOpen);
    			  temp.setLoyal(true);
    			  
    			  if(db.add(temp)) {
    				  outputConsole.appendText("Account added to database" + "\n");
    			  }else {
    				  outputConsole.appendText("Account is already in database" + "\n");
    			  
    		  }
    	  }else {
    		  String fname = firstName.getText();
			  String lname = lastName.getText();
			  Profile holder = new Profile(fname, lname);
			  Savings temp = new Savings(holder,initialBalance,dateOpen);
			  temp.setLoyal(false);
			  
			  if(db.add(temp)) {
				  outputConsole.appendText("Account added to database" + "\n");
			  }else {
				  outputConsole.appendText("Account is already in database" + "\n");
			  
			  }
    	  	}
    	  } else if (moneymarket.isSelected()) {
    		  String fname = firstName.getText();
			  String lname = lastName.getText();
			  Profile holder = new Profile(fname, lname);
			  MoneyMarket temp = new MoneyMarket(holder,initialBalance,dateOpen);
			  
			  if(db.add(temp)) {
				  outputConsole.appendText("Account added to database" + "\n");
			  }else {
				  outputConsole.appendText("Account is already in database" + "\n");
			  
			  }
    	  }
      }
    }

    @FXML
    void savingSelected(ActionEvent event) {
      loyalCustomer.setDisable(false);
      directDeposit.setDisable(true);
    }
    
    @FXML
    void withdrawMoney(ActionEvent event) {
      boolean validDataEntered = true;
      double withdrawAmount = 0.0;
      try {
        withdrawAmount = Double.parseDouble(amount.getText()); 
          if(withdrawAmount < 0) {
            throw new Exception("Cannot enter negative value in the amount field.");
          }
      }
      //Show the error message with a pop-up window.
      catch (NumberFormatException e) {
          Alert alert = new Alert(AlertType.WARNING);
          alert.setTitle("Warning!!");
          alert.setHeaderText("Non numeric data has been entered.");
          alert.setContentText("Please enter integers only in the textbox for amount.");
          alert.showAndWait();
          validDataEntered = false;
      }
      catch (Exception e) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("Negative value entered.");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
        validDataEntered = false;
      }
      if(firstName2.getText().equals("")) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("Null value entered.");
        alert.setContentText("First Name cannot be left null.");
        alert.showAndWait();
        validDataEntered = false;
      }
      if(lastName2.getText().equals("")) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("Null value entered.");
        alert.setContentText("Last Name cannot be left null.");
        alert.showAndWait();
        validDataEntered = false;
      }
      if(validDataEntered) {
         int withdrawn = -1;
         Profile holder = new Profile(firstName2.getText(), lastName2.getText());
         DecimalFormat formatter = new DecimalFormat("#,##0.00");
         if(checking2.isSelected()) {
           withdrawn = db.withdrawal(new Checking(holder, 0, null), withdrawAmount);
         }
         else if(savings2.isSelected()) {
           withdrawn = db.withdrawal(new Savings(holder, 0, null), withdrawAmount);
         }
         else if(moneymarket2.isSelected()) {
           withdrawn = db.withdrawal(new MoneyMarket(holder, 0, null), withdrawAmount);
         }
         if(withdrawn == 0) {
           outputConsole.appendText(formatter.format(withdrawAmount) + " withdrawn from account.\n");
         }
         else if(withdrawn == -1) {
           outputConsole.appendText("Account does not exist.\n");
         }
         else if(withdrawn == 1) {
           outputConsole.appendText("Insufficient funds.\n");
         }
      }
    }
    
    @FXML
    void depositMoney(ActionEvent event) {
      boolean validDataEntered = true;
      double depositAmount = 0.0;
      try {
        depositAmount = Double.parseDouble(amount.getText()); 
          if(depositAmount < 0) {
            throw new Exception("Cannot enter negative value in the amount field.");
          }
      }
      //Show the error message with a pop-up window.
      catch (NumberFormatException e) {
          Alert alert = new Alert(AlertType.WARNING);
          alert.setTitle("Warning!!");
          alert.setHeaderText("Non numeric data has been entered.");
          alert.setContentText("Please enter integers only in the textbox for amount.");
          alert.showAndWait();
          validDataEntered = false;
      }
      catch (Exception e) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("Negative value entered.");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
        validDataEntered = false;
      }
      if(firstName2.getText().equals("")) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("Null value entered.");
        alert.setContentText("First Name cannot be left null.");
        alert.showAndWait();
        validDataEntered = false;
      }
      if(lastName2.getText().equals("")) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("Null value entered.");
        alert.setContentText("Last Name cannot be left null.");
        alert.showAndWait();
        validDataEntered = false;
      }
      if(validDataEntered) {
        Profile holder = new Profile(firstName2.getText(), lastName2.getText());
        boolean depositSuccessful = false;
        if(checking2.isSelected()) {
          depositSuccessful = db.deposit(new Checking(holder, 0, null), depositAmount);
        }
        else if(savings2.isSelected()) {
          depositSuccessful = db.deposit(new Savings(holder, 0, null), depositAmount);
        }
        else if(moneymarket2.isSelected()) {
          depositSuccessful = db.deposit(new MoneyMarket(holder, 0, null), depositAmount);
        }
        DecimalFormat formatter = new DecimalFormat("#,##0.00");
        if(depositSuccessful) {
          outputConsole.appendText(formatter.format(depositAmount) + " deposited to account.\n");
        }
        else {
          outputConsole.appendText("Account does not exist.\n");
        }
      }
    }

}

