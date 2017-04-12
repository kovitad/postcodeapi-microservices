package com.demo.api.postcode;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.api.postcode.components.PostCodeServices;

@RunWith(SpringJUnit4ClassRunner.class)
public class PostCodeServiceTest {
	private PostCodeServices postCodeServiceMock;
   

    @Before
    public void setUp() {
    	postCodeServiceMock = Mockito.mock(PostCodeServices.class);
        
    }
    
    @Test
    public void createClientSuccessfuly() throws Exception {
    
    	when(postCodeServiceMock.searchByPostCode(contains("3000"))).thenReturn(new ArrayList<>());
    	assertNotNull(postCodeServiceMock.searchByPostCode("3000"));
    }
}
