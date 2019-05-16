package com.employees.employees.domain.employee;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.employees.employees.domain.employee.EmployeeDao;

@Service
public class EmployeeService implements IEmployeeService {
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Employee getEmployeeById(Long id) {
		Employee obj = employeeDao.findById(id).get(0);
		return obj;
	}	
	@Override
	public List<Employee> getAllEmployees(){
		List<Employee> list = new ArrayList<>();
		employeeDao.findAll().forEach(e -> list.add(e));
		return list;
	}
	@Override
	public synchronized boolean addEmployee(Employee employee){
	    employeeDao.save(employee);
    	return true;
    }
	@Override
	public void updateEmployee(Employee employee) {
		employeeDao.save(employee);
	}
	@Override
	public void deleteEmployee(Long id) {
		employeeDao.delete(getEmployeeById(id));
	}

	@Override
	public Employee getEmployeeByIdNumber(String id_number) {
		Employee obj = employeeDao.findByIdNumber(id_number).get(0);
		return obj;
	}	

	@Override
	public synchronized boolean addEmployeeWorkshift(Employee employee){
	    employeeDao.save(employee);
    	return true;
    }

} 