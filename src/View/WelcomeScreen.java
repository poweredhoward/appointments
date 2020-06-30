package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeScreen {

    @FXML
    private Label upcomingAppointmentAlert;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnCalendar;

    @FXML
    private Button btnAppointments;

    @FXML
    private Button btnReports;

    @FXML
    void clickAppointments(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) btnAppointments.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "AppointmentScreen.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
//        AddPartScreen controller = loader.getController();
//        controller.getInventory(inventory);
    }

    @FXML
    void clickCalendar(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) btnCalendar.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "CalendarScreen.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
//        AddPartScreen controller = loader.getController();
//        controller.getInventory(inventory);
    }

    @FXML
    void clickCustomers(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) btnCustomers.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "CustomerScreen.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
//        AddPartScreen controller = loader.getController();
//        controller.getInventory(inventory);
    }

    @FXML
    void clickReports(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) btnReports.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "ReportScreen.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
//        AddPartScreen controller = loader.getController();
//        controller.getInventory(inventory);
    }

}
