package org.example.dao;

import org.example.models.Employee;
import org.example.util.DatabaseConnection;

import java.sql.*;

public class EmployeeDao {
   // create new Employee
   public boolean createEmployee (Employee employee) {
      String sql = "INSERT INTO employees (name,email,department,salary,hire_date) VALUES (?,?,?,?,?)";

      try (Connection connection = DatabaseConnection.getConnection();
           PreparedStatement preparedStatement =
               connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

         // set values to statement
         preparedStatement.setString(1,employee.getName());
         preparedStatement.setString(2,employee.getEmail());
         preparedStatement.setString(3, employee.getDepartment());
         preparedStatement.setBigDecimal(4,employee.getSalary());
         preparedStatement.setDate(5, Date.valueOf(employee.getHireDate()));

         // execute the Query
         int rowsAffected = preparedStatement.executeUpdate();
         if(rowsAffected > 0){
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
               if(generatedKeys.next()){
                  employee.setId(generatedKeys.getInt(1));
               }
            }
            System.out.println("adding employee "+employee.getId());
            return true;
         }


      } catch (SQLException e) {
         System.out.println("Error creating employee " + e.getMessage());
      }
         return false;
   }
}
