package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CalendarScreen {

    @FXML
    private RadioButton radioWeekView;

    @FXML
    private RadioButton radioMonthView;

    @FXML
    private TableView<?> tableCalendar;

    @FXML
    private TableColumn<?, ?> colAppointmentDay;

    @FXML
    private TableColumn<?, ?> colAppointmentTime;

    @FXML
    private TableColumn<?, ?> colAppointmentCustomer;

    @FXML
    private TableColumn<?, ?> colAppointmentType;

    @FXML
    private Button btnBack;

    @FXML
    void clickBtnBack(ActionEvent event) {

    }

    @FXML
    void clickMonthRadio(ActionEvent event) {

    }

    @FXML
    void clickWeekRadio(ActionEvent event) {

    }

}

