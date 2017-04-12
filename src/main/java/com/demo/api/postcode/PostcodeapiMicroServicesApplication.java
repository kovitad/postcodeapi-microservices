package com.demo.api.postcode;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import com.demo.api.postcode.entity.PostCodeRecord;
import com.demo.api.postcode.repository.PostCodeRepository;

@SpringBootApplication
@EnableAuthorizationServer

public class PostcodeapiMicroServicesApplication implements CommandLineRunner{
	private static final Logger logger = LoggerFactory.getLogger(PostcodeapiMicroServicesApplication.class);
	
	@Autowired
	private PostCodeRepository postCodeRepository;
	public static void main(String[] args) {
		SpringApplication.run(PostcodeapiMicroServicesApplication.class, args);
	}
	
	/**
	 * Adding Default Postcode and suburb when starting the Micro Services
	 * 
	 */
	@Override
	public void run(String... strings) throws Exception {
		List<PostCodeRecord> records = new ArrayList<>();
		records.add(new PostCodeRecord("3000", "MELBOURNE","VIC"));
		records.add(new PostCodeRecord("3182", "ST KILDA WEST","VIC"));
	    
		postCodeRepository.save(records);
		
		logger.info("Looking to load default australian post code...");
		for (PostCodeRecord record : postCodeRepository.findAll()) {
	        logger.info(record.getPostCode());
	    }
	}
	
}
