package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CustomerScreen {

    @FXML
    private TextField textCustomerName;

    @FXML
    private TextField textCustomerAddress;

    @FXML
    private TextField textCustomerPhone;

    @FXML
    private Button btnSearchCustomers;

    @FXML
    private TextField textSearchCustomers;

    @FXML
    private TableView<?> existingCustomersTable;

    @FXML
    private TableColumn<?, ?> colCustomerID;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colCustomerPhone;

    @FXML
    private TableColumn<?, ?> colCustomerAddress;

    @FXML
    private Button btnDeleteCustomer;

    @FXML
    private Button btnSaveCustomer;

    @FXML
    private Button btnCancelSaveCustomer;

    @FXML
    private Button btnSelectCustomer;

    @FXML
    void clickCancelSaveCustomer(ActionEvent event) {

    }

    @FXML
    void clickDeleteCustomer(ActionEvent event) {

    }

    @FXML
    void clickSaveCustomer(ActionEvent event) {

    }

    @FXML
    void clickSearchCustomers(ActionEvent event) {

    }

    @FXML
    void clickSelectCustomer(ActionEvent event) {

    }

}
