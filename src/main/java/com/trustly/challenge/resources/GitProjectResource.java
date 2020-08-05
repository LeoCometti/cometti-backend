package com.trustly.challenge.resources;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.trustly.challenge.RetrieveGitContent;
import com.trustly.challenge.entities.GitFile;
import com.trustly.challenge.entities.GitProject;
import com.trustly.challenge.repositories.GitFileRepository;
import com.trustly.challenge.services.GitProjectService;

@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "https://cometti-frontend.herokuapp.com")
@RestController
@RequestMapping(value = "/gitprojects")
public class GitProjectResource {

	@Value("${spring.token}")
	private String appToken;
	
	@Autowired
	private GitProjectService service;
	
	@Autowired
	private GitFileRepository gitFileRepository;
	
	@GetMapping
	public ResponseEntity<List<GitProject>> findAll() {
		List<GitProject> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<GitProject> findById(@PathVariable Long id) {
		GitProject obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<GitProject> insert(@RequestBody GitProject obj) throws IOException {
		System.out.println(obj);
		
		obj = service.insert(obj);
		
		RetrieveGitContent retrieveContent = new RetrieveGitContent();
		List<GitFile> contentList = retrieveContent.getAllContent(obj, appToken);
		gitFileRepository.saveAll(contentList);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
}
