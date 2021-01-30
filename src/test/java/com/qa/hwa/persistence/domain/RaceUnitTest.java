package com.qa.hwa.persistence.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.hwa.HwaProjectApplication;

@SpringBootTest(classes = HwaProjectApplication.class)
public class RaceUnitTest {	
	
	Race testRace;
	List<Rider> riders = new ArrayList<>();
	
	@BeforeEach
	public void setup() {
		testRace = new Race(1L, "Test Race 1", "Road", riders);
	}
	
	@Test
	public void testSetId() {
		final Long TEST_ID = 2L;
		
		testRace.setId(TEST_ID);
		Race expected = new Race(2L, "Test Race 1", "Road", riders);
		Assertions.assertThat(testRace).isEqualTo(expected);
	}
	
	@Test
	public void testGetId() {
		
		Long expected = 1L;
		Long result = testRace.getId();
		Assertions.assertThat(result).isEqualTo(expected);
	}
	
	@Test
	public void testSetName() {
		final String TEST_NAME = "New Test Race";
		
		testRace.setName(TEST_NAME);
		Race expected = new Race(1L, TEST_NAME, "Road", riders);
		Assertions.assertThat(testRace).isEqualTo(expected);
	}
	
	@Test
	public void testGetName() {
		
		String expected = "Test Race 1";
		String result = testRace.getName();
		Assertions.assertThat(result).isEqualTo(expected);
	}
	
	@Test
	public void testSetType() {
		final String TEST_TYPE = "Gravel";
		
		testRace.setType(TEST_TYPE);
		Race expected = new Race(2L, "Test Race 1", TEST_TYPE, riders);
		Assertions.assertThat(testRace).isEqualTo(expected);
	}
	
	@Test
	public void testGetType() {
		
		String expected = "Road";
		String result = testRace.getType();
		Assertions.assertThat(result).isEqualTo(expected);
	}
	
	@Test
	public void testSetRiders() {
		List<Rider> newRiders = new ArrayList<>();
		List<Race> races = new ArrayList<>();
		newRiders.add(new Rider(1L, "Joe Bloggs", new Date(), "m", races));
		final List<Rider> TEST_RIDERS = newRiders;
		
		testRace.setRiders(TEST_RIDERS);
		Race expected = new Race(1L, "Test Race 1", "Road", TEST_RIDERS);
		Assertions.assertThat(testRace).isEqualTo(expected);
	}
	
	@Test
	public void testGetRiders() {
		
		List<Rider> expected = new ArrayList<>();
		List<Rider> result = testRace.getRiders();
		Assertions.assertThat(result).isEqualTo(expected);
	}

}
