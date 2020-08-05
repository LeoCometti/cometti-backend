package com.trustly.challenge.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trustly.challenge.entities.GitFile;
import com.trustly.challenge.services.GitFileService;

@RestController
@RequestMapping(value = "/gitfiles")
public class GitFileResource {

	@Autowired
	private GitFileService service;
	
	@GetMapping
	public ResponseEntity<List<GitFile>> findAll() {
		List<GitFile> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<GitFile> findById(@PathVariable Long id) {
		GitFile obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
