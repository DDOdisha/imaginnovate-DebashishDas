package com.imaginnovate.emp.dto;

import java.util.List;

import com.imaginnovate.emp.entities.Employee;
import com.imaginnovate.emp.entities.PhoneNumber;
/*
 * In this new class EmployeeRequest for easy deserialization
 */
public class EmployeeRequest {
	private Employee employee;
    private List<PhoneNumber> phoneNumbers;
    
	public EmployeeRequest() {
		super();
		
	}

	public EmployeeRequest(Employee employee, List<PhoneNumber> phoneNumbers) {
		super();
		this.employee = employee;
		this.phoneNumbers = phoneNumbers;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	
    
}
