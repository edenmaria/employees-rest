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
	        // List<WorkShift> list =  workShiftDao.findByName(workshift.getName()); 	
            //     if (list.size() > 0) {
    	    //        return false;
            //     } else {
    	         workShiftDao.save(workshift);
    	        return true;
    //    }
	}
	@Override
	public void updateWorkShift(WorkShift workshift) {
		 workShiftDao.save(workshift);
	}
	@Override
	public void deleteWorkShift(Long id) {
		 workShiftDao.delete(getWorkShiftById(id));
	}
} 