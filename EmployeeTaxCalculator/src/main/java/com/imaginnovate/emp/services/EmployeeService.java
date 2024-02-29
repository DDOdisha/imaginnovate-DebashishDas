package com.imaginnovate.emp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imaginnovate.emp.entities.Employee;
import com.imaginnovate.emp.entities.PhoneNumber;
import com.imaginnovate.emp.repositories.EmployeeRepository;
import com.imaginnovate.emp.repositories.PhoneNumberRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PhoneNumberRepository phoneNumberRepository;
    
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
    public Employee saveEmployeeWithPhoneNumbers(Employee employee, List<PhoneNumber> phoneNumbers) {
        // Save employee
        Employee savedEmployee = employeeRepository.save(employee);

        // Associate phone numbers with the saved employee
        for (PhoneNumber phoneNumber : phoneNumbers) {
            phoneNumber.setEmployee(savedEmployee);
            phoneNumberRepository.save(phoneNumber);
        }

        return savedEmployee;
    }
    
}
