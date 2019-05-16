package com.employees.employees.domain.employee;

import java.util.List;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import com.employees.employees.domain.employee.Employee;

public interface EmployeeDao extends CrudRepository<Employee, List> {
    List<Employee> findById(Long id);

    @Query(value = "SELECT * FROM employees where id_number=:id_number", nativeQuery = true)
		public ArrayList<Employee> findByIdNumber(@Param("id_number") String id_number);    
} 
