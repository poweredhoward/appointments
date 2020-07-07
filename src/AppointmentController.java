import Controller.AppointmentService;
import Controller.CustomerService;
import Model.Appointment;
import Model.Customer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Calendar;

// TODO
//Get rid of the unnecessary imports
public class AppointmentController extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/LoginScreen.fxml"));
        primaryStage.setTitle("Appointments");
        primaryStage.setScene(new Scene(root, 1400, 450));
        primaryStage.show();

//        ObservableList<Appointment> a = AppointmentService.getAllAppointments();
//        Customer b = CustomerService.getCustomer(1);
//        System.out.println(a.toString());

//        Customer c = new Customer(
//                5,
//                Calendar.getInstance(),
//                "Jeff Smith",
//                "135 Wilshire Way",
//                5,
//                "#6",
//                7,
//                "Athens",
//                "98765",
//                5,
//                "Greece",
//                "1112223333"
//        );
//
//        CustomerService.createCustomer(c);

    }


    public static void main(String[] args) {
        launch(args);
    }

}

