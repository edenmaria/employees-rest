package com.employees.employees.controllers;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;
import com.employees.employees.domain.employee.Employee;
import com.employees.employees.domain.workshift.WorkShift;
import com.employees.employees.domain.employee.IEmployeeService;
import com.employees.employees.domain.employee.EmployeeWorkshift;
import com.employees.employees.domain.workshift.WorkShiftDao;

@Controller
@RequestMapping("api")
public class EmployeeController {

	
	@Autowired
	private IEmployeeService employeeService;
	
	
	@GetMapping("employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
		Employee employee = employeeService.getEmployeeById(id);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@GetMapping("employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> list = employeeService.getAllEmployees();
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}

	@PostMapping("employees")
	public ResponseEntity<Void> addEmployee(@RequestBody Employee employee, UriComponentsBuilder builder) {
                boolean flag = employeeService.addEmployee(employee);
                if (flag == false) {
        	        return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/employee/{id}").buildAndExpand(employee.getId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("employees")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		employeeService.updateEmployee(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@DeleteMapping("employees/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	

	@PostMapping("employees-workshifts")
	public ResponseEntity<Employee> addEmployeeWorkshift(@RequestBody EmployeeWorkshift employeeworkshift, UriComponentsBuilder builder) {
        long id_employee = employeeworkshift.getIdEmployee();
		long id_workshift = employeeworkshift.getIdWorkshift();
		Employee employee = employeeService.getEmployeeById(id_employee);
		WorkShift workshift = workShiftDao.findById(id_workshift).get(0);
		List<WorkShift> list = new ArrayList<>();
		list=employee.getWorkShifts();
		list.add(workshift);
		employee.setWorkShifts(list);
		employeeService.addEmployeeWorkshift(employee);
		return new ResponseEntity<Employee>(employee , HttpStatus.OK);
	}
	@Autowired
	WorkShiftDao workShiftDao;
} 
