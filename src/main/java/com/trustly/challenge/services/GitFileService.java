package com.trustly.challenge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trustly.challenge.entities.GitFile;
import com.trustly.challenge.repositories.GitFileRepository;

@Service
public class GitFileService {

	@Autowired
	private GitFileRepository repository;
	
	public List<GitFile> findAll() {
		return repository.findAll();
	}
	
	public GitFile findById(Long id) {
		Optional<GitFile> obj = repository.findById(id);
		return obj.get();
	}
}
