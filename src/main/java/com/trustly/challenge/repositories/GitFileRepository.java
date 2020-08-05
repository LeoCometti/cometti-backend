package com.trustly.challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trustly.challenge.entities.GitFile;

public interface GitFileRepository extends JpaRepository<GitFile, Long> {

}
