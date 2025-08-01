package org.example.dao;

import org.example.models.Employee;
import org.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

   // - ===================== Crud operation ===========================
   //! create new Employee
   public boolean createEmployee (Employee employee) {
      String sql = "INSERT INTO employees (name,email,department,salary,hire_date) VALUES (?,?,?,?,?)";

      try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(
          sql,
          Statement.RETURN_GENERATED_KEYS)) {

         // set values to statement
         preparedStatement.setString(1, employee.getName());
         preparedStatement.setString(2, employee.getEmail());
         preparedStatement.setString(3, employee.getDepartment());
         preparedStatement.setBigDecimal(4, employee.getSalary());
         preparedStatement.setDate(5, Date.valueOf(employee.getHireDate()));

         // execute the Query
         int rowsAffected = preparedStatement.executeUpdate();
         if (rowsAffected > 0) {
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
               if (generatedKeys.next()) {
                  employee.setId(generatedKeys.getInt(1));
               }
            }
            System.out.println("adding employee " + employee.getId());
            return true;
         }

      } catch (SQLException e) {
         System.out.println("Error creating employee " + e.getMessage());
      }
      return false;
   }

   //! get Employee by id
   public Employee getEmployeeByI (int employeeId) {
      // create sql
      String sql = "SELECT * FROM employees WHERE id = ?";
      try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(
          sql)) {
         //set values
         preparedStatement.setInt(1, employeeId);
         // execute query
         try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
               return extractEmployeeFromResultSet(resultSet);
            }
         }
      } catch (Exception e) {
         System.out.println("error to get employee" + e.getMessage());
         throw new RuntimeException(e);
      }
      return null;
   }


   //! get All Employees
   public List<Employee> getAllEmployees () {
      List<Employee> employees = new ArrayList<Employee>();

      // create statement
      String sql = "SELECT * FROM employees";
      try (Connection connection = DatabaseConnection.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(
          sql)) {
         // add employees in List
         while (resultSet.next()) {
            Employee employee = extractEmployeeFromResultSet(resultSet);

            employees.add(employee);
         }
      } catch (SQLException e) {
         System.out.println("error to get employees " + e.getMessage());
      }

      return employees;
   }


   //! update employee
   public boolean updateEmployee (Employee employee) {
      String sql = "update employees set name = ?, email = ?, department = ?, salary = ?, hire_date= ? where id = ?";
      try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(
          sql)) {
         preparedStatement.setString(1, employee.getName());
         preparedStatement.setString(2, employee.getEmail());
         preparedStatement.setString(3, employee.getDepartment());
         preparedStatement.setBigDecimal(4, employee.getSalary());
         preparedStatement.setDate(5, Date.valueOf(employee.getHireDate()));
         preparedStatement.setInt(6, employee.getId());

         int rowEffected = preparedStatement.executeUpdate();
         if (rowEffected > 0) {
            System.out.println("employee has update successfully");
            return true;
         }

      } catch (Exception e) {
         throw new RuntimeException(e);
      }
      return false;
   }


   public boolean deleteEmployee (int employeeId) {

      String sql = "Delete from employees where id = ?";

      try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(
          sql)) {
         preparedStatement.setInt(1, employeeId);
         int rowEffected = preparedStatement.executeUpdate();
         if (rowEffected > 1) {
            System.out.println("employee has deleted with success ");
            return true;
         }
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
      return false;
   }

   //-================= Demonstration =======================
   //! demonstration to add Employee
   public void demoAddEmployee (Employee employee) {
      createEmployee(employee);
   }

   //! demonstrate to read all records
   public void demonstrateReadAll () {
      List<Employee> employees = getAllEmployees();
      for (Employee employee : employees) {
         System.out.println(employees);
      }
   }

   //! demonstrate to read one record
   public void demonstrateReadById (int emplyeeId) {
      System.out.println(getEmployeeByI(emplyeeId));
   }

   //! Delete Employee
   public void demonstrateUpdate (Employee employee) {
      System.out.println(updateEmployee(employee));
   }

   //! Delete Employee
   public void demonstrateDelete (int employeeId) {
      System.out.println(deleteEmployee(employeeId));
   }

   //-=========== method completion ==========
   //! extractEmployee
   private Employee extractEmployeeFromResultSet (ResultSet resultSet) throws SQLException {

      Employee employee = new Employee();
      employee.setId(resultSet.getInt("id"));
      employee.setName(resultSet.getString("name"));
      employee.setEmail(resultSet.getString("email"));
      employee.setDepartment(resultSet.getString("department"));
      employee.setSalary(resultSet.getBigDecimal("salary"));
      employee.setHireDate(resultSet.getDate("hire_date").toLocalDate());
      return employee;
   }
}
