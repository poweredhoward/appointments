package Controller;

import static Controller.DBConnection.conn;
import java.sql.ResultSet;
import java.sql.Statement;


public class DBQuery {
    private static ResultSet results;
    private static boolean updateSuccessful;

    public static void executeQuery(String query) {
        query = query.toLowerCase();
        try {
            Statement statement = conn.createStatement();
            if(query.startsWith("select")){
                results = statement.executeQuery(query);
            } else if(query.startsWith("insert") || query.startsWith("update") ||
                      query.startsWith("delete")){
                statement.executeUpdate(query);
            }
        } catch (Exception ex) {
            System.out.println("Error executing query: " + ex.getMessage());
        }
    }

    public static ResultSet getResults(){
        return results;
    }

    public static boolean getUpdateSuccessful(){
        return updateSuccessful;
    }
}
