package com.demo.api.postcode;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.api.postcode.entity.PostCodeRecord;
import com.demo.api.postcode.repository.PostCodeRepository;

import static org.assertj.core.api.Assertions.*;


import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostCodeRepositoryTest {
	@Autowired
	private PostCodeRepository postcodeRepo;
	
	/**
	 * By defualt we adding Melbourne suburb recored when starting spring context
	 */
	@Test
	public void testFindByPostCode(){
		List<PostCodeRecord> testList = this.postcodeRepo.findByPostCodeOrderByPostCodeAsc("3000");
		assertThat(testList.size() ==1);
		assertThat(testList.get(0).getPostCode().equals("3000"));
		assertThat(testList.get(0).getState().equals("VIC"));
		assertThat(testList.get(0).getSuburb().equals("MELBOURNE"));
		
	}
	
	@Test
	public void testFindBysuburb(){
		List<PostCodeRecord> testList = this.postcodeRepo.findBySuburbOrderBySuburbAsc("MELBOURNE"); 
		assertThat(testList.size() ==1);
		assertThat(testList.get(0).getPostCode().equals("3000"));
		assertThat(testList.get(0).getState().equals("VIC"));
		assertThat(testList.get(0).getSuburb().equals("MELBOURNE"));
		
	}
}
