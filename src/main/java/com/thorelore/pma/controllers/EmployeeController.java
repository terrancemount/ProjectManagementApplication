package com.thorelore.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thorelore.pma.dao.EmployeeRepository;
import com.thorelore.pma.entities.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired  //spring framework will inject an instance of this variable.
	EmployeeRepository empRepo;
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		
		model.addAttribute("employee", new Employee());
		
		return "employees/new-employee";
	}
	
	@PostMapping("/save")
	public String createEmployeeForm(Employee employee, Model model) {
		//this should handle saving to database...
		empRepo.save(employee);
		
		
		// use a redirect to prevent duplicate submission
		return "redirect:/employees/new";
	}
	
	@GetMapping
	public String displayEmployeeList(Model model) {
		model.addAttribute("employees", empRepo.findAll());
		
		return "employees/list-employees"; 
	}
}