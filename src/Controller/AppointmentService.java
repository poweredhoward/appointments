package Controller;

import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;

import static utilities.TimeConverter.stringToCalendar;

public class AppointmentService {

    //    Get appointment by ID
    public static Appointment getAppointment(int id) throws SQLException, Exception {
        DBConnection.makeConnection();
        String sql = "select * from appointment " +
                " inner join customer on customer.customerId=appointment.customerId" +
                " where appointmentId = " + id + ";";
        DBQuery.executeQuery(sql);
        ResultSet results = DBQuery.getResults();
        results.next();

        int appointmentId = results.getInt("appointmentId");
        Calendar createDate = stringToCalendar(results.getString("createDate"));
        Calendar startTime = stringToCalendar(results.getString("start"));
        Calendar endTime = stringToCalendar(results.getString("end"));
        int customerID = results.getInt("customerId");
        String customerName = results.getString("name");
        int consultantID = results.getInt("userId");
        String type  = results.getString("type");

        Appointment appointment = new Appointment(
                appointmentId,
                customerID,
                customerName,
                consultantID,
                startTime,
                endTime,
                type,
                createDate
        );
        DBConnection.closeConnection();

        return appointment;
    }


    //    Get all appointments
    public static ObservableList<Appointment> getAllAppointments() throws SQLException, Exception {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        DBConnection.makeConnection();
        String sql = "select * from appointment " +
                "inner join customer on customer.customerId=appointment.customerId;";
        DBQuery.executeQuery(sql);
        ResultSet results = DBQuery.getResults();

        while(results.next()){
            int appointmentId = results.getInt("appointmentId");
            Calendar createDate = stringToCalendar(results.getString("createDate"));
            Calendar startTime = stringToCalendar(results.getString("start"));
            Calendar endTime = stringToCalendar(results.getString("end"));
            int customerID = results.getInt("customerId");
            String customerName = results.getString("customerName");
            int consultantID = results.getInt("userId");
            String type  = results.getString("type");

            Appointment appointment = new Appointment(
                    appointmentId,
                    customerID,
                    customerName,
                    consultantID,
                    startTime,
                    endTime,
                    type,
                    createDate
                    );
            allAppointments.add(appointment);
        }

        DBConnection.closeConnection();

        return allAppointments;
    }


    //    Get future appointments for a customer for the next n days


    //    Create an appointment
    public static void createAppointment(Appointment appt) throws Exception {
        String now = "2019-01-01 00:00:00";

        DBConnection.makeConnection();

        String sql = String.format(
                "INSERT INTO appointment" +
                        "VALUES (%d, %d, %d, 'na', 'na', 'na', 'na', '%s', 'na', '%s', '%s', '%s', 'user', '%s', 'user);",
                appt.getId(), appt.getCustomerID(), appt.getConsultantID(), appt.getStart(),
                appt.getEnd(), now, now
        );

        DBQuery.executeQuery(sql);

        DBConnection.closeConnection();
    }


    //    Edit an appointment
    public static void editAppointment(Appointment appointment) throws Exception{
        DBConnection.makeConnection();

        String sql = String.format(
                "UPDATE appointment " +
                        "SET customerId=%d, userId=%d, type='%s', start='%s', end='%s' " +
                        "WHERE appointmentId = %d;",
                appointment.getCustomerID(), appointment.getConsultantID(), appointment.getType(),
                appointment.getStart(), appointment.getEnd(), appointment.getId());

        DBQuery.executeQuery(sql);

        DBConnection.closeConnection();

    }


//    Delete an appointment
    public static void deleteAppointment(int id) throws Exception{
        DBConnection.makeConnection();
        String sql = "DELETE FROM appointment WHERE id = " + id + ";";

        DBQuery.executeQuery(sql);
        DBConnection.closeConnection();
    }


//    Get number of each appointment type for a month
    public static HashMap<String, Integer> getNumberOfAppointmentsByMonth(Calendar beginning, Calendar end) throws Exception {
        DBConnection.makeConnection();

        String sql = String.format(
                "SELECT type, COUNT(type) as quantity FROM appointment" +
                        " WHERE start BETWEEN '%s' AND '%s' GROUP BY type;",
                beginning, end
        );

        DBQuery.executeQuery(sql);
        ResultSet results = DBQuery.getResults();
        HashMap<String, Integer> typeCounts = new HashMap<String, Integer>();

        while (results.next()){
            String type = results.getString("type");
            int count = results.getInt("quantity");

            typeCounts.put(type, count);
        }

        DBConnection.closeConnection();

        return typeCounts;

    }


//    Get all future appointments for a consultant
    public static ObservableList<Appointment> getConsultantFutureAppointments(int consultantId) throws Exception {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        DBConnection.makeConnection();

        String sql = String.format(
                "SELECT * from appointment" +
                        " inner join customer on customer.customerId=appointment.customerId" +
                        " WHERE userId = %d AND start > NOW();",
                consultantId
        );

        DBQuery.executeQuery(sql);
        ResultSet results = DBQuery.getResults();

        while(results.next()){
            int appointmentId = results.getInt("appointmentId");
            Calendar createDate = stringToCalendar(results.getString("createDate"));
            Calendar startTime = stringToCalendar(results.getString("start"));
            Calendar endTime = stringToCalendar(results.getString("end"));
            int customerID = results.getInt("customerId");
            String customerName = results.getString("name");
            int consultantID = results.getInt("userId");
            String type  = results.getString("type");

            Appointment appointment = new Appointment(
                    appointmentId,
                    customerID,
                    customerName,
                    consultantID,
                    startTime,
                    endTime,
                    type,
                    createDate
            );
            appointments.add(appointment);
        }

        DBConnection.closeConnection();

        return appointments;
    }


//    Get all appointments for a consultant for the next n days
    public static ObservableList<Appointment> getConsultantFutureAppointmentsNDays(int consultantId, int days) throws Exception {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        DBConnection.makeConnection();

        String sql = String.format(
                "SELECT * from appointment WHERE userId = %d inner join customer on customer.customerId=appointment.customerId" +
                        " AND start > NOW() AND end <= (NOW() + INTERVAL %d DAYS);",
                consultantId, days
        );

        DBQuery.executeQuery(sql);
        ResultSet results = DBQuery.getResults();

        while(results.next()){
            int appointmentId = results.getInt("appointmentId");
            Calendar createDate = stringToCalendar(results.getString("createDate"));
            Calendar startTime = stringToCalendar(results.getString("start"));
            Calendar endTime = stringToCalendar(results.getString("end"));
            int customerID = results.getInt("customerId");
            String customerName = results.getString("name");
            int consultantID = results.getInt("userId");
            String type  = results.getString("type");

            Appointment appointment = new Appointment(
                    appointmentId,
                    customerID,
                    customerName,
                    consultantID,
                    startTime,
                    endTime,
                    type,
                    createDate
            );
            appointments.add(appointment);
        }

        DBConnection.closeConnection();

        return appointments;
    }



//    Check if there is an appointment within n minutes from now for a given consultant
    public static boolean checkForAppointmentNMinutes(int consultantId, int minutes) throws Exception {
        DBConnection.makeConnection();

        String sql = String.format("SELECT * from appointment WHERE userId = %d AND" +
                " start > NOW() AND start <= (NOW() + INTERVAL %d MINUTE);",
                consultantId, minutes
                );
        DBQuery.executeQuery(sql);
        ResultSet results = DBQuery.getResults();

        if(results.next()){
            return true;
        } else{
            return false;
        }
    }



//    Get all future appointments for a customer for the next n days
    public static ObservableList<Appointment> getCustomerFutureAppointmentsNDays(int customerId, int days) throws Exception {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        DBConnection.makeConnection();

        String sql = String.format(
                "SELECT * from appointment WHERE customerId = %d " +
                        " inner join customer on customer.customerId=appointment.customerId " +
                        "AND start > NOW() AND end <= (NOW() + INTERVAL %d DAYS);",
                customerId, days
        );

        DBQuery.executeQuery(sql);
        ResultSet results = DBQuery.getResults();

        while(results.next()){
            int appointmentId = results.getInt("appointmentId");
            Calendar createDate = stringToCalendar(results.getString("createDate"));
            Calendar startTime = stringToCalendar(results.getString("start"));
            Calendar endTime = stringToCalendar(results.getString("end"));
            int customerID = results.getInt("customerId");
            String customerName = results.getString("name");
            int consultantID = results.getInt("userId");
            String type  = results.getString("type");

            Appointment appointment = new Appointment(
                    appointmentId,
                    customerID,
                    customerName,
                    consultantID,
                    startTime,
                    endTime,
                    type,
                    createDate
            );
            appointments.add(appointment);
        }

        DBConnection.closeConnection();

        return appointments;
    }

}
