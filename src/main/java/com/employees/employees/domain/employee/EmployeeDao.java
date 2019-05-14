package com.employees.employees.domain.employee;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.employees.employees.domain.employee.Employee;

public interface EmployeeDao extends CrudRepository<Employee, List> {
    List<Employee> findByName(String name);
    List<Employee> findById(Long id);
} 
