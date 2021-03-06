package com.employees.employees.controllers;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;
import com.employees.employees.domain.workshift.WorkShift;
import com.employees.employees.domain.workshift.IWorkShiftService;


@Controller
@RequestMapping("api")
public class WorkShiftController {
	
	@Autowired
	private IWorkShiftService workshiftService;
	
	@GetMapping("workshifts/{id}")
	public ResponseEntity<WorkShift> getWorkShiftById(@PathVariable("id") Long id) {
		WorkShift workshift = workshiftService.getWorkShiftById(id);
		return new ResponseEntity<WorkShift>(workshift, HttpStatus.OK);
	}

	@GetMapping("workshifts")
	public ResponseEntity<List<WorkShift>> getAllWorkShifts() {
		List<WorkShift> list = workshiftService.getAllWorkShifts();
		return new ResponseEntity<List<WorkShift>>(list, HttpStatus.OK);
	}

	@PostMapping("workshifts")
	public ResponseEntity<Void> addWorkShift(@RequestBody WorkShift workshift, UriComponentsBuilder builder) {
                boolean flag = workshiftService.addWorkShift(workshift);
                if (flag == false) {
        	        return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/workshift/{id}").buildAndExpand(workshift.getId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("workshifts")
	public ResponseEntity<WorkShift> updateWorkShift(@RequestBody WorkShift workshift) {
		workshiftService.updateWorkShift(workshift);
		return new ResponseEntity<WorkShift>(workshift, HttpStatus.OK);
	}

	@DeleteMapping("workshifts/{id}")
	public ResponseEntity<Void> deleteWorkShift(@PathVariable("id") Long id) {
		workshiftService.deleteWorkShift(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	

	@GetMapping("workshifts-filters/")
	public ResponseEntity<List<WorkShift>> getWorkShiftByFilter(@RequestParam String dias,String hora_inicio,String hora_fin) {
		List<WorkShift> list = workshiftService.getWorkShiftByFilter(dias,hora_inicio,hora_fin);
		return new ResponseEntity<List<WorkShift>>(list, HttpStatus.OK);
	}
} 
