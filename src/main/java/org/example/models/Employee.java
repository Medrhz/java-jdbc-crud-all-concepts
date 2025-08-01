package org.example.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {
   private int id;
   private String name;
   private String email;
   private String department;
   private BigDecimal salary;
   private LocalDate hireDate;

   // default constructor
   public Employee(){}

   // full Constructor
   public Employee(int id, String name, String email, String department, BigDecimal salary, LocalDate hireDate) {
      this.id = id;
      this.name = name;
      this.email = email;
      this.department = department;
      this.salary = salary;
      this.hireDate = hireDate;
   }

   // constructor without id
   public Employee( String name, String email, String department, BigDecimal salary, LocalDate hireDate) {
      this.name = name;
      this.email = email;
      this.department = department;
      this.salary = salary;
      this.hireDate = hireDate;
   }


   // getters and setters
   public int getId () {
      return id;
   }

   public void setId (int id) {
      this.id = id;
   }

   public String getName () {
      return name;
   }

   public void setName (String name) {
      this.name = name;
   }

   public String getEmail () {
      return email;
   }

   public void setEmail (String email) {
      this.email = email;
   }

   public String getDepartment () {
      return department;
   }

   public void setDepartment (String department) {
      this.department = department;
   }

   public BigDecimal getSalary () {
      return salary;
   }

   public void setSalary (BigDecimal salary) {
      this.salary = salary;
   }

   public LocalDate getHireDate () {
      return hireDate;
   }

   public void setHireDate (LocalDate hireDate) {
      this.hireDate = hireDate;
   }



   @Override
   public String toString () {
      return "Employee{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + ", department='" + department + '\'' + ", salary=" + salary + ", hireDate=" + hireDate + '}';
   }


}
