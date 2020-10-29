package application;

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

    }

    @FXML
    void closeAccountClicked(ActionEvent event) {

    }

    @FXML
    void moneymarketSelected(ActionEvent event) {
      directDeposit.setDisable(true);
      loyalCustomer.setDisable(true);
    }

    @FXML
    void openAccountClicked(ActionEvent event) {
      boolean validDataEntered = true;
      try {
          int initialBalance = Integer.parseInt(balance.getText()); 
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
      // change type from localdate to out date object
      LocalDate date = dateOpen.getValue();
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
      if(validDataEntered) {
        // do further processing
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
      try {
          int initialBalance = Integer.parseInt(amount.getText()); 
          if(initialBalance < 0) {
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
        // do further processing
      }
    }
    
    @FXML
    void depositMoney(ActionEvent event) {
      boolean validDataEntered = true;
      try {
          int initialBalance = Integer.parseInt(amount.getText()); 
          if(initialBalance < 0) {
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
        // do further processing
      }
    }

}
