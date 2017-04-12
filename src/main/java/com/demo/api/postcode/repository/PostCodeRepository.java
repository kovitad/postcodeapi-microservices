package com.demo.api.postcode.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.api.postcode.entity.PostCodeRecord;

@Repository
public interface PostCodeRepository extends CrudRepository<PostCodeRecord,Long>{
	public List<PostCodeRecord> findByPostCodeOrderByPostCodeAsc(String postCode);
	public List<PostCodeRecord> findBySuburbOrderBySuburbAsc(String suburb);
	public List<PostCodeRecord> findByStateOrderByStateAsc(String state);
	public List<PostCodeRecord> findBySuburbAndStateOrderBySuburbAsc(String suburb,String state);
	
	
}
