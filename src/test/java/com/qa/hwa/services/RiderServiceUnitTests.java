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
import com.qa.hwa.persistence.dtos.RiderDTO;
import com.qa.hwa.persistence.repo.RiderRepo;

@SpringBootTest(classes = HwaProjectApplication.class)
public class RiderServiceUnitTests {
	
	@MockBean
	private RiderRepo repo;
	
	@Autowired
	private RiderService service;
	
	@Test
	public void testReadAllRiders() {
		
		List<Rider> riders = new ArrayList<>();
		riders.add(new Rider(1L, "Phil Space", null, "m", null));
		riders.add(new Rider(2L, "Joe Bloggs", null, "m", null));
		
		List<RiderDTO> expected = new ArrayList<>();
		expected.add(new RiderDTO(1L, "Phil Space", null, "m", null));
		expected.add(new RiderDTO(2L, "Joe Bloggs", null, "m", null));
		
		// Rules
		Mockito.when(this.repo.findAll()).thenReturn(riders);
		
		// Action
		List<RiderDTO> result = this.service.readAllRiders();
		
		// Assert and verify
		Assertions.assertThat(result).isEqualTo(expected);
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void testReadRider() {
		
		final Long ID = 1L;
		
		Rider rider = new Rider(1L, "Phil Space", null, "m", null);
		RiderDTO expected = new RiderDTO(1L, "Phil Space", null, "m", null);
		
		// Rules
		Mockito.when(this.repo.findById(Mockito.any(Long.class))).thenReturn(Optional.of(rider));
		
		// Action
		RiderDTO result = this.service.readRider(ID);
		
		// Assert and verify
		Assertions.assertThat(result).isEqualTo(expected);
		Mockito.verify(this.repo, Mockito.times(1)).findById(ID);
	}
	
	@Test
	public void testCreateRider() {
		
		Rider rider = new Rider(1L, "Phil Space", null, "m", null);
		RiderDTO expected = new RiderDTO(1L, "Phil Space", null, "m", null);
		
		// Rules
		Mockito.when(this.repo.save(Mockito.any(Rider.class))).thenReturn(rider);
		
		// Action
		RiderDTO result = this.service.createRider(rider);
		
		// Assert and verify
		Assertions.assertThat(result.equals(expected)).isTrue();
		Mockito.verify(this.repo, Mockito.times(1)).save(rider);
	}
	
	@Test
	public void testUpdateRider() {
		
		final Long ID = 1L;
		
		Rider rider = new Rider(ID, "Phil Space", null, "m", null);
		Rider update = new Rider(ID, "Polly Filla", null, "f", null);
		RiderDTO expected = new RiderDTO(ID, "Polly Filla", null, "f", null);
		
		// Rules
		Mockito.when(this.repo.findById(Mockito.any(Long.class))).thenReturn(Optional.of(rider));
		Mockito.when(this.repo.save(Mockito.any(Rider.class))).thenReturn(rider);
		
		// Action
		RiderDTO result = this.service.updateRider(ID, update);
		
		// Assert and verify
		Assertions.assertThat(result).isEqualTo(expected);
		Mockito.verify(this.repo, Mockito.times(1)).findById(ID);
		Mockito.verify(this.repo, Mockito.times(1)).save(rider);
	}
	
	@Test
	public void testDeleteRider() {
		
		// Rules
		Mockito.when(this.repo.existsById(Mockito.any(Long.class))).thenReturn(false);
		
		// Action
		Boolean result = this.service.deleteRider(1L);
		
		// Assert and verify
		Assertions.assertThat(result).isTrue();
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);	
	}

}
