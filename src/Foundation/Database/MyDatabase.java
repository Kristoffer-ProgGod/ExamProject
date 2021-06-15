package Foundation.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDatabase {

    //Opens a connection to the SQL database
    public static Connection openConnection() {
        try {
            // (1) load the driver into memory

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // (2) establish Connection
            return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=db_Timeline", "sa", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //Closes the given connection
    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}