package com.trustly.challenge.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_file")
public class GitFile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String extension;
	private Integer numberOfFiles;
	private Integer numberOfLines;
	private Integer numberOfBytes;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "projectId")
	private GitProject project;
	
	public GitFile() {
		
	}

	public GitFile(Long id, String extension, Integer numberOfFiles, Integer numberOfLines, Integer numberOfBytes, GitProject project) {
		super();
		this.id = id;
		this.extension = extension;
		this.numberOfFiles = numberOfFiles;
		this.numberOfLines = numberOfLines;
		this.numberOfBytes = numberOfBytes;
		this.project = project;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Integer getNumberOfFiles() {
		return numberOfFiles;
	}

	public void setNumberOfFiles(Integer numberOfFiles) {
		this.numberOfFiles = numberOfFiles;
	}

	public Integer getNumberOfLines() {
		return numberOfLines;
	}

	public void setNumberOfLines(Integer numberOfLines) {
		this.numberOfLines = numberOfLines;
	}

	public Integer getNumberOfBytes() {
		return numberOfBytes;
	}

	public void setNumberOfBytes(Integer numberOfBytes) {
		this.numberOfBytes = numberOfBytes;
	}
	
	public GitProject getProject() {
		return project;
	}

	public void setProject(GitProject project) {
		this.project = project;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GitFile other = (GitFile) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
