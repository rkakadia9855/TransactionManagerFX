package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.StringTokenizer;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.MenuItem;

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
    private MenuItem printAccount;

    @FXML
    private MenuItem printByLastName;

    @FXML
    private MenuItem printByDateOpen;

    @FXML
    private TextArea outputConsole;
    
    @FXML
    private MenuItem importFile;

    @FXML
    private MenuItem exportFile;

    @FXML
    void checkingSelected(ActionEvent event) {
      directDeposit.setDisable(false);
      loyalCustomer.setSelected(false);
      loyalCustomer.setDisable(true);
    }

    @FXML
    void clearClicked(ActionEvent event) {
    	firstName.clear();
    	lastName.clear();
    	//dateOpen.getEditor().clear();
    	loyalCustomer.setDisable(false);
    	directDeposit.setDisable(false);
    	dateOpen.setValue(null);
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
            outputConsole.appendText("You need to enter your first name.\n");
          }
          if(lastName.getText().equals("")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("Null value entered.");
            alert.setContentText("Last Name cannot be left null.");
            alert.showAndWait();
            validDataEntered = false;
            outputConsole.appendText("You need to enter your last name.\n");
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
    		          outputConsole.appendText("Account closed and removed from the database." 
    		              + "\n");
    		        }
        	  }
        	  else {
        	    outputConsole.appendText("You need to select an account type.\n");
        	  }
          }
    }

    @FXML
    void moneymarketSelected(ActionEvent event) {
      directDeposit.setSelected(false);
      loyalCustomer.setSelected(false);
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
          outputConsole.appendText("Balance must have a numeric data type.\n");
      }
      catch (Exception e) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("Negative value entered.");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
        validDataEntered = false;
        outputConsole.appendText("Balance cannot be negative.\n");
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
           outputConsole.appendText("Date you selected is invalid.\n");
         }
      }
      else {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("Null value entered");
        alert.setContentText("Date cannot be left null.");
        alert.showAndWait();
        validDataEntered = false;
        outputConsole.appendText("Select a date. If you are entering the date instead of selecting "
            + "it, please select it using the provided date picker.\n");
      }
        
      // we can use the isValid method of our transactionmanager date class to validate the date
      if(firstName.getText().equals("")) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("Null value entered.");
        alert.setContentText("First Name cannot be left null.");
        alert.showAndWait();
        validDataEntered = false;
        outputConsole.appendText("You need to enter your first name.\n");
      }
      if(lastName.getText().equals("")) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("Null value entered.");
        alert.setContentText("Last Name cannot be left null.");
        alert.showAndWait();
        validDataEntered = false;
        outputConsole.appendText("You need to enter your last name.\n");
      }
      if(!checking.isSelected() && !savings.isSelected() && !moneymarket.isSelected()) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("Account type unclear.");
        alert.setContentText("Please select the type of account you would like to open.");
        alert.showAndWait();
        validDataEntered = false;
        outputConsole.appendText("Please select the type of account you would like to open.\n");
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
      directDeposit.setSelected(false);
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
          outputConsole.appendText("You must enter a numeric value in the amount field.\n");
      }
      catch (Exception e) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("Negative value entered.");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
        validDataEntered = false;
        outputConsole.appendText("Amount cannot be negative.\n");
      }
      if(firstName2.getText().equals("")) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("Null value entered.");
        alert.setContentText("First Name cannot be left null.");
        alert.showAndWait();
        validDataEntered = false;
        outputConsole.appendText("You need to enter your first name.\n");
      }
      if(lastName2.getText().equals("")) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("Null value entered.");
        alert.setContentText("Last Name cannot be left null.");
        alert.showAndWait();
        validDataEntered = false;
        outputConsole.appendText("You need to enter your last name.\n");
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
          outputConsole.appendText("You must enter a numeric value in the amount field.\n");
      }
      catch (Exception e) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("Negative value entered.");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
        validDataEntered = false;
        outputConsole.appendText("Amount cannot be negative.\n");
      }
      if(firstName2.getText().equals("")) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("Null value entered.");
        alert.setContentText("First Name cannot be left null.");
        alert.showAndWait();
        validDataEntered = false;
        outputConsole.appendText("You need to enter your first name.\n");
      }
      if(lastName2.getText().equals("")) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("Null value entered.");
        alert.setContentText("Last Name cannot be left null.");
        alert.showAndWait();
        validDataEntered = false;
        outputConsole.appendText("You need to enter your last name.\n");
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
    
    @FXML
    void printAccountByDateOpen(ActionEvent event) {
      String outputPrint = db.printByDateOpen();
      outputConsole.appendText(outputPrint);
    }

    @FXML
    void printAccountByLastName(ActionEvent event) {
      String outputPrint = db.printByLastName();
      outputConsole.appendText(outputPrint);
    }

    @FXML
    void printAccounts(ActionEvent event) {
      String outputPrint = db.printAccounts();
      outputConsole.appendText(outputPrint);
    }
    
    @FXML
    void exportDataFile(ActionEvent event) {
      FileChooser chooser = new FileChooser();
      chooser.setTitle("Open Target File for the Export");
      chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
              new ExtensionFilter("All Files", "*.*"));
      Stage stage = new Stage();
      File targetFile = chooser.showSaveDialog(stage); //get the reference of the target file
      //write code to write to the file.
      String exportData = db.printAccounts();
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile))) {
        if(writer != null) {
          writer.write(exportData);
          writer.flush();
          writer.close();
          outputConsole.appendText("File was successfully exported to the selected location.\n");
        }
      } catch (Exception e) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("I/O Error");
        alert.setContentText("An error occured while trying to export the data: ."+e.getMessage());
        alert.showAndWait();
        outputConsole.appendText("Unable to export the data to a file.\n");
      }
    }

    @FXML
    void importDataFile(ActionEvent event) {
      FileChooser chooser = new FileChooser();
      chooser.setTitle("Open Source File for the Import");
      chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
              new ExtensionFilter("All Files", "*.*"));
      Stage stage = new Stage();
      File sourceFile = chooser.showOpenDialog(stage); //get the reference of the source file
      StringTokenizer tokenizeDate;
      StringTokenizer tokenizeLine;
      //write code to read from the file.
      try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {

        String line;
        int lineNumber = 0;
        while ((line = reader.readLine()) != null) {
          lineNumber++;
          String fname;
          String lname;
          Profile holder;
          Date dateOpen;
          double balance = 0.0;
          String dateString = "";
          boolean lastArg = false;
          
          tokenizeLine = new StringTokenizer(line, ",");
          if(tokenizeLine.countTokens() != 6) {
            outputConsole.appendText("Invalid number of arguments on line "
                +lineNumber+". Continuing with next line.\n");
            continue;
          }
          String command = tokenizeLine.nextToken();
          if(command.equals("C")) {
            fname = tokenizeLine.nextToken();
            lname = tokenizeLine.nextToken();
            holder = new Profile(fname, lname);
            try {
              balance = Double.parseDouble(tokenizeLine.nextToken());
            }
            catch(NumberFormatException excep) {
              outputConsole.appendText("Invalid argument type. Balance is not of type double "
                  + "on line " + lineNumber + "."
                  + "Continuing with next line.\n");
              continue;
            }
            catch(Exception excep) {
              outputConsole.appendText("Invalid argument type. Balance is not of type double "
                  + "on line " + lineNumber + "."
                  + "Continuing with next line.\n");
              continue;
            }
            dateString = tokenizeLine.nextToken();
            tokenizeDate = new StringTokenizer(dateString, "/");
            int month = 0, day = 0, year = 0;
            try {
              month = Integer.parseInt(tokenizeDate.nextToken());
              day = Integer.parseInt(tokenizeDate.nextToken());
              year = Integer.parseInt(tokenizeDate.nextToken());
            }
            catch(NumberFormatException e) {
              outputConsole.appendText("Input data type mismatch for date on line "+lineNumber
                  + ". Continuing with next line\n");
              continue;
            }
            catch(Exception excep) {
              outputConsole.appendText("Input data type mismatch for date on line "+lineNumber
                  + ". Continuing with next line\n");
              continue;
            }
            dateOpen = new Date(month, day, year);
            if(!(dateOpen.isValid())) {
              outputConsole.appendText(dateString + " on line "+lineNumber+ " is not valid."
                  + " Continuing with next line.\n");
              continue;
            }
            try {
              lastArg = Boolean.parseBoolean(tokenizeLine.nextToken());
            }
            catch(Exception e) {
              outputConsole.appendText("Input data type mismatch on line "+lineNumber
                  + ". Continuing with next line\n");
              continue;
            }
            Checking temp = new Checking(holder, balance, dateOpen);
            temp.setDirectDeposit(lastArg);
            if(db.add(temp)) {
              outputConsole.appendText("Account opened and added to the database.\n");
            }
            else {
              outputConsole.appendText("Account is already in the database.\n");
            }
            
          }
          else if(command.equals("S")) {
            fname = tokenizeLine.nextToken();
            lname = tokenizeLine.nextToken();
            holder = new Profile(fname, lname);
            try {
              balance = Double.parseDouble(tokenizeLine.nextToken());
            }
            catch(NumberFormatException excep) {
              outputConsole.appendText("Invalid argument type. Balance is not of type double "
                  + "on line " + lineNumber + "."
                  + "Continuing with next line.\n");
              continue;
            }
            catch(Exception excep) {
              outputConsole.appendText("Invalid argument type. Balance is not of type double "
                  + "on line " + lineNumber + "."
                  + "Continuing with next line.\n");
              continue;
            }
            dateString = tokenizeLine.nextToken();
            tokenizeDate = new StringTokenizer(dateString, "/");
            int month = 0, day = 0, year = 0;
            try {
              month = Integer.parseInt(tokenizeDate.nextToken());
              day = Integer.parseInt(tokenizeDate.nextToken());
              year = Integer.parseInt(tokenizeDate.nextToken());
            }
            catch(NumberFormatException e) {
              outputConsole.appendText("Input data type mismatch for date on line "+lineNumber
                  + ". Continuing with next line\n");
              continue;
            }
            catch(Exception e) {
              outputConsole.appendText("Input data type mismatch for date on line "+lineNumber
                  + ". Continuing with next line\n");
              continue;
            }
            dateOpen = new Date(month, day, year);
            if(!(dateOpen.isValid())) {
              outputConsole.appendText(dateString + " on line "+lineNumber+ " is not valid."
                  + " Continuing with next line.\n");
              continue;
            }
            try {
              lastArg = Boolean.parseBoolean(tokenizeLine.nextToken());
            }
            catch(Exception e) {
              outputConsole.appendText("Input data type mismatch on line "+lineNumber
                  + ". Continuing with next line\n");
              continue;
            }
            Savings temp = new Savings(holder, balance, dateOpen);
            temp.setLoyal(lastArg);
            if(db.add(temp)) {
              outputConsole.appendText("Account opened and added to the database.\n");
            }
            else {
              outputConsole.appendText("Account is already in the database.\n");
            }
          }
          else if(command.equals("M")) {
            int numWithdrawals = 0;
            fname = tokenizeLine.nextToken();
            lname = tokenizeLine.nextToken();
            holder = new Profile(fname, lname);
            try {
              balance = Double.parseDouble(tokenizeLine.nextToken());
            }
            catch(NumberFormatException excep) {
              outputConsole.appendText("Invalid argument type. Balance is not of type double "
                  + "on line " + lineNumber + "."
                  + "Continuing with next line.\n");
              continue;
            }
            catch(Exception excep) {
              outputConsole.appendText("Invalid argument type. Balance is not of type double "
                  + "on line " + lineNumber + "."
                  + "Continuing with next line.\n");
              continue;
            }
            dateString = tokenizeLine.nextToken();
            tokenizeDate = new StringTokenizer(dateString, "/");
            int month = 0, day = 0, year = 0;
            try {
              month = Integer.parseInt(tokenizeDate.nextToken());
              day = Integer.parseInt(tokenizeDate.nextToken());
              year = Integer.parseInt(tokenizeDate.nextToken());
            }
            catch(NumberFormatException e) {
              outputConsole.appendText("Input data type mismatch for date on line "+lineNumber
                  + ". Continuing with next line\n");
              continue;
            }
            catch(Exception e) {
              outputConsole.appendText("Input data type mismatch for date on line "+lineNumber
                  + ". Continuing with next line\n");
              continue;
            }
            dateOpen = new Date(month, day, year);
            if(!(dateOpen.isValid())) {
              outputConsole.appendText(dateString + " on line "+lineNumber+ " is not valid."
                  + " Continuing with next line.\n");
              continue;
            }
            try {
              numWithdrawals = Integer.parseInt(tokenizeLine.nextToken());
           //   lastArg = Boolean.parseBoolean(tokenizeLine.nextToken());
            }
            catch(Exception e) {
              outputConsole.appendText("Input data type mismatch on line "+lineNumber
                  + ". Continuing with next line\n");
              continue;
            }
            MoneyMarket temp = new MoneyMarket(holder, balance, dateOpen);
            temp.setWithdrawals(numWithdrawals);
            if(db.add(temp)) {
              outputConsole.appendText("Account opened and added to the database.\n");
            }
            else {
              outputConsole.appendText("Account is already in the database.\n");
            }
          }
          else {
            outputConsole.appendText("Invalid first command: "+command+" on line "+lineNumber
                +". Exiting import.\n");
            continue;
          }
        }
        
        outputConsole.appendText("Data was imported from the file.\n");

      } catch (IOException e) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("I/O Error");
        alert.setContentText("An error occured while trying to read from a file: "+
            sourceFile.getName());
        alert.showAndWait();
        outputConsole.appendText("Unable to read from a file.\n");
      } catch (NullPointerException e) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("I/O Error");
        alert.setContentText("An error occured while trying to read from a file.");
        alert.showAndWait();
        outputConsole.appendText("Unable to import from a file.\n");
      }
      catch (Exception e) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("I/O Error");
        alert.setContentText("An error occured while trying to read from a file.");
        alert.showAndWait();
        outputConsole.appendText("Unable to import from a file.\n");
      }
      
    }

}

