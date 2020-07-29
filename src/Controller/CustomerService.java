package Controller;

import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Calendar;

import static utilities.TimeConverter.stringToDateTime;

public class CustomerService {
//    Get customer by ID
    public static Customer getCustomer(int id) throws SQLException, Exception{
        DBConnection.makeConnection();
        String sql = "SELECT * FROM customer " +
                " INNER JOIN address on address.addressId = customer.addressId " +
                " INNER JOIN city on city.cityId = address.cityId " +
                " INNER JOIN country on country.countryId = city.countryId" +
                " WHERE customerId = " + id;
        DBQuery.executeQuery(sql);
        ResultSet results = DBQuery.getResults();
        results.next();

        int customerID = results.getInt("customerId");
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
                " INNER JOIN city on city.cityId = address.cityId " +
                " INNER JOIN country on country.countryId = city.countryId";
        DBQuery.executeQuery(sql);
        ResultSet results = DBQuery.getResults();

        while(results.next()) {
            int customerID = results.getInt("customerId");
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
                " INNER JOIN city on city.cityId = address.cityId " +
                " INNER JOIN country on country.countryId = city.countryId" +
                " WHERE customerName LIKE '%" + searchText + "%'";
        DBQuery.executeQuery(sql);
        ResultSet results = DBQuery.getResults();

        while(results.next()) {
            int customerID = results.getInt("customerId");
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

    //    Get a customer by name
    public static Customer getCustomersByName(String customerName) throws SQLException, Exception{
        Customer customer = null;

        DBConnection.makeConnection();
        String sql = "SELECT * FROM customer " +
                " INNER JOIN address on address.addressId = customer.addressId " +
                " INNER JOIN city on city.cityId = address.cityId " +
                " INNER JOIN country on country.countryId = city.countryId" +
                " WHERE customerName = '" + customerName + "'";
        DBQuery.executeQuery(sql);
        ResultSet results = DBQuery.getResults();

        while(results.next()) {
            int customerID = results.getInt("customerId");
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

            customer = new Customer(
                    customerID,
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

        }

        DBConnection.closeConnection();

        return customer;
    }


//    Create a customer
    public static void createCustomer(Customer customer) throws Exception {
//        Calendar cal = Calendar.getInstance();
//        Date now = new Date(cal.getTimeInMillis());

//        String now = "2020-06-30 00:00:00";

        DBConnection.makeConnection();

        String country_sql = String.format(
                "INSERT INTO country " +
                        "VALUES (%d, '%s', NOW(), 'user', NOW(), 'user');",
                customer.getCountryId(), customer.getCountry());
        DBQuery.executeQuery(country_sql);


        String city_sql = String.format(
                "INSERT INTO city " +
                        "VALUES (%d, '%s', %d, NOW(), 'user', NOW(), 'user');",
                customer.getCityId(), customer.getCity(), customer.getCountryId());
        DBQuery.executeQuery(city_sql);


        String address_sql = String.format(
                "INSERT INTO address" +
                        " VALUES (%d, '%s', '%s', %d, '%s', '%s', NOW(), 'user', NOW(), 'user');",
                customer.getAddressId(), customer.getAddress(), customer.getAddress2(),
                customer.getCityId(), customer.getPostalCode(), customer.getPhone()
        );
        DBQuery.executeQuery(address_sql);


        String customer_sql = String.format(
                "INSERT INTO customer VALUES (%d, '%s', %d, 1, NOW(), 'user', NOW(), 'user');",
                customer.getId(), customer.getName(), customer.getAddressId()
        );
        DBQuery.executeQuery(customer_sql);

        DBConnection.closeConnection();

    }

    public static int getNextCustomerId() throws Exception {
        DBConnection.makeConnection();

        String count_query = "SELECT MAX(customerId) as 'maxId' from customer;";
        DBQuery.executeQuery(count_query);

        ResultSet r = DBQuery.getResults();
        r.next();
        int next_id = r.getInt("maxId") + 1;

        DBConnection.closeConnection();
        return next_id;
    }

    public static int getNextAddressId() throws Exception {
        DBConnection.makeConnection();

        String count_query = "SELECT MAX(addressId) as 'maxId' from address;";
        DBQuery.executeQuery(count_query);

        ResultSet r = DBQuery.getResults();
        r.next();
        int next_id = r.getInt("maxId") + 1;

        DBConnection.closeConnection();
        return next_id;
    }

    public static int getNextCityId() throws Exception {
        DBConnection.makeConnection();

        String count_query = "SELECT MAX(cityId) as 'maxId' from city;";
        DBQuery.executeQuery(count_query);

        ResultSet r = DBQuery.getResults();
        r.next();
        int next_id = r.getInt("maxId") + 1;

        DBConnection.closeConnection();
        return next_id;
    }

    public static int getNextCountryId() throws Exception {
        DBConnection.makeConnection();

        String count_query = "SELECT MAX(countryId) as 'maxId' from country;";
        DBQuery.executeQuery(count_query);

        ResultSet r = DBQuery.getResults();
        r.next();
        int next_id = r.getInt("maxId") + 1;

        DBConnection.closeConnection();
        return next_id;
    }

//    Edit a customer
    public static void editCustomer(Customer customer) throws Exception {

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
        String sql = "DELETE FROM customer WHERE customerId = " + id;

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
