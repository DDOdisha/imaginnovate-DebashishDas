package com.imaginnovate.emp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.imaginnovate.emp.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	

	 @Query("SELECT " +
	           "    CASE " +
	           "        WHEN EXTRACT(YEAR FROM e.dateOfJoin) = :financialYear THEN " +
	           "            CASE " +
	           "                WHEN EXTRACT(MONTH FROM e.dateOfJoin) < 4 THEN " +
	           "                    EXTRACT(MONTH FROM e.dateOfJoin) " +
	           "                ELSE " +
	           "                    12 - (EXTRACT(MONTH FROM e.dateOfJoin) - 3) " +
	           "            END " +
	           "        WHEN EXTRACT(YEAR FROM e.dateOfJoin) = :financialYear - 1 THEN " +
	           "            3 + EXTRACT(MONTH FROM e.dateOfJoin) " +
	           "        ELSE " +
	           "            CASE " +
	           "                WHEN EXTRACT(YEAR FROM e.dateOfJoin) < :financialYear THEN " +
	           "                    12 " +
	           "                ELSE " +
	           "                    0 " +
	           "            END " +
	           "    END AS monthsWorked " +
	           "FROM Employee e " +
	           "WHERE e.id = :employeeId")
	    Integer findMonthsWorkedForEmployeeInFinancialYear(
	            @Param("employeeId") Long employeeId,
	            @Param("financialYear") int financialYear
	    );
	 @Query("SELECT e.salary FROM Employee e WHERE e.id = :employeeId")
	    Double findSalaryByEmployeeId(@Param("employeeId") Long employeeId);

}


