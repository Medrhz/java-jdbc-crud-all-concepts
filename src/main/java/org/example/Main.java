package org.example;

import org.example.dao.EmployeeDao;
import org.example.models.Employee;

import java.math.BigDecimal;
import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
   public static void main (String[] args) {

      // test crud operation


      Employee employee = new Employee("mohamed rhziza","mohamed@gmail.com","it", BigDecimal.valueOf(20000.00),
          LocalDate.now());

      EmployeeDao employeeDao = new EmployeeDao();

      Boolean isCreated = employeeDao.createEmployee(employee);

      System.out.println(isCreated ? "Employee created" : "Employee not created");
   }
}