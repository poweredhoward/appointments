package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreen implements Initializable {

    @FXML
    private TextField textUsername;

    @FXML
    private TextField textPassword;

    @FXML
    private Button btnLogin;

    @FXML
    void clickLogin(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
