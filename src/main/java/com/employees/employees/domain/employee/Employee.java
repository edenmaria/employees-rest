package com.employees.employees.domain.employee;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;

import com.employees.employees.domain.workshift.WorkShift;


@Entity
@Table(name="employees")
public class Employee implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="id")
  private Long id;

  @Column(name="name")
  private String name;

  @Column(name="surname")
  private String surname;

  @Column(name="id_number",unique = true, length = 32)
  private String id_number;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "workshift_employee",
    joinColumns = @JoinColumn(name = "workshift_id", referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
  private List<WorkShift> workshifts;

  public List<WorkShift> getWorkShifts() {
    return workshifts;
  }

  public void setWorkShifts(List<WorkShift> workshifts) {
       this.workshifts = workshifts;
  }
  

  public Long getId() {
  return id;
  }
    
	public void setId(Long id) {
		this.id = id;
  }
    
	public String getName() {
		return name;
  }
    
	public void setName(String name) {
		this.name = name;
  }
    
  public String getSurname() {
		return surname;
  }
    
	public void setSurname(String surname) {
		this.surname = surname;
  }
    
  public String getId_number() {
		return id_number;
  }
    
	public void setId_number(String id_number) {
		this.id_number = id_number;
	}
}