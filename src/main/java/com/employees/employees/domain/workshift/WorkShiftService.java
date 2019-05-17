package com.employees.employees.domain.workshift;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.employees.employees.domain.workshift.WorkShiftDao;


@Service
public class WorkShiftService implements IWorkShiftService {
	
	@Autowired
	private WorkShiftDao workShiftDao;
	@Override
	public WorkShift getWorkShiftById(Long id) {
		WorkShift obj = workShiftDao.findById(id).get(0);
		return obj;
	}	
	@Override
	public List<WorkShift> getAllWorkShifts(){
		List<WorkShift> list = new ArrayList<>();
		 workShiftDao.findAll().forEach(e -> list.add(e));
		return list;
	}
	@Override
	public synchronized boolean addWorkShift(WorkShift  workshift){
		workShiftDao.save(workshift);
		return true;

	}
	@Override
	public void updateWorkShift(WorkShift workshift) {
		 workShiftDao.save(workshift);
	}
	@Override
	public void deleteWorkShift(Long id) {
		 workShiftDao.delete(getWorkShiftById(id));
	}

	@Override
	public List<WorkShift> getWorkShiftByFilter(String dias,String hora_inicio,String hora_fin){
		List<WorkShift> list = new ArrayList<>();
		workShiftDao.findWorkShift(dias,hora_inicio,hora_fin).forEach(e -> list.add(e));
		return list;
	}
} 