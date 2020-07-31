package Controller;

import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.util.Calendar;
import java.util.HashMap;

import static utilities.TimeConverter.dateTimeToString;
import static utilities.TimeConverter.stringToDateTime;

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
//        ZonedDateTime createDate = stringToDateTime(results.getString("createDate"));
        ZonedDateTime startTime = stringToDateTime(results.getString("start"));
        ZonedDateTime endTime = stringToDateTime(results.getString("end"));
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
                type
        );
        DBConnection.closeConnection();

        return appointment;
    }


    //    Get all appointments
    public static ObservableList<Appointment> getAllAppointments() throws SQLException, Exception {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        DBConnection.makeConnection();
        String sql = "select * from appointment " +
                "inner join customer on customer.customerId=appointment.customerId ORDER BY appointmentId;";
        DBQuery.executeQuery(sql);
        ResultSet results = DBQuery.getResults();

        while(results.next()){
            int appointmentId = results.getInt("appointmentId");
//            ZonedDateTime createDate = stringToDateTime(results.getString("createDate"));
            ZonedDateTime startTime = stringToDateTime(results.getString("start"));
            ZonedDateTime endTime = stringToDateTime(results.getString("end"));
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
                    type
                    );
            allAppointments.add(appointment);
        }

        DBConnection.closeConnection();

        return allAppointments;
    }


    //    Get future appointments for a customer for the next n days


    //    Create an appointment
    public static void createAppointment(Appointment appt) throws Exception {
        DBConnection.makeConnection();

        String sql = String.format(
                "INSERT INTO appointment" +
                        " VALUES (%d, %d, %d, 'na', 'na', 'na', 'na', '%s', 'na', '%s', '%s', NOW(), 'user', NOW(), 'user');",
                appt.getId(), appt.getCustomerID(), appt.getConsultantID(), appt.getType(),
                dateTimeToString(appt.getStart()), dateTimeToString(appt.getEnd())
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
        String sql = "DELETE FROM appointment WHERE appointmentId = " + id + ";";

        DBQuery.executeQuery(sql);
        DBConnection.closeConnection();
    }


//    Get number of each appointment type for a month
    public static ObservableList<MonthResult> getNumberOfAppointmentsByMonth() throws Exception {
        ObservableList<MonthResult> rows = FXCollections.observableArrayList();
        DBConnection.makeConnection();

        String sql = String.format(
                "SELECT type, monthname(start) as month, COUNT(type) as count FROM appointment GROUP BY type, monthname(start);"
        );

        DBQuery.executeQuery(sql);
        ResultSet results = DBQuery.getResults();

        while (results.next()){
            String type = results.getString("type");
            String month = results.getString("month");
            int count = results.getInt("count");
            MonthResult monthResult = new MonthResult(type, month, count);

            rows.add(monthResult);
        }

        DBConnection.closeConnection();

        return rows;

    }


//    Get all future appointments for a consultant for n days
    public static ObservableList<Appointment> getFutureAppointmentsForNDays(int days) throws Exception {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        DBConnection.makeConnection();

        String sql = String.format(
                "SELECT * from appointment" +
                        " inner join customer on customer.customerId=appointment.customerId" +
                        " WHERE start > NOW() AND end <= (NOW() + INTERVAL %d DAY);",
                days
        );

        DBQuery.executeQuery(sql);
        ResultSet results = DBQuery.getResults();

        while(results.next()){
            int appointmentId = results.getInt("appointmentId");
//            ZonedDateTime createDate = stringToDateTime(results.getString("createDate"));
            ZonedDateTime startTime = stringToDateTime(results.getString("start"));
            ZonedDateTime endTime = stringToDateTime(results.getString("end"));
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
                    type
            );
            appointments.add(appointment);
        }

        DBConnection.closeConnection();

        return appointments;
    }


//    Get all appointments for a consultant for the whole current month
    public static ObservableList<Appointment> getConsultantAppointmentsThisMonth(int consultantId) throws Exception {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        DBConnection.makeConnection();

        String sql = String.format(
                "SELECT * from appointment inner join customer on customer.customerId=appointment.customerId" +
                        "  WHERE userId = %d AND YEAR(start) = YEAR(CURRENT_DATE()) AND MONTH(start) = MONTH(CURRENT_DATE());",
                consultantId
        );

        DBQuery.executeQuery(sql);
        ResultSet results = DBQuery.getResults();

        while(results.next()){
            int appointmentId = results.getInt("appointmentId");
            ZonedDateTime startTime = stringToDateTime(results.getString("start"));
            ZonedDateTime endTime = stringToDateTime(results.getString("end"));
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
                    type
            );
            appointments.add(appointment);
        }

        DBConnection.closeConnection();

        return appointments;
    }


    //    Get all future appointments for a customer for the next n days
    public static ObservableList<Appointment> getCustomerFutureAppointmentsNDays(int customerId, int days) throws Exception {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        DBConnection.makeConnection();

        String sql = String.format(
                "SELECT * from appointment" +
                        " inner join customer on customer.customerId=appointment.customerId " +
                        "  WHERE appointment.customerId = %d  AND start > NOW() AND end <= (NOW() + INTERVAL %d DAY);",
                customerId, days
        );

        DBQuery.executeQuery(sql);
        ResultSet results = DBQuery.getResults();

        while(results.next()){
            int appointmentId = results.getInt("appointmentId");
//            ZonedDateTime createDate = stringToDateTime(results.getString("createDate"));
            ZonedDateTime startTime = stringToDateTime(results.getString("start"));
            ZonedDateTime endTime = stringToDateTime(results.getString("end"));
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
                    type
            );
            appointments.add(appointment);
        }

        DBConnection.closeConnection();

        return appointments;
    }

    public static int getNextId() throws Exception {
        DBConnection.makeConnection();

        String count_query = "SELECT MAX(appointmentId) as 'maxId' from appointment;";
        DBQuery.executeQuery(count_query);

        ResultSet r = DBQuery.getResults();
        r.next();
        int next_id = r.getInt("maxId") + 1;

        DBConnection.closeConnection();
        return next_id;
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

    public static class MonthResult {
        public String type;
        public String month;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int count;

        public MonthResult(String type, String month, int count){
            this.type = type;
            this.month = month;
            this.count = count;
        }
    }



}
