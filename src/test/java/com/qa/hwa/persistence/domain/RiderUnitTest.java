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
public class RiderUnitTest {

	Rider testRider;
	List<Race> races = new ArrayList<>();
	final Date MY_DATE = new Date();
	
	@BeforeEach
	public void setup() {
		testRider = new Rider(1L, "Joe Bloggs", MY_DATE, "m", races);
	}
	
	@Test
	public void testSetId() {
		final Long TEST_ID = 2L;
		
		testRider.setId(TEST_ID);
		Rider expected = new Rider(TEST_ID, "Joe Bloggs", MY_DATE, "m", races);
		Assertions.assertThat(testRider).isEqualTo(expected);
	}
	
	@Test
	public void testGetId() {
		
		Long expected = 1L;
		Long result = testRider.getId();
		Assertions.assertThat(result).isEqualTo(expected);
	}
	
	@Test
	public void testSetName() {
		final String TEST_NAME = "Phil Space";
		
		testRider.setName(TEST_NAME);
		Rider expected = new Rider(1L, TEST_NAME, MY_DATE, "m", races);
		Assertions.assertThat(testRider).isEqualTo(expected);
	}
	
	@Test
	public void testGetName() {
		
		String expected = "Joe Bloggs";
		String result = testRider.getName();
		Assertions.assertThat(result).isEqualTo(expected);
	}
	
	@Test
	public void testSetDob() {
		Date testDate = new Date();
		testDate.setTime(128476875634L);
		
		testRider.setDateOfBirth(testDate);
		Rider expected = new Rider(1L, "Joe Bloggs", testDate, "m", races);
		Assertions.assertThat(testRider).isEqualTo(expected);
	}
	
	@Test
	public void testGetDob() {
		
		Date expected = MY_DATE;
		Date result = testRider.getDateOfBirth();
		Assertions.assertThat(result).isEqualTo(expected);
	}
	
	@Test
	public void testSetSex() {
		final String TEST_SEX = "f";
		
		testRider.setSex(TEST_SEX);
		Rider expected = new Rider(1L, "Joe Bloggs", MY_DATE, "f", races);
		Assertions.assertThat(testRider).isEqualTo(expected);
	}
	
	@Test
	public void testGetSex() {
		
		String expected = "m";
		String result = testRider.getSex();
		Assertions.assertThat(result).isEqualTo(expected);
	}
	
	@Test
	public void testSetRaces() {
		List<Race>newRaces = new ArrayList<>();
		newRaces.add(new Race());
		final List<Race> TEST_RACES = newRaces;
		
		testRider.setRaces(TEST_RACES);
		Rider expected = new Rider(1L, "Joe Bloggs", MY_DATE, "m", TEST_RACES);
		Assertions.assertThat(testRider).isEqualTo(expected);
	}
	
	@Test
	public void testGetRaces() {
		
		List<Race> expected = new ArrayList<>();
		List<Race> result = testRider.getRaces();
		Assertions.assertThat(result).isEqualTo(expected);
	}
	
}
