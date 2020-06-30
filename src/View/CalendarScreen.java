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
import javafx.stage.Stage;

import java.io.IOException;

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
    void clickMonthRadio(ActionEvent event) {

    }

    @FXML
    void clickWeekRadio(ActionEvent event) {

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

