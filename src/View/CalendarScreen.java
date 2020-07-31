package View;

import Controller.AppointmentService;
import Model.Appointment;
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
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

public class CalendarScreen implements Initializable {
    public ToggleGroup viewToggle;
    private String selectedView;


    @FXML
    private RadioButton radioWeekView;

    @FXML
    private RadioButton radioMonthView;

    @FXML
    private TableView<Appointment> tableCalendar;

    @FXML
    private TableColumn<?, ?> colAppointmentStart;

    @FXML
    private TableColumn<?, ?> colAppointmentEnd;

    @FXML
    private TableColumn<?, ?> colAppointmentCustomer;

    @FXML
    private TableColumn<?, ?> colAppointmentType;

    @FXML
    private Button btnBack;

    @FXML
    void clickMonthRadio(ActionEvent event) throws Exception {
        selectedView = "Month";
        fillOutCalendar();
    }

    @FXML
    void clickWeekRadio(ActionEvent event) throws Exception {
        selectedView = "Week";
        fillOutCalendar();
    }

    private void fillOutCalendar() throws Exception {
        if(selectedView.equals("Month")){
            ObservableList<Appointment> appointments = AppointmentService.getConsultantAppointmentsThisMonth(1);
            tableCalendar.setItems(appointments);
        } else {
            ObservableList<Appointment> appointments = AppointmentService.getFutureAppointmentsForNDays(7);
            tableCalendar.setItems(appointments);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedView = "Month";
        viewToggle.selectToggle(radioMonthView);
        colAppointmentStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        colAppointmentEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        colAppointmentCustomer.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colAppointmentType.setCellValueFactory(new PropertyValueFactory<>("type"));

        try {
            fillOutCalendar();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void clickBtnBack(ActionEvent event) throws IOException {
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

}

