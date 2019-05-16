package com.employees.employees.domain.workshift;
import java.util.List;

public interface IWorkShiftService {
     List<WorkShift> getAllWorkShifts();
     WorkShift getWorkShiftById(Long id);
     boolean addWorkShift(WorkShift workshift);
     void updateWorkShift(WorkShift workshift);
     void deleteWorkShift(Long id);
     //List<WorkShift> getWorkShiftByFilter(String dias, String hora_inicio, String hora_fin);
     List<WorkShift> getWorkShiftByFilter(String dias, String hora_inicio);
}