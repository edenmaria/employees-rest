package com.employees.employees.domain.workshift;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.employees.employees.domain.employee.Employee;


@Entity
@Table(name="workshifts")
public class WorkShift implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Temporal(TemporalType.TIME)
    @Column(name="hora_inicio")
    private Date hora_inicio;

    @Temporal(TemporalType.TIME)
    @Column(name="hora_fin")
    private Date hora_fin;

    @Column(name="dias")
    private String dias;

    @ManyToMany(mappedBy = "workshifts")
    private List<Employee> employees; 

    public Long getId() {
		  return id;
    }
    
    public void setId(Long id) {
      this.id = id;
    }
      
    public Date getHoraInicio() {
      return hora_inicio;
    }
      
    public void setHoraInicio(Date hora_inicio) {
      this.hora_inicio = hora_inicio;
    }
      
     public Date getHoraFin() {
      return hora_fin;
    }
      
    public void setHoraFin(Date hora_fin) {
      this.hora_fin = hora_fin;
    }
      
    public String getDias() {
      return dias;
    }
      
    public void setDias(String dias) {
      this.dias = dias;
    }
  }