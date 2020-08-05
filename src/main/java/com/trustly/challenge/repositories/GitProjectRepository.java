package com.trustly.challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trustly.challenge.entities.GitProject;

public interface GitProjectRepository extends JpaRepository<GitProject, Long> {

}
