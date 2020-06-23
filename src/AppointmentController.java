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
        Customer a = CustomerService.getCustomer(1);
        System.out.println(a.toString());

    }


    public static void main(String[] args) {
        launch(args);
    }

}

