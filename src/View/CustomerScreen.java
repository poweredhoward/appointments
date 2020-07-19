package View;

import Controller.CustomerService;
import Model.Customer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerScreen implements Initializable {
    private boolean isNewCustomer;

    private Customer selectedCustomer;
    @FXML
    private TextField textCustomerName;

    @FXML
    private TextField textCustomerAddress;

    @FXML
    private TextField textCustomerAddress2;

    @FXML
    private TextField textCustomerCity;

    @FXML
    private TextField textCustomerCountry;

    @FXML
    private TextField textCustomerPostalCode;

    @FXML
    private TextField textCustomerPhone;

    @FXML
    private Button btnSearchCustomers;

    @FXML
    private TextField textSearchCustomers;

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
    private Button btnDeleteCustomer;

    @FXML
    private Button btnSaveCustomer;

    @FXML
    private Button btnCancelSaveCustomer;

    @FXML
    private Button btnSelectCustomer;

    @FXML
    void clickDeleteCustomer(ActionEvent event) throws Exception {
        Customer toBeDeleted = (Customer) existingCustomersTable.getSelectionModel().getSelectedItem();

        CustomerService.deleteCustomer(toBeDeleted.getId());
        setCustomersTable(CustomerService.getAllCustomers());
    }

//    TODO: ID incrementing
    @FXML
    void clickSaveCustomer(ActionEvent event) throws Exception {
        String name = textCustomerName.getText();
        String address = textCustomerAddress.getText();
        String address2 = textCustomerAddress2.getText();
        String city = textCustomerCity.getText();
        String postalCode = textCustomerPostalCode.getText();
        String country = textCustomerCountry.getText();
        String phone = textCustomerPhone.getText();


        if(isNewCustomer){
            int customerid = CustomerService.getNextCustomerId();
            int addressid = CustomerService.getNextAddressId();
            int cityid = CustomerService.getNextCityId();
            int countryid = CustomerService.getNextCountryId();
            Customer c = new Customer(customerid, name, address, addressid, address2,
                    cityid, city, postalCode, countryid, country, phone);

//            selectedCustomer.setId(id);
            CustomerService.createCustomer(c);
        } else {
            selectedCustomer.setName(name);
            selectedCustomer.setAddress(address);
            selectedCustomer.setAddress2(address2);
            selectedCustomer.setCity(city);
            selectedCustomer.setPostalCode(postalCode);
            selectedCustomer.setCountry(country);
            selectedCustomer.setPhone(phone);
            CustomerService.editCustomer(selectedCustomer);
        }


        setCustomersTable(CustomerService.getAllCustomers());

    }

    @FXML
    void clickSearchCustomers(ActionEvent event) throws Exception {
        String searchText = textSearchCustomers.getText();

        setCustomersTable(CustomerService.searchCustomersByName(searchText));
    }

    @FXML
    void clickSelectCustomer(ActionEvent event) throws Exception {
        isNewCustomer = false;
        selectedCustomer = (Customer) existingCustomersTable.getSelectionModel().getSelectedItem();
//        System.out.println(selectedCustomer.toString());

        textCustomerName.setText(selectedCustomer.getName());
        textCustomerAddress.setText(selectedCustomer.getAddress());
        textCustomerAddress2.setText(selectedCustomer.getAddress2());
        textCustomerCity.setText(selectedCustomer.getCity());
        textCustomerPostalCode.setText(selectedCustomer.getPostalCode());
        textCustomerCountry.setText(selectedCustomer.getCountry());
        textCustomerPhone.setText(selectedCustomer.getPhone());

    }

    public void clickCancelSaveCustomer(ActionEvent actionEvent)  throws IOException {
        Stage stage;
        Parent root;

        stage=(Stage) btnCancelSaveCustomer.getScene().getWindow();
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

        isNewCustomer = true;

        try {
            setCustomersTable(CustomerService.getAllCustomers());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCustomersTable(ObservableList<Customer> customerList){
        try {
            existingCustomersTable.setItems(customerList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
