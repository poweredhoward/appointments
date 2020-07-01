package View;

import Controller.AppointmentService;
import Controller.CustomerService;
import Model.Appointment;
import Model.Customer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentScreen implements Initializable {
    private Customer selectedCustomer;
    private Appointment selectedAppointment;

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
    private TableView<Appointment> existingAppointmentsTable;

    @FXML
    private TableColumn<?, ?> colAppointmentID;

    @FXML
    private TableColumn<?, ?> colAppointmentStart;

    @FXML
    private TableColumn<?, ?> colAppointmentEnd;

    @FXML
    private TableColumn<?, ?> colCustomerName1;

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
    private TableView<Customer> existingCustomersTable;

    @FXML
    private TableColumn<?, ?> colCustomerID;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colCustomerPhone;

    @FXML
    private TableColumn<?, ?> colCustomerAddress;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCustomerPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("fullAddress"));

        try {
            setCustomersTable(CustomerService.getAllCustomers());
        } catch (Exception e) {
            e.printStackTrace();
        }

        colAppointmentID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAppointmentStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        colAppointmentEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        colCustomerName1.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colAppointmentType.setCellValueFactory(new PropertyValueFactory<>("type"));

        try {
            setAppointmentsTable(AppointmentService.getAllAppointments());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void setCustomersTable(ObservableList<Customer> customerList){
        try {
            existingCustomersTable.setItems(customerList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAppointmentsTable(ObservableList<Appointment> appointmentList){
        try {
            existingAppointmentsTable.setItems(appointmentList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
