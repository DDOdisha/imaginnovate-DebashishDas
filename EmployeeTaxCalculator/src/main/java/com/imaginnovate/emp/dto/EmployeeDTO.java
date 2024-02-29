package com.imaginnovate.emp.dto;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EmployeeDTO {
    private Long id;
    private String fName;
    private String lName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfJoin;
    private double salary;
    private Set<String> phoneNumbers;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFName() {
		return fName;
	}
	public void setFName(String firstName) {
		this.fName = firstName;
	}
	public String getLName() {
		return lName;
	}
	public void setLName(String lastName) {
		this.lName = lastName;
	}
	public Date getDateOfJoin() {
		return dateOfJoin;
	}
	public void setDateOfJoin(Date dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Set<String> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(Set<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

    // Getters and setters
}
