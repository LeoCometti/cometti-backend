package com.trustly.challenge.entities;

public class FileProperties {

	private String name;
	private String url;
	private String type;
	private Integer numberOfFiles;
	private Integer numberOfLines;
	private Integer numberOfBytes;
	
	public FileProperties(String name, Integer numberOfFiles, Integer numberOfLines, Integer numberOfBytes) {
		super();
		this.name = name;
		this.numberOfFiles = numberOfFiles;
		this.numberOfLines = numberOfLines;
		this.numberOfBytes = numberOfBytes;
	}
	
	public FileProperties(String name, String url, String type, Integer numberOfFiles, Integer numberOfLines, Integer numberOfBytes) {
		super();
		this.name = name;
		this.url = url;
		this.type = type;
		this.numberOfFiles = numberOfFiles;
		this.numberOfLines = numberOfLines;
		this.numberOfBytes = numberOfBytes;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public String getType() {
		return type;
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

	
}
