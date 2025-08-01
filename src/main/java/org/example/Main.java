package org.example;

import org.example.dao.EmployeeDao;
import org.example.models.Employee;

import java.math.BigDecimal;
import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
   public static void main (String[] args) {

      //* test operations
      EmployeeDao employeeDao = new EmployeeDao();
      //! create
      Employee employee = new Employee("mohamed rhziza",
          "mohame2d@gmail.com",
          "it",
          BigDecimal.valueOf(20000.00),
          LocalDate.now());
      Employee employee1 = new Employee("test test",
          "test@gmail.com",
          "test",
          BigDecimal.valueOf(20000.00),
          LocalDate.now());
      employeeDao.demoAddEmployee(employee);
      employeeDao.demoAddEmployee(employee1);
      //! read
      employeeDao.demonstrateReadById(1);
      employeeDao.demonstrateReadById(2);
      //! read all
      employeeDao.demonstrateReadAll();
      //! update
      Employee employeeUpdate = employeeDao.getEmployeeByI(1);
      employeeUpdate.setName("ayoub laaraj");
      employeeUpdate.setName("ayoub@gmail.com");
      employeeUpdate.setSalary(new BigDecimal(20000));
      employeeDao.demonstrateUpdate(employeeUpdate);
      //! delete
      employeeDao.demonstrateUpdate(employeeDao.getEmployeeByI(1));

   }
}