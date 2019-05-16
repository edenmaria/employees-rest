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
import com.employees.employees.domain.workshift.IWorkShiftService;
import com.employees.employees.domain.employee.EmployeeWorkshift;

@Controller
@RequestMapping("api")
public class EmployeeController {

	
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IWorkShiftService workshiftService;
	
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


	// @PostMapping("employeeWorkshift")
	// public ResponseEntity<Employee> addEmployeeWorkshift(@JsonArg("/id_employee") Long id_employee, ("/id_workshift") Long id_workshift, UriComponentsBuilder builder) {
    //     Employee employee = employeeService.getEmployeeById(id_employee);
	// 	WorkShift workshift = workshiftService.getWorkShiftById(id_workshift);
	// 	List<WorkShift> list = new ArrayList<>();
	// 	list.add(workshift);
	// 	employee.setWorkShifts(list);
	// 	employeeService.addEmployeeWorkshift(employee);
	// 	return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	// }

	@PostMapping("employees-workshifts")
	public ResponseEntity<Employee> addEmployeeWorkshift(@RequestBody EmployeeWorkshift employeeworkshift, UriComponentsBuilder builder) {
        long id_employee = employeeworkshift.getIdEmployee();
		long id_workshift = employeeworkshift.getIdWorkshift();
		System.out.println("El error es:_" + id_workshift); 
		Employee employee = employeeService.getEmployeeById(id_employee);
		//WorkShift workshift = workshiftService.getWorkShiftById(x);
		//WorkShift workshift = workshiftService.getWorkShiftById(x);
		WorkShift workshift = new WorkShift();
		//workshift = workshiftService.getWorkShiftById(id_workshift);
		workshift.setId(id_workshift);
		List<WorkShift> list = new ArrayList<>();
		list.add(workshift);
		employee.setWorkShifts(list);
		employeeService.addEmployeeWorkshift(employee);
		return new ResponseEntity<Employee>(employee , HttpStatus.OK);
	}
} 
