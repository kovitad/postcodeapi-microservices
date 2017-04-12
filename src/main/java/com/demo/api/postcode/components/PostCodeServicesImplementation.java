package com.demo.api.postcode.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.api.postcode.entity.PostCodeRecord;
import com.demo.api.postcode.repository.PostCodeRepository;

/**
 * Post code services to search and store the postcode entity
 * 
 * @author kovitadjanlakhon
 *
 */

@Service
public class PostCodeServicesImplementation implements PostCodeServices{
	@Autowired
	private PostCodeRepository postcodeRepository;
	@Override
	public List<PostCodeRecord> searchByPostCode(String postCode) {
		
		return postcodeRepository.findByPostCodeOrderByPostCodeAsc(postCode);
	}

	@Override
	public List<PostCodeRecord> searchBysuburb(String suburbName) {
		
		return postcodeRepository.findBySuburbOrderBySuburbAsc(suburbName);
	}

	@Override
	public PostCodeRecord saveNewRecord(PostCodeRecord record) {
		
		return postcodeRepository.save(record);
	}

	@Override
	public List<PostCodeRecord> searhAll() {
		return (List<PostCodeRecord>) postcodeRepository.findAll();
	}

}
