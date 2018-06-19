package com.acc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	@Id 
	@Column(name = "emp_id",unique=true)
	private Integer empid;

	@Column(name = "employee_name", nullable = false)
	private String name;

	@Column(name = "employee_designation", nullable = false)
	private String designation;

	@Column(name = "technology", nullable = false)
	private String technology;

	@Column(name = "delivery_group", nullable = false)
	private String deliveryGroup;

	@Column(name = "location", nullable = false)
	private String location;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getEmpid() {
		return empid;
	}

	public void setEmpid(Integer empid) {
		this.empid = empid;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getDeliveryGroup() {
		return deliveryGroup;
	}

	public void setDeliveryGroup(String deliveryGroup) {
		this.deliveryGroup = deliveryGroup;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {

		return empid + "\t" + name + "\t" + location + "\t" + technology + "\t" + deliveryGroup + "\t" + designation
				+ "";
	}
}
