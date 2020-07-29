package Controller;

import Model.Consultant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;


public class ConsultantService {

    //    Get consultant by username
    public static Consultant getConsultant(String username) throws SQLException, Exception {
        DBConnection.makeConnection();
        String sql = "select * from user where userName = '" + username + "';";
        DBQuery.executeQuery(sql);
        ResultSet results = DBQuery.getResults();
        Consultant consultant = null;
        while (results.next()){
            int userId = results.getInt("userId");
            String name  = results.getString("userName");
            String password  = results.getString("password");


            consultant = new Consultant(
                    userId,
                    name,
                    password
            );
        }


        DBConnection.closeConnection();

        return consultant;
    }


//    Search consultant by username
    public static ObservableList<Consultant> searchConsultantByUsername(String searchText) throws SQLException, Exception {
        ObservableList<Consultant> matches = FXCollections.observableArrayList();

        DBConnection.makeConnection();
        String sql = "select * from user where userName like '%" + searchText + "%'";
        DBQuery.executeQuery(sql);
        ResultSet results = DBQuery.getResults();

        while(results.next()){
            int userId = results.getInt("userId");
            String username  = results.getString("username");
            String password  = results.getString("password");


            Consultant consultant = new Consultant(
                    userId,
                    username,
                    password
            );
            matches.add(consultant);
        }

        DBConnection.closeConnection();

        return matches;
    }


    //    Get all consultants
    public static ObservableList<Consultant> getAllConsultants() throws SQLException, Exception {
        ObservableList<Consultant> consultants = FXCollections.observableArrayList();

        DBConnection.makeConnection();
        String sql = "select * from user";
        DBQuery.executeQuery(sql);
        ResultSet results = DBQuery.getResults();

        while(results.next()){
            int userId = results.getInt("userId");
            String username  = results.getString("username");
            String password  = results.getString("password");


            Consultant consultant = new Consultant(
                    userId,
                    username,
                    password
            );
            consultants.add(consultant);
        }

        DBConnection.closeConnection();

        return consultants;
    }


}
