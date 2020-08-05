package com.trustly.challenge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRateLimit;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import com.trustly.challenge.entities.FileProperties;
import com.trustly.challenge.entities.GitFile;
import com.trustly.challenge.entities.GitProject;
import com.trustly.challenge.properties.AppProperties;

public class RetrieveGitContent {

	GitHub github = null;
	String repoOnwer = null;
	String repository = null;	
	private String appToken;
	
	@Autowired
	AppProperties appProperties;
	
	public List<GitFile> getAllContentTeste(String url) throws IOException {
		List<GitFile> gitFile = new ArrayList<>();
		
		GHRepository repo = getGitRepository(url);
		List<GHContent> contentList = repo.getDirectoryContent(".");
		List<FileProperties> allFiles = getAllFiles(contentList);
		List<FileProperties> singFiles = getSingFiles(allFiles);
		gitFile = getGitFile(singFiles, null);
				
		return gitFile;
	}
	
	public List<GitFile> getAllContent(GitProject obj, String appToken) throws IOException {
		List<GitFile> gitFile = new ArrayList<>();
		this.appToken = appToken;
		
		GHRepository repo = getGitRepository(obj.getUrl());
		List<GHContent> contentList = repo.getDirectoryContent(".");
		List<FileProperties> allFiles = getAllFiles(contentList);
		List<FileProperties> singFiles = getSingFiles(allFiles);
		gitFile = getGitFile(singFiles, obj);
		
		return gitFile;
	}

	public GHRepository getGitRepository(String url) throws IOException {
		github = new GitHubBuilder().withOAuthToken(appToken).build();
		GHRateLimit index =  github.getRateLimit();
		System.out.println(index);
		
		getCorrectUrl(url);
		String fullName = repoOnwer + "/" + repository;
		return github.getRepository(fullName);
	}
	
	public List<FileProperties> getAllFiles(List<GHContent> contentList) throws IOException {
		List<FileProperties> allFiles = new ArrayList<>();
		
		if (contentList != null) {
			for (GHContent content : contentList) {
				if (content.isFile())
				{
					String name = content.getName();
					Long size = content.getSize();
					String fileContent = content.getContent();
					
					String[] bits = name.split("\\.(?=[^\\.]+$)");
					String variable = "";
					variable = (bits.length > 1) ? bits[1] : bits[0];
					String extension = "." + variable;
					Integer numberOfLines = (int) fileContent.lines().count();;
					Integer numberOfBytes = Integer.parseInt(size.toString());

					allFiles.add(new FileProperties(extension, 1, numberOfLines, numberOfBytes));
				}
				else
				{
					List<GHContent> dirContent = content.listDirectoryContent().asList();
					allFiles.addAll(getAllFiles(dirContent));
				}
			}
		}
		
		return allFiles;
	}
	
	private List<FileProperties> getSingFiles(List<FileProperties> allFiles) {
		List<FileProperties> singleFiles = new ArrayList<>();
		
		for (FileProperties file : allFiles) {
			int index = getIndex(singleFiles, file.getName());
			if (index > -1) {
				int numberOfFiles = singleFiles.get(index).getNumberOfFiles() + 1;
				singleFiles.get(index).setNumberOfFiles(numberOfFiles);
				int numberOfLines = singleFiles.get(index).getNumberOfLines() + file.getNumberOfLines();
				singleFiles.get(index).setNumberOfLines(numberOfLines);
				int numberOfBytes = singleFiles.get(index).getNumberOfBytes() + file.getNumberOfBytes();
				singleFiles.get(index).setNumberOfBytes(numberOfBytes);
			} else {
				singleFiles.add(file);
			}
		}
		
		return singleFiles;
	}
	
	public Integer getIndex(List<FileProperties> fileList, String name) {
		int index = -1;
		int count = 0;
		for (FileProperties file : fileList) {
			if (file.getName().equals(name)) {
				index = count;
				break;
			}
			count++;
		}
		return index;
	}
	
	public List<GitFile> getGitFile(List<FileProperties> allFiles, GitProject obj) {
		List<GitFile> gitFile = new ArrayList<>();
		for (FileProperties file : allFiles) {
			gitFile.add(new GitFile(null, file.getName(), file.getNumberOfFiles(), file.getNumberOfLines(), file.getNumberOfBytes(), obj));
		}
		return gitFile;
	}
	
	public void getCorrectUrl(String url) {		
		List<String> items = Arrays.asList(url.split("/"));

		if (items != null) {
			int index = items.indexOf("github.com");
			if (index > 0 && items.size() > index + 2) {
				repoOnwer = items.get(index + 1);
				repository = items.get(index + 2);
			} else {
				index = items.indexOf("api.github.com");
				if (index > 0 && items.contains("repos") && items.size() > index + 3) {
					repoOnwer = items.get(index + 2);
					repository = items.get(index + 3);
				}
			}
		}
	}

}
