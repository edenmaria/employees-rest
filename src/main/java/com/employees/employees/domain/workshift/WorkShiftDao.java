package com.employees.employees.domain.workshift;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import com.employees.employees.domain.workshift.WorkShift;

public interface WorkShiftDao extends CrudRepository<WorkShift, List> {
    List<WorkShift> findById(Long id);
   
    @Query(value = "SELECT * FROM workshifts "+
				   "WHERE dias LIKE CONCAT('%',:dias,'%') "+ 
				   "AND (hora_inicio LIKE CONCAT('%',cast(:hora_inicio as time),'%') OR length(hora_inicio) != 0) "+ 
				   "AND (hora_fin LIKE CONCAT('%',cast(:hora_fin as time),'%') OR length(hora_fin) != 0)", nativeQuery = true)
	public ArrayList<WorkShift> findWorkShift(@Param("dias") String dias,@Param("hora_inicio") String hora_inicio,@Param("hora_fin") String hora_fin);
    
} 