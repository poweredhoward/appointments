package Controller;

import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import static utilities.TimeConverter.stringToCalendar;

public class AppointmentService {

    //    Get appointment by ID
    public static Appointment getAppointment(int id) throws SQLException, Exception {
        DBConnection.makeConnection();
        String sql = "select * from appointment where appointmentId = " + id;
        DBQuery.executeQuery(sql);
        ResultSet results = DBQuery.getResults();
        results.next();

        int appointmentId = results.getInt("appointmentId");
        Calendar createDate = stringToCalendar(results.getString("createDate"));
        Calendar startTime = stringToCalendar(results.getString("start"));
        int customerID = results.getInt("customerId");
        int consultantID = results.getInt("userId");
        String type  = results.getString("type");

        Appointment appointment = new Appointment(
                appointmentId,
                createDate,
                startTime,
                customerID,
                consultantID,
                type
        );
        DBConnection.closeConnection();

        return appointment;
    }


    //    Get all appointments
    public static ObservableList<Appointment> getAllAppointments() throws SQLException, Exception {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        DBConnection.makeConnection();
        String sql = "select * from appointment";
        DBQuery.executeQuery(sql);
        ResultSet results = DBQuery.getResults();

        while(results.next()){
            int appointmentId = results.getInt("appointmentId");
            Calendar createDate = stringToCalendar(results.getString("createDate"));
            Calendar startTime = stringToCalendar(results.getString("start"));
            int customerID = results.getInt("customerId");
            int consultantID = results.getInt("userId");
            String type  = results.getString("type");

            Appointment appointment = new Appointment(
                    appointmentId,
                    createDate,
                    startTime,
                    customerID,
                    consultantID,
                    type
            );
            allAppointments.add(appointment);
        }

        DBConnection.closeConnection();

        return allAppointments;
    }


    //    Get future appointments for a customer for the next n days


    //    Create an appointment


    //    Edit an appointment


//    Delete an appointment


//    Get number of each appointment type for a month


//    Get all future appointments for a consultant


//    Get all appointments for a consultant for the next n days


//    Check if there is an appointment within n minutes from now for a given consultant


//    Get all future appointments for a customer for the next n days

}
