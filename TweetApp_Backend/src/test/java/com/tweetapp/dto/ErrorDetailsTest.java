package com.tweetapp.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tweetapp.dto.ErrorDetails;
@ExtendWith(MockitoExtension.class)
class ErrorDetailsTest {

	ErrorDetails errorDetail;
	@BeforeEach
	public void before(){
		errorDetail=new ErrorDetails();
		errorDetail.setCode("404");
		errorDetail.setMsg("Not Found");
	}
	
	@Test
	public void testGetCode() {
		assertEquals("404", errorDetail.getCode());
	}
	
	@Test
	public void testGetMsg() {
		assertEquals("Not Found", errorDetail.getMsg());
	}

}
