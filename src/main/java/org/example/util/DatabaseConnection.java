package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

   //information to connection with database
   public static final  String URL = "jdbc:mysql://localhost:3306/company_db";
   public  static final String username = "root";
   public  static final String password = "";

   // variable to store connection (singleton pattern)
   private static Connection connection;

   // method to get connection with database
   public static Connection getConnection() throws SQLException {
      if (connection == null || connection.isClosed()) {
           try {
              //! 1 load the driver
              Class.forName("com.mysql.cj.jdbc.Driver");
              //! 2 create connection
              connection = DriverManager.getConnection(URL, username, password);
              System.out.println("Connected to database successfully");

           } catch (ClassNotFoundException e) {
              System.out.println("Driver class not found"+e.getMessage());
              throw new SQLException(e);
           }
      }
      return connection;
   }

// method to Close connection

   public static  void closeConnection() {
       if (connection != null) {
          try {
                connection.close();
            } catch (SQLException e) {
             System.out.println("Error closing connection"+e.getMessage());
          }
       }
   }

}
