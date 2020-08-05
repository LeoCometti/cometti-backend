package com.trustly.challenge.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.trustly.challenge.RetrieveGitContent;
import com.trustly.challenge.entities.GitFile;
import com.trustly.challenge.entities.GitProject;
import com.trustly.challenge.repositories.GitFileRepository;
import com.trustly.challenge.repositories.GitProjectRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Value("${spring.token}")
	private String appToken;
	
	@Autowired
	private GitProjectRepository gitProjectRepository;

	@Autowired
	private GitFileRepository gitFileRepository;

	@Override
	public void run(String... args) throws Exception {
		Boolean runOne = true; 
		
		if (runOne) {
			RetrieveGitContent retrieveContent = new RetrieveGitContent();
			String url = "https://api.github.com/repos/renatogroffe/ASPNETCore3.1-REST-API-AzureAppConfiguration-KeyVault/contents/";
			//String url2 = "https://github.com/renatogroffe/ASPNETCore3.1-REST-API-AzureAppConfiguration-KeyVault";
			GitProject project1 = new GitProject(null, url);
			List<GitFile> contentList = retrieveContent.getAllContent(project1, appToken);
			gitProjectRepository.saveAll(Arrays.asList(project1));
			gitFileRepository.saveAll(contentList);
			
		} else {
			String url = "https://api.github.com/repos/renatogroffe/ASPNETCore3.1-REST-API-AzureAppConfiguration-KeyVault/contents/";
			//String url2 = "https://github.com/renatogroffe/ASPNETCore3.1-REST-API-AzureAppConfiguration-KeyVault";
			GitProject project1 = new GitProject(null, url);
			gitProjectRepository.saveAll(Arrays.asList(project1));
		}
	}
}
