package com.imaginnovate.emp.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imaginnovate.emp.dto.EmployeeDTO;
import com.imaginnovate.emp.entities.Employee;
import com.imaginnovate.emp.entities.PhoneNumber;
import com.imaginnovate.emp.repositories.EmployeeRepository;
import com.imaginnovate.emp.services.EmployeeService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/emp")
public class EmployeeController {
	@Autowired
    private EmployeeService employeeService;
	@Autowired
    private EmployeeRepository employeeRepository;
   
	@GetMapping("/allEmp")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployee()
	{
        List<Employee> employees = employeeRepository.findAll();
        
        // Convert entities to DTOs
        List<EmployeeDTO> employeeDTOs = employees.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(employeeDTOs, HttpStatus.OK);
	}
	
	    @PostMapping("/addEmpWithPhone")
	    public ResponseEntity<?> saveEmployeeWithPhoneNumbers(@Valid @RequestBody Map<String, Object> request,BindingResult result) {
	    	//Validation error to be returned 
	    	if (result.hasErrors()) {
	            List<String> errors = result.getAllErrors().stream()
	                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
	                    .collect(Collectors.toList());
	            return ResponseEntity.badRequest().body(errors);
	        }
	    	// Extract employee details from the request body
	        ObjectMapper mapper = new ObjectMapper();
	        Employee employee = mapper.convertValue(request.get("employee"), Employee.class);
	        
	     // Extract phone numbers from the request body as a Set
	        Set<String> phoneNumbersSet = new HashSet<>((List<String>) request.get("phoneNumbers"));

	        // Convert Set of phone numbers to a Set of PhoneNumber objects
	        Set<PhoneNumber> phoneNumbers = new HashSet<>();
	        for (String number : phoneNumbersSet) {
	            PhoneNumber phoneNumber = new PhoneNumber();
	            phoneNumber.setNumber(number);
	            phoneNumbers.add(phoneNumber);
	        }

	        // Set phone numbers to the employee object
	         employee.setPhoneNumbers(phoneNumbers);
	       // employee.getPhoneNumbers().addAll(phoneNumbers);

	        // Save employee with phone numbers
	        Employee savedEmployee = employeeService.saveEmployeeWithPhoneNumbers(employee,new ArrayList<>(phoneNumbers));
	        return new ResponseEntity<>(this.convertToDto(savedEmployee), HttpStatus.CREATED);
	    }
	    private EmployeeDTO convertToDto(Employee employee) {
	        ModelMapper modelMapper = new ModelMapper();
	        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
	        employeeDTO.setPhoneNumbers(employee.getPhoneNumbers().stream()
	                .map(PhoneNumber::getNumber)
	                .collect(Collectors.toSet()));
	        return employeeDTO;
	    }
}