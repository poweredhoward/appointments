package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String databaseName="U077jS";
    private static final String DB_URL="jdbc:mysql://3.227.166.251/"+databaseName;
    private static final String username="U077jS";
    private static final String password="53688956782";
    private static final String driver="com.mysql.jdbc.Driver";
    static Connection conn;
    public static void makeConnection()throws ClassNotFoundException, SQLException, Exception
    {
        Class.forName(driver);
        conn=(Connection) DriverManager.getConnection(DB_URL,username,password);
        System.out.println("Connection Successful");
    }
    public static void closeConnection()throws ClassNotFoundException, SQLException, Exception{
        conn.close();
        System.out.println("Connection Closed");
    }
}
