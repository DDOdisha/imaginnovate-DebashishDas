package com.imaginnovate.emp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.emp.dto.EmployeeTaxDetailsDTO;
import com.imaginnovate.emp.entities.Employee;
import com.imaginnovate.emp.repositories.EmployeeRepository;

@RestController
@RequestMapping("/tax")
public class TaxController {

    @Autowired
    private EmployeeRepository employeeRepository;

	/*
	 * @GetMapping("/emp/{employeeId}") public ResponseEntity<Double>
	 * calculateTax(@PathVariable Long employeeId) { // Determine start and end
	 * dates of the current financial year (April to March) LocalDate
	 * currentFinancialYearStart = LocalDate.now().withMonth(4).withDayOfMonth(1);
	 * LocalDate currentFinancialYearEnd =
	 * LocalDate.now().plusYears(1).withMonth(3).withDayOfMonth(31);
	 * 
	 * // Retrieve employee's income for the current financial year Double
	 * totalIncome =
	 * employeeRepository.findTotalSalaryForFinancialYearByEmployeeId(employeeId,
	 * currentFinancialYearStart, currentFinancialYearEnd);
	 * System.out.println("Total income "+totalIncome); // Calculate total income
	 * //double totalIncome =
	 * incomes.stream().mapToDouble(Double::doubleValue).sum();
	 * 
	 * // Calculate tax deduction based on total income (implement your tax
	 * calculation logic here) double taxDeduction =
	 * calculateTaxDeduction(totalIncome);
	 * 
	 * return ResponseEntity.ok(taxDeduction); }
	 */
    // Tax calculation method
    public double calculateTax(double yearlySalary) {
        if (yearlySalary <= 250000) {
            return 0; // No tax
        } else if (yearlySalary <= 500000) {
            return (yearlySalary - 250000) * 0.05; // 5% tax
        } else if (yearlySalary <= 1000000) {
            return 12500 + (yearlySalary - 500000) * 0.1; // 10% tax
        } else {
            return 12500 + 50000 + (yearlySalary - 1000000) * 0.2; // 20% tax
        }
    }
    // Tax calculation cess
    public double calculateAdditionalCess(double yearlySalary) {
        if (yearlySalary > 2500000) {
            return (yearlySalary - 2500000) * 0.02;
        } else {
            return 0;
        }
    }
    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<EmployeeTaxDetailsDTO> getTotalSalaryForFinancialYear(
            @PathVariable Long employeeId,
            @RequestParam("financialYear") @DateTimeFormat(pattern = "yyyy") int financialYear
    ) {
    	
        
          // Check if the employee exists
        if (!employeeRepository.existsById(employeeId)) {
            return ResponseEntity.notFound().build();
        }
        Double monthlySalary=employeeRepository.findSalaryByEmployeeId(employeeId);
        //find number of months the employee has worked in the financial year
        int months=employeeRepository.findMonthsWorkedForEmployeeInFinancialYear(employeeId, financialYear);
        // Calculate total salary for the financial year
        Double totalSalary = monthlySalary*months;
        Double taxAmount=calculateTax(totalSalary);
        Double cess=calculateAdditionalCess(totalSalary);
        System.out.println("Total salry in FY "+totalSalary + "TaxAmount "+taxAmount+" cess "+cess);
        EmployeeTaxDetailsDTO employeeTaxDto=new EmployeeTaxDetailsDTO();
        Optional<Employee> emp=employeeRepository.findById(employeeId);
        employeeTaxDto.setFirstName(emp.get().getFname());
        employeeTaxDto.setLastName(emp.get().getLname());
        employeeTaxDto.setYearlySalary(totalSalary);
        employeeTaxDto.setId(emp.get().getId());
        employeeTaxDto.setTaxAmount(taxAmount);
        employeeTaxDto.setCessAmount(cess);
        return ResponseEntity.ok(employeeTaxDto);
    }
}

