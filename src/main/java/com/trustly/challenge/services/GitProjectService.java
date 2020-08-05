package com.trustly.challenge.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trustly.challenge.entities.GitProject;
import com.trustly.challenge.repositories.GitProjectRepository;

@Service
public class GitProjectService {

	@Autowired
	private GitProjectRepository repository;
		
	public List<GitProject> findAll() {
		return repository.findAll();
	}
	
	public GitProject findById(Long id) {
		Optional<GitProject> obj = repository.findById(id);
		return obj.get();
	}
	
	public GitProject insert(GitProject obj) throws IOException {
		return repository.save(obj);
	}
	
}
