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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.hwa.HwaProjectApplication;
import com.qa.hwa.persistence.domain.Race;
import com.qa.hwa.persistence.domain.Rider;
import com.qa.hwa.persistence.dtos.RaceDTO;

@SpringBootTest(classes = HwaProjectApplication.class)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:schema-test.sql", "classpath:data-test.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "reg")
public class RaceControllerIntegrationTest {

	// RESOURCES ====================================================================
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ObjectMapper jsonifier;
	
	private RaceDTO mapToDto(Race model) {
		return this.mapper.map(model, RaceDTO.class);
	}
	
	// TESTS ========================================================================
	
	// CREATE
	@Test
	public void testCreateRace() throws Exception {
		
		List<Rider> riders = new ArrayList<>();
		Race TEST_RACE = new Race(3L, "Test race 1", "Road", riders);
		
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.POST, "/race/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_RACE))
				.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(mapToDto(TEST_RACE)));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		
		// Perform and assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	// READ
	@Test
	public void testReadOneRace() throws Exception {
		
		final Long TEST_ID = 3L;
		List<Rider> riders = new ArrayList<>();
		
		RaceDTO TEST_RACE = new RaceDTO(3L, "Test race 3", "Enduro", riders);
		
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/race/read/"+TEST_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(TEST_RACE));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		// Perform and assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	@Test
	public void testReadAllRaces() throws Exception {
		
		List<Rider> riders1 = new ArrayList<>();
		List<RaceDTO> TEST_RACELIST = new ArrayList<>();
		TEST_RACELIST.add(new RaceDTO(1L, "Test race 1", "Road", riders1));
		TEST_RACELIST.add(new RaceDTO(2L, "Test race 2", "Gravel", riders1));
		TEST_RACELIST.add(new RaceDTO(3L, "Test race 3", "Enduro", riders1));
		
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/race/readAll")
				.contentType(MediaType.APPLICATION_JSON)
				//.content(this.jsonifier.writeValueAsString(value))
				.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(TEST_RACELIST));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		// Perform and assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	// UPDATE
	@Test
	public void testUpdateRace() throws Exception {
		
		final Long TEST_ID = 1L;
		
		List<Rider> riders = new ArrayList<>();
		Race TEST_RACE = new Race(1L, "Updated Test Race", "Downhill", riders);
		
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.PUT, "/race/update?id="+TEST_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_RACE))
				.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(mapToDto(TEST_RACE)));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		// Perform and assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	@Test
	public void testAddRider() throws Exception {
		
		final Long RACE_ID = 1L;
		final Long RIDER_ID = 1L;
		
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.PUT, "/race/addRider?raceId="+RACE_ID+"&riderId=+"+RIDER_ID);
		
		// Assertion checks
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		// Perform and assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus);
	}
	
	@Test
	public void testRemoveRider() throws Exception {
		final Long RACE_ID = 1L;
		final Long RIDER_ID = 1L;
		
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest1 = 
				MockMvcRequestBuilders.request(HttpMethod.PUT, "/race/addRider?raceId="+RACE_ID+"&riderId=+"+RIDER_ID);
		MockHttpServletRequestBuilder mockRequest2 =
		MockMvcRequestBuilders.request(HttpMethod.PUT, "/race/addRider?raceId="+RACE_ID+"&riderId=+"+RIDER_ID);
		
		// Assertion checks
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		// Perform and assert
		this.mock.perform(mockRequest1);
		this.mock.perform(mockRequest2)
		.andExpect(matchStatus);
	}
	
	// DELETE
	@Test
	public void testDeleteRace() throws Exception {
		
		final Long RACE_ID = 1L;
		
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.DELETE, "/race/delete/"+RACE_ID);
		
		// Assertion checks
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();
		
		// Perform and assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus);
	}
}
