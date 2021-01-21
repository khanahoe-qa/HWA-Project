package com.qa.hwa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.hwa.persistence.domain.Race;
import com.qa.hwa.persistence.dtos.RaceDTO;
import com.qa.hwa.persistence.repo.RaceRepo;
import com.qa.hwa.utils.MyBeanUtils;

@Service
public class RaceService {
	
	private RaceRepo repo;
	private ModelMapper mapper;
	
	@Autowired
	public RaceService(RaceRepo repo, ModelMapper mapper){
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private RaceDTO mapToDTO(Race model) {
		return this.mapper.map(model, RaceDTO.class);
	}
	
	// Read all
	public List<RaceDTO> readAllRaces() {
		List<Race> raceList = this.repo.findAll();
		return raceList.stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	// Read one
	public RaceDTO readRace(Long id) {
		return mapToDTO(this.repo.findById(id).orElseThrow());
	}
	
	// Create
	public RaceDTO createRace(Race toCreate) {
		return mapToDTO(this.repo.save(toCreate));
	}
	
	// Update
	public RaceDTO updateRace(Long id, Race updated) {
		Race existing = this.repo.findById(id).orElseThrow();
		existing.setName(updated.getName());
		existing.setType(updated.getType());
		existing.setRiders(updated.getRiders());
		MyBeanUtils.mergeNotNull(updated, existing);
		return mapToDTO(this.repo.save(existing));
	}
	
	// Delete
	public Boolean deleteRace(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}
