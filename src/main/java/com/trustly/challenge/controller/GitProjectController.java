package com.trustly.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trustly.challenge.entities.GitProject;
import com.trustly.challenge.repositories.GitProjectRepository;

@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "https://cometti-frontend.herokuapp.com")
@RestController
@RequestMapping("api/")
public class GitProjectController {

	@Autowired
	private GitProjectRepository gitProjectRepository;
		
	@GetMapping("gitprojects")
	public List<GitProject> getGitProjects() {
		return this.gitProjectRepository.findAll();
	}		
}
