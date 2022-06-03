package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> getEmployeeByName(@Param("name") String name);
	
	@Query("Select e from Employee e Where e.address IS NOT NULL and e.address.city=:name")
	List<Employee> getEmployeeByAddress(@Param("name") String name);
}
