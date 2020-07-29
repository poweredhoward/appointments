package View;

import Controller.AppointmentService;
import Controller.ConsultantService;
import Controller.CustomerService;
import Model.Appointment;
import Model.Consultant;
import Model.Customer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportScreen implements Initializable {

     @FXML
    private ComboBox comboConsultant;

    @FXML
    private ComboBox comboCustomer;

    @FXML
    private Button btnRunReport1;

    @FXML
    private Button btnRunReport2;

    @FXML
    private Button btnRunReport3;

    @FXML
    private TableView<AppointmentService.MonthResult> tableReport1;

    @FXML
    private TableColumn<?, ?> report1Col1;

    @FXML
    private TableColumn<?, ?> report1Col2;

    @FXML
    private TableColumn<?, ?> report1Col3;

    @FXML
    private TableView<Appointment> tableReport2;

    @FXML
    private TableColumn<?, ?> report2Col1;

    @FXML
    private TableColumn<?, ?> report2Col2;

    @FXML
    private TableColumn<?, ?> report2Col3;

    @FXML
    private TableView<Appointment> tableReport3;

    @FXML
    private TableColumn<?, ?> report3Col1;

    @FXML
    private TableColumn<?, ?> report3Col2;

    @FXML
    private TableColumn<?, ?> report3Col3;

    @FXML
    private Button btnBack;

    @FXML
    void clickReport1(ActionEvent event) throws Exception {
        ObservableList<AppointmentService.MonthResult> results = AppointmentService.getNumberOfAppointmentsByMonth();
        tableReport1.setItems(results);
    }

    @FXML
    void clickReport2(ActionEvent event) throws Exception {
        String consultantName = (String)comboConsultant.getValue();
        Consultant consultant = ConsultantService.getConsultant(consultantName);
        ObservableList<Appointment> consultantAppointments =
                AppointmentService.getConsultantFutureAppointmentsForNDays(consultant.getId(), 365);
        tableReport2.setItems(consultantAppointments);

    }

    @FXML
    void clickReport3(ActionEvent event) throws Exception {
        String customerName = (String)comboCustomer.getValue();
        Customer customer = CustomerService.getCustomersByName(customerName);
        ObservableList<Appointment> customerAppointments =
                AppointmentService.getCustomerFutureAppointmentsNDays(customer.getId(), 365);
        tableReport3.setItems(customerAppointments);

    }

    @FXML
    void clickBtnBack(ActionEvent event)  throws IOException {
        Stage stage;
        Parent root;

        stage=(Stage) btnBack.getScene().getWindow();
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

        ObservableList<Consultant> allConsultants = null;
        try {
            allConsultants = ConsultantService.getAllConsultants();
            for (Consultant user : allConsultants){
                comboConsultant.getItems().add(user.getUsername());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        ObservableList<Customer> allCustomers = null;
        try {
            allCustomers = CustomerService.getAllCustomers();
            for (Customer customer : allCustomers){
                comboCustomer.getItems().add(customer.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        report1Col1.setCellValueFactory(new PropertyValueFactory<>("month"));
        report1Col2.setCellValueFactory(new PropertyValueFactory<>("type"));
        report1Col3.setCellValueFactory(new PropertyValueFactory<>("count"));

        report2Col1.setCellValueFactory(new PropertyValueFactory<>("start"));
        report2Col2.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        report2Col3.setCellValueFactory(new PropertyValueFactory<>("type"));

        report3Col1.setCellValueFactory(new PropertyValueFactory<>("start"));
        report3Col2.setCellValueFactory(new PropertyValueFactory<>("end"));
        report3Col3.setCellValueFactory(new PropertyValueFactory<>("type"));


    }
}
