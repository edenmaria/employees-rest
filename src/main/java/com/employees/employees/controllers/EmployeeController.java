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

 public int flag= 0;
	
	@Autowired
	private IEmployeeService employeeService;
	
	@GetMapping("employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> list = employeeService.getAllEmployees();
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}	

	@GetMapping("employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
		Employee employee = employeeService.getEmployeeById(id);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
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

	@GetMapping("employees-workshifts/{id_number}")
	public ResponseEntity<Employee> getEmployeeByIdNumber(@PathVariable("id_number") String id_number) {
		Employee employee = employeeService.getEmployeeByIdNumber(id_number);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@PostMapping("employees-workshifts")
	public ResponseEntity<String> addEmployeeWorkshift(@RequestBody EmployeeWorkshift employeeworkshift, UriComponentsBuilder builder) {
		try {
			long id_employee = employeeworkshift.getIdEmployee();
			long id_workshift = employeeworkshift.getIdWorkshift();
			Employee employee = employeeService.getEmployeeById(id_employee);
			WorkShift workshift = workShiftDao.findById(id_workshift).get(0);
			List<WorkShift> list = new ArrayList<>();
			flag=0;
			list=employee.getWorkShifts();
			list.forEach(e -> {
       							if(e.getId() == workshift.getId()) {
									 flag=1;
        						}
    						});
			if(flag==0){
				list.add(workshift);
				employee.setWorkShifts(list);
				employeeService.addEmployeeWorkshift(employee);
				return new ResponseEntity<String>("Exitoso" , HttpStatus.CREATED);
			}
			else{
				return new ResponseEntity<String>("El horario ya esta asignado" , HttpStatus.OK);
			}
		}
		catch(RuntimeException e){
			return new ResponseEntity<String>("Ocurrio un error, verifique los datos ingresados", HttpStatus.BAD_REQUEST);
		}	
	}

	@DeleteMapping("employees-workshifts/{id_employee}/{id_workshift}")
	public ResponseEntity<String> deleteEmployeeWorkshift(@PathVariable("id_employee") Long id_employee, @PathVariable("id_workshift") Long id_workshift) {
		try{
			Employee employee = employeeService.getEmployeeById(id_employee);
			WorkShift workshift = workShiftDao.findById(id_workshift).get(0);
			List<WorkShift> list = new ArrayList<>();
			flag=0;
				list=employee.getWorkShifts();
				list.forEach(e -> {
									if(e.getId() == workshift.getId()) {
										System.out.println("El error es:_" + workshift.getId());
										flag=1;
									}
								});
				if(flag==1){
					list.remove(workshift);
					employee.setWorkShifts(list);
					employeeService.addEmployeeWorkshift(employee);
					return new ResponseEntity<String>("Exitoso" , HttpStatus.OK);
				}
				else{
					return new ResponseEntity<String>("El horario no esta asignado" , HttpStatus.OK);
				}
		}
		catch(RuntimeException e){
			return new ResponseEntity<String>("Ocurrio un error, verifique los datos ingresados", HttpStatus.BAD_REQUEST);
		}	
	}	


	@Autowired
	WorkShiftDao workShiftDao;
} 
