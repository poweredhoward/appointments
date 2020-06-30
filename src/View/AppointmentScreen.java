package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AppointmentScreen {

    @FXML
    private TextField textCustomerName;

    @FXML
    private TextField textCustomerAddress;

    @FXML
    private TextField textCustomerPhone;

    @FXML
    private Button btnSearchAppointments;

    @FXML
    private TextField textSearchAppointments;

    @FXML
    private TableView<?> existingAppointmentsTable;

    @FXML
    private TableColumn<?, ?> colAppointmentID;

    @FXML
    private TableColumn<?, ?> colAppointmentTime;

    @FXML
    private TableColumn<?, ?> colAppointmentDate;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colAppointmentType;

    @FXML
    private Button btnDeleteAppointment;

    @FXML
    private Button btnSaveAppointment;

    @FXML
    private Button btnCancelSaveAppointment;

    @FXML
    private Button btnSelectAppointment;

    @FXML
    private Button btnSearchCustomers1;

    @FXML
    private TextField textSearchCustomers1;

    @FXML
    private TableView<?> existingCustomersTable1;

    @FXML
    private TableColumn<?, ?> colCustomerID1;

    @FXML
    private TableColumn<?, ?> colCustomerName1;

    @FXML
    private TableColumn<?, ?> colCustomerPhone1;

    @FXML
    private TableColumn<?, ?> colCustomerAddress1;

    @FXML
    private Button btnSelectCustomer1;

    @FXML
    private RadioButton radioSalesPitch;

    @FXML
    private RadioButton radioPlanning;

    @FXML
    private RadioButton radioTagup;

    @FXML
    void clickDeleteAppointment(ActionEvent event) {

    }

    @FXML
    void clickRadioPlanning(ActionEvent event) {

    }

    @FXML
    void clickRadioSalesPitch(ActionEvent event) {

    }

    @FXML
    void clickRadioTagup(ActionEvent event) {

    }

    @FXML
    void clickSaveAppointment(ActionEvent event) {

    }

    @FXML
    void clickSearchAppointments(ActionEvent event) {

    }

    @FXML
    void clickSearchCustomers(ActionEvent event) {

    }

    @FXML
    void clickSelectAppointment(ActionEvent event) {

    }

    @FXML
    void clickSelectCustomer(ActionEvent event) {

    }

    @FXML
    void clickCancelSaveAppointment(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage=(Stage) btnCancelSaveAppointment.getScene().getWindow();
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
