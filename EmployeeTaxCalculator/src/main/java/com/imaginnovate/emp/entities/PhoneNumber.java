package com.imaginnovate.emp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/*
 * This class is the Entity to represent Employee's Phone Numbers 
 */
@Entity
public class PhoneNumber extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	private String number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    // Constructors, getters, and setters

    public PhoneNumber() {
    }

    public PhoneNumber(String number) {
        this.number = number;
    }

    // Getter and setter methods

   
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
