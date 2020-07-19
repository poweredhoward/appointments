package View;

import Controller.AppointmentService;
import Controller.CustomerService;
import Model.Appointment;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AppointmentScreen implements Initializable {


    private Customer selectedCustomer;
    private Appointment selectedAppointment;
    private boolean isNewAppointment;
    private String selectedType;
    public ToggleGroup type;

    @FXML
    private DatePicker dateStartDate;

    @FXML
    private ComboBox comboStartHour;

    @FXML
    private ComboBox comboStartMin;

    @FXML
    private DatePicker comboEndDate;

    @FXML
    private ComboBox comboEndHour;

    @FXML
    private ComboBox comboEndMin;

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
    private RadioButton radioPresentation;

    @FXML
    private RadioButton radioScrum;

    @FXML
    private RadioButton radioPlanning;


    @FXML
    void clickDeleteAppointment(ActionEvent event) throws Exception {
        Appointment toBeDeleted = (Appointment) existingAppointmentsTable.getSelectionModel().getSelectedItem();

        AppointmentService.deleteAppointment(toBeDeleted.getId());
        setAppointmentsTable(AppointmentService.getAllAppointments());
    }

    @FXML
    void clickRadioPlanning(ActionEvent event) {
        selectedType = "Planning Meeting";
    }

    @FXML
    void clickRadioScrum(ActionEvent event) {
        selectedType = "Scrum";
    }

    @FXML
    void clickRadioPresentation(ActionEvent event) {
        selectedType = "Presentation";
    }

    @FXML
    void clickSaveAppointment(ActionEvent event) throws Exception {
        LocalDate date = dateStartDate.getValue();
        int startHour = Integer.parseInt((String) comboStartHour.getValue());
        int startMin = Integer.parseInt((String) comboStartMin.getValue());
        int endHour = Integer.parseInt((String) comboEndHour.getValue());
        int endMin = Integer.parseInt((String) comboEndMin.getValue());
        LocalTime startTime = LocalTime.of(startHour, startMin);
        LocalTime endTime = LocalTime.of(endHour, endMin);

        ZonedDateTime start = ZonedDateTime.of(date, startTime, ZoneId.systemDefault());
        ZonedDateTime end = ZonedDateTime.of(date, endTime, ZoneId.systemDefault());

        Customer c = (Customer) existingCustomersTable.getSelectionModel().getSelectedItem();
        int customerID = c.getId();
        int consultantId = 1;
        String type = selectedType;

        if (isNewAppointment){
            int id = AppointmentService.getNextId();
            Appointment newAppointment = new Appointment(id, customerID, c.getName(),
                    consultantId, start, end, type);

            AppointmentService.createAppointment(newAppointment);
        } else{
            selectedAppointment.setStart(start);
            selectedAppointment.setEnd(end);
            selectedAppointment.setCustomerID(customerID);
            selectedAppointment.setConsultantID(consultantId);
            selectedAppointment.setType(type);

            AppointmentService.editAppointment(selectedAppointment);
        }

        setAppointmentsTable(AppointmentService.getAllAppointments());

    }

//    TODO: Exception handling for searching. What if the search is blank or a letter?
    @FXML
    void clickSearchAppointments(ActionEvent event) throws Exception {
        int searchedID = new Integer(textSearchAppointments.getText());
        Appointment a = AppointmentService.getAppointment(searchedID);

        setAppointmentsTable(FXCollections.observableArrayList(a));

    }

    @FXML
    void clickSearchCustomers(ActionEvent event) throws Exception {
        String searchText = textSearchCustomers1.getText();

        setCustomersTable(CustomerService.searchCustomersByName(searchText));

    }

    @FXML
    void clickSelectAppointment(ActionEvent event) throws Exception {
        isNewAppointment = false;

        selectedAppointment = (Appointment) existingAppointmentsTable.getSelectionModel().getSelectedItem();


        selectedType = selectedAppointment.getType();
        System.out.println(selectedType);
        if(selectedType.toLowerCase().equals("presentation")){
            type.selectToggle(radioPresentation);
        } else if(selectedType.toLowerCase().equals("scrum")){
            type.selectToggle(radioScrum);
        } else if(selectedType.toLowerCase().equals("planning meeting")){
            type.selectToggle(radioPlanning);
        }


        Customer c = CustomerService.getCustomer(selectedAppointment.getCustomerID());


        ObservableList<Customer> allCustomers = existingCustomersTable.getItems();
        for (Customer cust : allCustomers){
            if(cust.getId() == c.getId()){
                existingCustomersTable.getSelectionModel().select(cust);
            }
        }


        String startDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.S").format(selectedAppointment.getStart());
        String date = startDateTime.substring(0, 10);
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));
        String startHour = startDateTime.substring(11, 13);
        String startMinute = startDateTime.substring(14, 16);
        LocalDate localStartDate = LocalDate.of(year, month, day);

        dateStartDate.setValue(localStartDate);
        comboStartHour.getSelectionModel().select(startHour);
        comboStartMin.getSelectionModel().select(startMinute);

        String endDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.S").format(selectedAppointment.getEnd());
        String endHour = endDateTime.substring(11, 13);
        String endMinute = endDateTime.substring(14, 16);

        comboEndHour.getSelectionModel().select(endHour);
        comboEndMin.getSelectionModel().select(endMinute);

    }

    @FXML
    void clickSelectCustomer(ActionEvent event) {
        selectedCustomer = (Customer) existingCustomersTable.getSelectionModel().getSelectedItem();
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

        isNewAppointment = true;

        try {
            setAppointmentsTable(AppointmentService.getAllAppointments());
        } catch (Exception e) {
            e.printStackTrace();
        }


        selectedType = "";

        for (int i = 1; i < 24; i++) {
            comboStartHour.getItems().add(Integer.toString(i));
            comboEndHour.getItems().add(Integer.toString(i));

        }

        comboStartMin.getItems().add("00");
        comboStartMin.getItems().add("15");
        comboStartMin.getItems().add("30");
        comboStartMin.getItems().add("45");

        comboEndMin.getItems().add("00");
        comboEndMin.getItems().add("15");
        comboEndMin.getItems().add("30");
        comboEndMin.getItems().add("45");

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
