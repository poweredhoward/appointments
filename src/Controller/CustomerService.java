package Controller;

import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
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
//        String address = getCustomerAddress(results);
        String phone = results.getString("phone");
        String address = results.getString("address");
        int addressId = results.getInt("addressId");
        String address2 = results.getString("address2");
        String city = results.getString("city");
        int cityId = results.getInt("cityId");
        String country = results.getString("country");
        int countryId = results.getInt("countryId");
        String postalCode = results.getString("postalCode");

        Customer customer = new Customer(
                customerID,
                createDate,
                name,
                address,
                addressId,
                address2,
                cityId,
                city,
                postalCode,
                countryId,
                country,
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
//        String address = getCustomerAddress(results);
            String phone = results.getString("phone");
            String address = results.getString("address");
            int addressId = results.getInt("addressId");
            String address2 = results.getString("address2");
            String city = results.getString("city");
            int cityId = results.getInt("cityId");
            String country = results.getString("country");
            int countryId = results.getInt("countryId");
            String postalCode = results.getString("postalCode");

            Customer customer = new Customer(
                    customerID,
                    createDate,
                    name,
                    address,
                    addressId,
                    address2,
                    cityId,
                    city,
                    postalCode,
                    countryId,
                    country,
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
//        String address = getCustomerAddress(results);
            String phone = results.getString("phone");
            String address = results.getString("address");
            int addressId = results.getInt("addressId");
            String address2 = results.getString("address2");
            String city = results.getString("city");
            int cityId = results.getInt("cityId");
            String country = results.getString("country");
            int countryId = results.getInt("countryId");
            String postalCode = results.getString("postalCode");

            Customer customer = new Customer(
                    customerID,
                    createDate,
                    name,
                    address,
                    addressId,
                    address2,
                    cityId,
                    city,
                    postalCode,
                    countryId,
                    country,
                    phone
            );

            matches.add(customer);
        }

        DBConnection.closeConnection();

        return matches;
    }


//    Create a customer
    public static void createCustomer(Customer customer) throws Exception {
//        Calendar cal = Calendar.getInstance();
//        Date now = new Date(cal.getTimeInMillis());

        String now = "2020-06-30 00:00:00";

        DBConnection.makeConnection();
        String country_sql = String.format(
                "INSERT INTO country " +
                        "VALUES (%d, '%s', '%s', 'user', '%s', 'user');",
                customer.getCountryId(), customer.getCountry(), now, now);

        String city_sql = String.format(
                "INSERT INTO city " +
                        "VALUES (%d, '%s', %d, '%s', 'user', '%s', 'user');",
                customer.getCityId(), customer.getCity(), customer.getCountryId(), now, now);

        String address_sql = String.format(
                "INSERT INTO address" +
                        " VALUES (%d, '%s', '%s', %d, '%s', '%s', '%s', 'user', '%s', 'user');",
                customer.getAddressId(), customer.getAddress(), customer.getAddress2(),
                customer.getCityId(), customer.getPostalCode(), customer.getPhone(), now, now
        );

        String customer_sql = String.format(
                "INSERT INTO customer VALUES (%d, '%s', %d, 1, '%s', 'user', '%s', 'user');",
                customer.getId(), customer.getName(), customer.getAddressId(), now, now
        );

        DBQuery.executeQuery(country_sql);
        DBQuery.executeQuery(city_sql);
        DBQuery.executeQuery(address_sql);
        DBQuery.executeQuery(customer_sql);

        DBConnection.closeConnection();


    }

//    Edit a customer
    public static void editCustomer(Customer customer) throws Exception {
        String now = "2019-01-01 00:00:00";

        DBConnection.makeConnection();

        String country_sql = String.format(
                "UPDATE country " +
                        "SET country='%s' WHERE countryId = %d;",
                customer.getCountry(), customer.getCountryId());

        String city_sql = String.format(
                "UPDATE city " +
                        "SET city = '%s' WHERE cityId = %d;",
                customer.getCity(), customer.getCityId());

        String address_sql = String.format(
                "UPDATE address " +
                        "SET address='%s', address2='%s', postalCode='%s', phone='%s' " +
                        "WHERE addressId = %d;",
                customer.getAddress(), customer.getAddress2(),
                customer.getPostalCode(), customer.getPhone(), customer.getAddressId());

        String customer_sql = String.format(
                "UPDATE customer " +
                        "SET customerName = '%s' WHERE customerId = '%s';",
                customer.getName(), customer.getId());

        DBQuery.executeQuery(country_sql);
        DBQuery.executeQuery(city_sql);
        DBQuery.executeQuery(address_sql);
        DBQuery.executeQuery(customer_sql);

        DBConnection.closeConnection();

    }


//    Delete a customer
    public static void deleteCustomer(int id) throws Exception{
        DBConnection.makeConnection();
        String sql = "DELETE FROM customer WHERE id = " + id;

        DBQuery.executeQuery(sql);
        DBConnection.closeConnection();
    }



//    private static String getCustomerAddress(ResultSet customerData) throws SQLException {
//        String address = customerData.getString("address");
//        String address2 = customerData.getString("address2");
//        String city = customerData.getString("city");
//        String country = customerData.getString("country");
//        String zip = customerData.getString("postalCode");
//
//        return String.format("%s %s %s %s %s", address, address2, city, country, zip);
//
//    }

}
