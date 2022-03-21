package com.thorelore.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.thorelore.pma.dao.EmployeeRepository;
import com.thorelore.pma.dao.ProjectRepository;

@Controller
public class HomeController {
	
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	
	@GetMapping({"","/home"})
	public String displayHome(Model model) {
		
		model.addAttribute("projects", proRepo.findAll());
		model.addAttribute("employees", empRepo.findAll());
		
		return "main/home";
	}
	
}
