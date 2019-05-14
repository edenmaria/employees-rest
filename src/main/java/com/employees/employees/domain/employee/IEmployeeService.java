package com.employees.employees.domain.employee;
import java.util.List;

public interface IEmployeeService {
     List<Employee> getAllEmployees();
     Employee getEmployeeById(Long id);
     boolean addEmployee(Employee employee);
     void updateEmployee(Employee employee);
     void deleteEmployee(Long id);
}
