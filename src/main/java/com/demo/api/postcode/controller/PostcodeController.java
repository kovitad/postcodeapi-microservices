package com.demo.api.postcode.controller;


import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.api.postcode.components.PostCodeServices;
import com.demo.api.postcode.entity.PostCodeRecord;
import com.demo.api.postcode.entity.SearchResult;
import com.demo.api.postcode.exception.ResourceNotFoundException;

/**
 * Rest Controller to define the Restful APIs
 * Search by Post Code, Search By suburb and Add new Record
 * 
 * @author kovitadjanlakhon
 *
 */

@RestController
@Validated
public class PostcodeController {
	
	@Autowired
	private PostCodeServices service;
	
	@RequestMapping("/")
	public String home() {
		return "Welcome to Post Code Micro Service API";
	}
	/**
	 * Restful API to search by postcode
	 * 
	 * @param postcode must be 4 digits (Australian postcode)
	 * @return The JSON Response containing postcode ,suburb and state
	 */
	@CrossOrigin
	@RequestMapping(value="/api/public/postcode/{postcode}", method=RequestMethod.GET)
	public SearchResult search(
			@Valid
			
			
			@Pattern(regexp="([0-9]{4})", message = "Invalid Australian PostCode Format")
			@PathVariable ("postcode")  
			String postcode){
		
		SearchResult result = new SearchResult();
		result.setStatus(PostCodeServices.API_SUCCESS_STATUS);
		List<PostCodeRecord> searchResult = service.searchByPostCode(postcode);
		try {
			Assert.notEmpty(searchResult);
		}catch(Exception e){
			throw new ResourceNotFoundException(e);
		}
		
		result.setResult( searchResult);
		return  result;
	}
	/**
	 * Restful API to search by suburb
	 * 
	 * @param suburb The Australian suburb Name
	 * @return The JSON Response containing postcode ,suburb and state
	 */
	@CrossOrigin
	@RequestMapping(value="/api/public/suburb/{suburbName}", method=RequestMethod.GET)
	public SearchResult searchByStateAndsuburb(
	@PathVariable ("suburbName")  String suburbName){
		SearchResult result = new SearchResult();
		result.setStatus(PostCodeServices.API_SUCCESS_STATUS);
		List<PostCodeRecord> searchResult = service.searchBysuburb(suburbName);
		try {
			Assert.notEmpty(searchResult);
		}catch(Exception e){
			throw new ResourceNotFoundException(e);
		}
		result.setResult(searchResult);
		return  result;
	}
	/**
	 * The Restful API to add new Postcode record to the database
	 * 
	 * @param newRecord
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/api/secure/addrecord", method=RequestMethod.POST)
	public PostCodeRecord addNewRecord(@RequestBody PostCodeRecord newRecord){
		return service.saveNewRecord(newRecord);
	}
	
	
	
}
