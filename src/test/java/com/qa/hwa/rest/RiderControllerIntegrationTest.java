package com.qa.hwa.rest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.hwa.HwaProjectApplication;
import com.qa.hwa.persistence.domain.Race;
import com.qa.hwa.persistence.domain.Rider;
import com.qa.hwa.persistence.dtos.RiderDTO;

@SpringBootTest(classes = HwaProjectApplication.class)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:schema-test.sql", "classpath:data-test.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "reg")
public class RiderControllerIntegrationTest {

	// RESOURCES ====================================================================
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ObjectMapper jsonifier;
	
	private RiderDTO mapToDto(Rider model) {
		return this.mapper.map(model, RiderDTO.class);
	}
	
	// TESTS ========================================================================
	
	// CREATE
	@Test
	public void testCreateRider() throws Exception {
		
		List<Race> races = new ArrayList<>();
		
		Date myDate = new Date();
		// BODGE
		myDate.setTime(631152000000L);
		Rider TEST_RIDER = new Rider(4L, "Xavier Tra", myDate, "m", races);
		
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.POST, "/rider/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_RIDER))
				.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(mapToDto(TEST_RIDER)));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		
		// Perform and assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	// READ
	@Test
	public void testReadOneRider() throws Exception {
		
		final Long TEST_ID = 1L;
		List<Race> races = new ArrayList<>();
		Date myDate = new Date();
		myDate.setTime(631152000000L);
		Rider TEST_RIDER = new Rider(1L, "Phil Space", myDate, "m", races);
		
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/rider/read/"+TEST_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_RIDER))
				.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(mapToDto(TEST_RIDER)));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		// Perform and assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	@Test
	@Disabled
	public void testReadAllRides() throws Exception {

		List<Race> races = new ArrayList<>();
		List<Rider> TEST_RIDERS = new ArrayList<>();
		Calendar myCalendar1 = new GregorianCalendar(1990, 0, 1);
		Date myDate1 = myCalendar1.getTime();
		TEST_RIDERS.add(new Rider(1L, "Phil Space", myDate1, "m", races));
		Calendar myCalendar2 = new GregorianCalendar(1992, 1, 2);
		Date myDate2 = myCalendar2.getTime();
		TEST_RIDERS.add(new Rider(2L, "Polly Filla", myDate2, "f", races));
		Calendar myCalendar3 = new GregorianCalendar(1994, 3, 4);
		Date myDate3 = myCalendar3.getTime();
		TEST_RIDERS.add(new Rider(3L, "Lazy Joe", myDate3, "m", races));
		
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/rider/readAll")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_RIDERS))
				.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(TEST_RIDERS));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		// Perform and assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	// UPDATE
	
	@Test
	public void testUpdateRider() throws Exception {
		final Long TEST_ID = 1L;
		
		Date myDate = new Date();
		myDate.setTime(631152000000L);

		List<Race> races = new ArrayList<>();
		Rider TEST_RIDER = new Rider(1L, "Herbert Gusset", myDate, "m", races);
		
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.PUT, "/rider/update?id="+TEST_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_RIDER))
				.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(mapToDto(TEST_RIDER)));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		// Perform and assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	// DELETE
	
	@Test
	public void testDeleteRider() throws Exception {
		final Long RIDER_ID = 1L;
		
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.DELETE, "/rider/delete/"+RIDER_ID);
		
		// Assertion checks
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();
		
		// Perform and assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus);
	}
	
}
