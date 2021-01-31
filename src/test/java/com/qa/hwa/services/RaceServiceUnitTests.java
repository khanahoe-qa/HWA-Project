package com.qa.hwa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.hwa.HwaProjectApplication;
import com.qa.hwa.persistence.domain.Race;
import com.qa.hwa.persistence.domain.Rider;
import com.qa.hwa.persistence.dtos.RaceDTO;
import com.qa.hwa.persistence.repo.RaceRepo;
import com.qa.hwa.persistence.repo.RiderRepo;

@SpringBootTest(classes = HwaProjectApplication.class)
public class RaceServiceUnitTests {
	
	@MockBean
	private RaceRepo repo;
	
	@MockBean
	private RiderRepo riderRepo;
	
	@Autowired
	private RaceService service;
	
	@Test
	public void testReadAllRaces() {
		
		List<Race> races = new ArrayList<>();
		races.add(new Race(1L, "Test Race", "Test Type", null));
		races.add(new Race(2L, "Test Race", "Test Type", null));
		
		List<RaceDTO> expected = new ArrayList<>();
		expected.add(new RaceDTO(1L, "Test Race", "Test Type", null));
		expected.add(new RaceDTO(2L, "Test Race", "Test Type", null));
		
		// Rules
		Mockito.when(this.repo.findAll()).thenReturn(races);
		
		// Action
		List<RaceDTO> result = this.service.readAllRaces();
		
		// Assert and verify
		Assertions.assertThat(result).isEqualTo(expected);
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void testReadRace() {
		
		final Long ID = 1L;
		
		Race race = new Race(ID, "Test Race", "Test Type", null);
		RaceDTO expected = (new RaceDTO(ID, "Test Race", "Test Type", null));
		
		// Rules
		Mockito.when(this.repo.findById(Mockito.any(Long.class))).thenReturn(Optional.of(race));
		
		// Action
		RaceDTO result = this.service.readRace(ID);
		
		// Assert and verify
		Assertions.assertThat(result).isEqualTo(expected);
		Mockito.verify(this.repo, Mockito.times(1)).findById(ID);
	}
	
	@Test
	public void testCreateRace() {
		
		Race race = new Race(1L, "Test Race", "Test Type", null);
		RaceDTO expected = new RaceDTO(1L, "Test Race", "Test Type", null);
		
		// Rules
		Mockito.when(this.repo.save(Mockito.any(Race.class))).thenReturn(race);
		
		// Action
		RaceDTO result = this.service.createRace(race);
		
		// Assert and verify
		Assertions.assertThat(result.equals(expected)).isTrue();
		Mockito.verify(this.repo, Mockito.times(1)).save(race);
	}
	
	@Test
	public void testUpdateRace() {
		
		final Long ID = 1L;
		
		Race race = new Race(ID, "Test Race", "Test Type", null);
		Race update = new Race(ID, "Updated Test Race", "Updated Test Type", null);
		RaceDTO expected = new RaceDTO(ID, "Updated Test Race", "Updated Test Type", null);
		
		// Rules
		Mockito.when(this.repo.findById(Mockito.any(Long.class))).thenReturn(Optional.of(race));
		Mockito.when(this.repo.save(Mockito.any(Race.class))).thenReturn(race);
		
		// Action
		RaceDTO result = this.service.updateRace(ID, update);
		
		// Assert and verify
		Assertions.assertThat(result).isEqualTo(expected);
		Mockito.verify(this.repo, Mockito.times(1)).findById(ID);
		Mockito.verify(this.repo, Mockito.times(1)).save(race);
	}
	
	@Test
	public void testAddRider() {
		
		List<Rider> existing = new ArrayList<>();
		Race race = new Race(1L, "Test Race", "Test Type", existing);
		Rider toAdd = new Rider();
		toAdd.setId(1L);
		List<Rider> riderList = new ArrayList<>();
		riderList.add(toAdd);
		RaceDTO expected = new RaceDTO(1L, "Test Race", "Test Type", riderList);
		
		// Rules
		Mockito.when(this.repo.findById(Mockito.any(Long.class))).thenReturn(Optional.of(race));
		Mockito.when(this.riderRepo.findById(Mockito.any(Long.class))).thenReturn(Optional.of(toAdd));
		Mockito.when(this.repo.save(Mockito.any(Race.class))).thenReturn(race);
		
		// Action
		RaceDTO result = this.service.addRider(1L, 1L);
		
		// Assert and verify
		Assertions.assertThat(result).isEqualTo(expected);
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.riderRepo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).save(race);
		
	}
	
	@Test
	public void testRemoveRider() {
		
		List<Rider> existing = new ArrayList<>();
		Rider toRemove = new Rider();
		toRemove.setId(1L);
		existing.add(toRemove);
		
		Race race = new Race(1L, "Test Race", "Test Type", existing);

		List<Rider> riderList = new ArrayList<>();
		RaceDTO expected = new RaceDTO(1L, "Test Race", "Test Type", riderList);
		
		// Rules
		Mockito.when(this.repo.findById(Mockito.any(Long.class))).thenReturn(Optional.of(race));
		Mockito.when(this.repo.save(Mockito.any(Race.class))).thenReturn(race);
		
		// Action
		RaceDTO result = this.service.removeRider(1L, 1L);
		
		// Assert and verify
		Assertions.assertThat(result).isEqualTo(expected);
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).save(race);
		
	}
	
	@Test
	public void testDeleteRace() {
		
		// Rules
		Mockito.when(this.repo.existsById(Mockito.any(Long.class))).thenReturn(false);
		
		// Action
		Boolean result = this.service.deleteRace(1L);
		
		// Assert and verify
		Assertions.assertThat(result).isTrue();
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);	
	}

}
