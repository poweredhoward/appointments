package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerScreen {


    @FXML
    private TextField textCustomerName;

    @FXML
    private TextField textCustomerAddress;

    @FXML
    private TextField textCustomerAddress2;

    @FXML
    private TextField textCustomerCity;

    @FXML
    private TextField textCustomerCountry;

    @FXML
    private TextField textCustomerPostalCode;

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

    public void clickCancelSaveCustomer(ActionEvent actionEvent)  throws IOException {
        Stage stage;
        Parent root;

        stage=(Stage) btnCancelSaveCustomer.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "WelcomeScreen.fxml"
        ));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
//        MainScreen controller = loader.getController();
    }
}
