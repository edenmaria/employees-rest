package com.employees.employees.domain.workshift;
import java.util.List;

public interface IWorkShiftService {
     List<WorkShift> getAllWorkShifts();
     WorkShift getWorkShiftById(Long id);
     boolean addWorkShift(WorkShift workshift);
     void updateWorkShift(WorkShift workshift);
     void deleteWorkShift(Long id);
}