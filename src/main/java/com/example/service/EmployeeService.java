package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.exception.NotFoundException;
import com.example.repo.EmployeeRepository;
import com.example.repo.EmployeeRepositoryNew;

@Service
public class EmployeeService {

	private EmployeeRepository employeeRepository;
	private EmployeeRepositoryNew employeeRepositoryNew;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository, EmployeeRepositoryNew employeeRepositoryNew) {
		this.employeeRepository = employeeRepository;
		this.employeeRepositoryNew = employeeRepositoryNew;
	}

	public List<Employee> getEmployee() {
		return this.employeeRepository.findAll();
	}

	public List<Employee> getEmployeePaginated() {
		System.out.println("---------------------------------------HERE----------------------------");
		List<Employee> list = new ArrayList<Employee>();

		int count = 1;
		Pageable sortedByName = PageRequest.of(0, 2, Sort.by("id", "name").ascending());
		// Page<Employee> emp = employeeRepositoryNew.findByName("Him", sortedByName);
		Page<Employee> emp = employeeRepositoryNew.findByNameAndId("Him", 4L, sortedByName);
		list.addAll(emp.getContent());
		System.out.println(count);
		while (emp.hasNext()) {
			emp = employeeRepositoryNew.findByName("Him", sortedByName.next());
			list.addAll(emp.getContent());
			count += 1;
			System.out.println(count);
		}
		// Page<Employee> emp = this.employeeRepositoryNew.findAll(sortedByName);
		System.out.println("----------------------RETURNING-------------------");
		return list;
	}

	public List<Employee> getEmployeePaginatedParam(Integer page, Integer offset, Long id, String name) {
		System.out.println("---------------------------------------HERE----------------------------");
		System.out.println(page);
		System.out.println(offset);
		System.out.println(id);
		System.out.println(name);
		List<Employee> list = new ArrayList<Employee>();

		int count = 1;
		Page<Employee> emp = null;
		if (Objects.isNull(page)) {
			Pageable pages = PageRequest.of(0, Integer.MAX_VALUE);
			if (!Objects.isNull(id) && (!Objects.isNull(name) && !name.isEmpty())) {
				emp = employeeRepositoryNew.findByNameAndId(name, id, pages);
			} else if (Objects.isNull(id) && !Objects.isNull(name)) {
				emp = employeeRepositoryNew.findByName(name, pages);
			} else if (!Objects.isNull(id)) {
				emp = employeeRepositoryNew.findById(id, pages);
			} else {
				pages = Pageable.unpaged();
				emp = this.employeeRepositoryNew.findAll(pages);
			}
		} else {
			Pageable pages = null;
			if (Objects.isNull(offset)) {
				pages = PageRequest.of(page, Integer.MAX_VALUE, Sort.unsorted());
			} else {
				pages = PageRequest.of(page, offset, Sort.unsorted());
			}
			if (!Objects.isNull(id) && (!Objects.isNull(name) && !name.isEmpty())) {
				emp = employeeRepositoryNew.findByNameAndId(name, id, pages);
			} else if (Objects.isNull(id) && !Objects.isNull(name)) {
				emp = employeeRepositoryNew.findByName(name, pages);
			} else if (!Objects.isNull(id)) {
				emp = employeeRepositoryNew.findById(id, pages);
			} else {
				emp = this.employeeRepositoryNew.findAll(pages);
			}
		}
		list.addAll(emp.getContent());

		System.out.println(count);
//		while (emp.hasNext()) {
//			emp = employeeRepositoryNew.findByName(name, pages.next());
//			// list.addAll(emp.getContent());
//			// count += 1;
//			System.out.println(count);
//		}
		System.out.println("----------------------RETURNING-------------------");
		return list;
	}

	public Employee getEmployee(Long id) {
		System.out.println(id);
		Optional<Employee> emp = this.employeeRepository.findById(id);
		if (Objects.isNull(emp) || !emp.isPresent()) {
			throw new NotFoundException("Employee not found");
		}
		return emp.get();
	}

	public Employee saveEmployee(Employee employee) throws NotFoundException {
		if (employee.getId() != null) {
			Employee employeeDb = getEmployee(employee.getId());
			employeeDb.setName(employee.getName());
			employeeDb.setAddress(employee.getAddress());
			employeeDb = employeeRepository.save(employeeDb);
			// employeeRepository.saveAndFlush(employeeDb);
			// employeeDb = employeeRepository.saveAndFlush(employeeDb);
			return employeeDb;
		}
		employee = employeeRepository.save(employee);
		return employee;
	}

	public List<Employee> getEmployeeByName(String name) {
		// return employeeRepository.getEmployeeByName(name);
		return employeeRepository.getEmployeeByAddress(name);
	}
}
