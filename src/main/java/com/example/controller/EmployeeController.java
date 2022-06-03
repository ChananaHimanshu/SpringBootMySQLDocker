package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Employee;
import com.example.service.EmployeeService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
		System.out.println(">>>>>>>>>>>>>>>>>>"+ SpringVersion.getVersion());
		System.out.println(">>>>>>>>>>>>>>>>>>"+ SpringBootVersion.getVersion());
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Employee> getEmployee() {
		return employeeService.getEmployee();
	}

	@RequestMapping(value = "/paginated", method = RequestMethod.GET)
	public List<Employee> getEmployeePaginated() {
		return employeeService.getEmployeePaginated();
	}
	
	@RequestMapping(value = "/paginated/param", method = RequestMethod.GET)
	public List<Employee> getEmployeePaginatedParam(
			@RequestParam(value = "page", required = false) Integer page, 
			@RequestParam(value = "offset", required = false) Integer offset, 
			@RequestParam(value = "id", required = false) Long id, 
			@RequestParam(value = "name", required = false) String name) {
		return employeeService.getEmployeePaginatedParam(page, offset, id, name);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Employee getEmployee(@PathVariable Long id) throws NotFoundException {
		return employeeService.getEmployee(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Employee saveEmployee(@RequestBody Employee employee) throws NotFoundException {
		return employeeService.saveEmployee(employee);
	}

	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public List<Employee> getEmployeeByName(@PathVariable String name) {
		return employeeService.getEmployeeByName(name);
	}
}
