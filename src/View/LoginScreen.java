package View;

import Controller.AppointmentService;
import Controller.ConsultantService;
import Model.Consultant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utilities.LoginLogging;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginScreen implements Initializable {
    private String locale;
    private String englishErrorMessage = "The username and password did not match";
    private String spanishErrorMessage = "El nombre de usuario y la contraseña no coincidían";
    private String frenchErrorMessage  = "Le nom d'utilisateur et le mot de passe ne correspondent pas";

    private String englishTitle = "Appointments";
    private String spanishTitle = "Citas";
    private String frenchTitle = "Rendez-vous";

    private String englishUsername = "Username";
    private String spanishUsername = "Nombre de usuario";
    private String frenchUsername = "Nom d'utilisateur";

    private String englishPassword = "Password";
    private String spanishPassword = "Contraseña";
    private String frenchPassword = "Mot de passe";

    private String englishButton = "Log In";
    private String spanishButton = "Inicie sesión";
    private String frenchButton = "S'identifier";


    @FXML
    private TextField textUsername;

    @FXML
    private TextField textPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Label labelAppointments;

    @FXML
    private Label labelUsername;

    @FXML
    private Label labelPassword;

    @FXML
    private Text errorText;

    private boolean checkLoginCredentials() throws Exception {
        String userName = textUsername.getText();
        String password = textPassword.getText();

        return ConsultantService.checkLoginCredentials(userName, password);
    }

    @FXML
    void clickLogin(ActionEvent event) throws IOException, Exception {
        if (checkLoginCredentials()){
            Consultant consultant = ConsultantService.getConsultant(textUsername.getText());
            if(AppointmentService.checkForAppointmentNMinutes(consultant.getId(),15)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have an appointment in the next 15 minutes");
                alert.setHeaderText("Appointment Reminder");
                alert.showAndWait();
            }
            LoginLogging.addEntry(consultant);
            Stage stage;
            Parent root;
            stage = (Stage) btnLogin.getScene().getWindow();
            //load up OTHER FXML document
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "WelcomeScreen.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            if (locale.equals("es_MX")) {
                errorText.setText(spanishErrorMessage);
                errorText.setVisible(true);
                throw new Exception(spanishErrorMessage);
            } else if (locale.equals("fr_FR")) {
                errorText.setText(frenchErrorMessage);
                errorText.setVisible(true);
                throw new Exception(frenchErrorMessage);
            } else {
                errorText.setText(englishErrorMessage);
                errorText.setVisible(true);
                throw new Exception(englishErrorMessage);
            }

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        locale = Locale.getDefault().toString();

        if (locale.equals("es_MX")) {
            labelAppointments.setText(spanishTitle);
            labelUsername.setText(spanishUsername);
            labelPassword.setText(spanishPassword);
            btnLogin.setText(spanishButton);
        } else if (locale.equals("fr_FR")) {
            labelAppointments.setText(frenchTitle);
            labelUsername.setText(frenchUsername);
            labelPassword.setText(frenchPassword);
            btnLogin.setText(frenchButton);
        } else {
            labelAppointments.setText(englishTitle);
            labelUsername.setText(englishUsername);
            labelPassword.setText(englishPassword);
            btnLogin.setText(englishButton);
        }


    }
}
