package Controller;

import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import static utilities.TimeConverter.stringToCalendar;

public class CustomerService {
//    Get customer by ID
    public static Customer getCustomer(int id) throws SQLException, Exception{
        DBConnection.makeConnection();
        String sql = "SELECT * FROM customer " +
                " INNER JOIN address on address.addressId = customer.addressId " +
                " INNER JOIN city on city.cityId = address.addressId " +
                " INNER JOIN country on country.countryId = city.countryId" +
                " WHERE customerId = " + id;
        DBQuery.executeQuery(sql);
        ResultSet results = DBQuery.getResults();
        results.next();

        int customerID = results.getInt("customerId");
        Calendar createDate = stringToCalendar(results.getString("createDate"));
        String name = results.getString("customerName");
        String address = getCustomerAddress(results);
        String phone = results.getString("phone");

        Customer customer = new Customer(
                customerID,
                createDate,
                name,
                address,
                phone
        );

        DBConnection.closeConnection();

        return customer;
    }

//    Get all customers
    public static ObservableList<Customer> getAllCustomers() throws SQLException, Exception{
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

        DBConnection.makeConnection();
        String sql = "SELECT * FROM customer " +
                " INNER JOIN address on address.addressId = customer.addressId " +
                " INNER JOIN city on city.cityId = address.addressId " +
                " INNER JOIN country on country.countryId = city.countryId";
        DBQuery.executeQuery(sql);
        ResultSet results = DBQuery.getResults();

        while(results.next()) {
            int customerID = results.getInt("customerId");
            Calendar createDate = stringToCalendar(results.getString("createDate"));
            String name = results.getString("customerName");
            String address = getCustomerAddress(results);
            String phone = results.getString("phone");

            Customer customer = new Customer(
                    customerID,
                    createDate,
                    name,
                    address,
                    phone
            );

            allCustomers.add(customer);
        }

        DBConnection.closeConnection();

        return allCustomers;
}


    //    Search customers by name
    public static ObservableList<Customer> searchCustomersByName(String searchText) throws SQLException, Exception{
        ObservableList<Customer> matches = FXCollections.observableArrayList();

        DBConnection.makeConnection();
        String sql = "SELECT * FROM customer " +
                " INNER JOIN address on address.addressId = customer.addressId " +
                " INNER JOIN city on city.cityId = address.addressId " +
                " INNER JOIN country on country.countryId = city.countryId" +
                " WHERE customerName LIKE '%" + searchText + "%'";
        DBQuery.executeQuery(sql);
        ResultSet results = DBQuery.getResults();

        while(results.next()) {
            int customerID = results.getInt("customerId");
            Calendar createDate = stringToCalendar(results.getString("createDate"));
            String name = results.getString("customerName");
            String address = getCustomerAddress(results);
            String phone = results.getString("phone");

            Customer customer = new Customer(
                    customerID,
                    createDate,
                    name,
                    address,
                    phone
            );

            matches.add(customer);
        }

        DBConnection.closeConnection();

        return matches;
    }


//    Create a customer


//    Edit a customer


//    Delete a customer


    private static String getCustomerAddress(ResultSet customerData) throws SQLException {
        String address = customerData.getString("address");
        String address2 = customerData.getString("address2");
        String city = customerData.getString("city");
        String country = customerData.getString("country");
        String zip = customerData.getString("postalCode");

        return String.format("%s %s %s %s %s", address, address2, city, country, zip);

    }

}
