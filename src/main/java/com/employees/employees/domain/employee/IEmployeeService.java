package com.employees.employees.domain.employee;
import java.util.List;
import com.employees.employees.domain.workshift.WorkShift;

public interface IEmployeeService {
     List<Employee> getAllEmployees();
     Employee getEmployeeById(Long id);
     boolean addEmployee(Employee employee);
     void updateEmployee(Employee employee);
     void deleteEmployee(Long id);
     boolean addEmployeeWorkshift(Employee employee);
}
