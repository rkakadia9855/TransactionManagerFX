package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

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

    }

    @FXML
    void clearClicked(ActionEvent event) {

    }

    @FXML
    void closeAccountClicked(ActionEvent event) {

    }

    @FXML
    void moneymarketSelected(ActionEvent event) {

    }

    @FXML
    void openAccountClicked(ActionEvent event) {

    }

    @FXML
    void savingSelected(ActionEvent event) {

    }

}
