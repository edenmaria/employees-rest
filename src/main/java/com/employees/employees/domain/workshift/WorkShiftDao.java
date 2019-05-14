package com.employees.employees.domain.workshift;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.employees.employees.domain.workshift.WorkShift;

public interface WorkShiftDao extends CrudRepository<WorkShift, List> {
    List<WorkShift> findById(Long id);
} 