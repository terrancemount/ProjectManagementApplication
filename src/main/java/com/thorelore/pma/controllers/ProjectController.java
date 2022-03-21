package com.thorelore.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thorelore.pma.dao.EmployeeRepository;
import com.thorelore.pma.dao.ProjectRepository;
import com.thorelore.pma.entities.Employee;
import com.thorelore.pma.entities.Project;



@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired  //spring framework will inject an instance of this variable.
	ProjectRepository proRepo;
	
	@Autowired 
	EmployeeRepository empRepo;
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		Project aProject = new Project();
		
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees", empRepo.findAll());
		
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProjectForm(Project project, @RequestParam List<Long> employees, Model model) {
		//this should handle saving to database...
		proRepo.save(project);
		
		for(Employee emp : empRepo.findAllById(employees)) {
			//emp.setProject(project);
			empRepo.save(emp);
		}
		
		// use a redirect to prevent duplicate submission
		return "redirect:/projects/new";
	}
	
	@GetMapping
	public String displayProjectList(Model model) {
		model.addAttribute("projects", proRepo.findAll());
		
		return "projects/list-projects"; 
	}
}
