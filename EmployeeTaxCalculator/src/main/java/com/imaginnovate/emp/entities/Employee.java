package com.imaginnovate.emp.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
/**
 * This class is the Entity to represent Employee
 */
@Entity 
public class Employee extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "First name is required")
	String fname;
	@NotBlank(message = "Last name is required")
	String lname;
	@NotBlank(message = "Email is required")
	String email;
	@Temporal(TemporalType.DATE)
	@NotNull(message = "Date of join is required")
    @Past(message = "Date of join must be in the past")
	private Date dateOfJoin;
	@NotNull(message = "Salary is required")
	private double salary;
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PhoneNumber> phoneNumbers = new HashSet<>();
	public Employee() {
		super();
		}
	public Employee(String fname, String lname, String email, Date dateOfJoin, double salary,
			Set<PhoneNumber> phoneNumbers) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.dateOfJoin = dateOfJoin;
		this.salary = salary;
		this.phoneNumbers = phoneNumbers;
	}
	public Employee(String fname, String lname) {
		this.fname=fname;
		this.lname=lname;
	}
// Getter and setter methods
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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

	
 
	
	public void setLname(String lname) {
		this.lname = lname;
	}
	public Set<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        phoneNumbers.add(phoneNumber);
        phoneNumber.setEmployee(this);
    }

    public void removePhoneNumber(PhoneNumber phoneNumber) {
        phoneNumbers.remove(phoneNumber);
        phoneNumber.setEmployee(null);
    }
}
