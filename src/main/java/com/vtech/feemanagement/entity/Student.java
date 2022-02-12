package com.vtech.feemanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="student_details")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name = "sname")
	private String name;
	
	@Column(name = "usn")
	private String usn;
	
	@Column(name = "department")
	private String department;
	
	@Column(name = "sem")
    private String sem;
	
	@Column(name = "collegefee")
	private long collegefee;
	
	@Column(name = "busfee")
	private long busfee;
	
	@Column(name = "activityfee")
	private long activityfee;
	
	@Column(name = "esdpfee")
	private long esdpfee;
	
	@Column(name = "alumnifee")
	private long alumnifee;
	
	public Student() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsn() {
		return usn;
	}

	public void setUsn(String usn) {
		this.usn = usn;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSem() {
		return sem;
	}

	public void setSem(String sem) {
		this.sem = sem;
	}

	public long getCollegefee() {
		return collegefee;
	}

	public void setCollegefee(long collegefee) {
		this.collegefee = collegefee;
	}

	public long getBusfee() {
		return busfee;
	}

	public void setBusfee(long busfee) {
		this.busfee = busfee;
	}

	public long getActivityfee() {
		return activityfee;
	}

	public void setActivityfee(long activityfee) {
		this.activityfee = activityfee;
	}

	public long getEsdpfee() {
		return esdpfee;
	}

	public void setEsdpfee(long esdpfee) {
		this.esdpfee = esdpfee;
	}

	public long getAlumnifee() {
		return alumnifee;
	}

	public void setAlumnifee(long alumnifee) {
		this.alumnifee = alumnifee;
	}
	
	
}

