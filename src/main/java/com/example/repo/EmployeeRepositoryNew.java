package com.example.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Employee;

@Repository
public interface EmployeeRepositoryNew extends PagingAndSortingRepository<Employee, Long> {

	List<Employee> findByName(String name);

	Page<Employee> findById(Long id, Pageable pageable);

	Page<Employee> findByName(String name, Pageable pageable);
	
	Page<Employee> findByNameAndId(String name, Long id, Pageable pageable);
}
