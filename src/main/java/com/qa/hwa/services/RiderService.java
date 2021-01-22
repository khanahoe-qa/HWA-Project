package com.qa.hwa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.hwa.persistence.domain.Rider;
import com.qa.hwa.persistence.dtos.RiderDTO;
import com.qa.hwa.persistence.repo.RiderRepo;
import com.qa.hwa.utils.MyBeanUtils;

@Service
public class RiderService {

	private RiderRepo repo;
	private ModelMapper mapper;
	
	@Autowired
	public RiderService(RiderRepo repo, ModelMapper mapper){
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private RiderDTO mapToDTO(Rider model) {
		return this.mapper.map(model, RiderDTO.class);
	}
	
	// Read all
	public List<RiderDTO> readAllRiders() {
		List<Rider> riderList = this.repo.findAll();
		return riderList.stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	// Read one
	public RiderDTO readRider(Long id) {
		return mapToDTO(this.repo.findById(id).orElseThrow());
	}
	
	// Create
	public RiderDTO createRider(Rider toCreate) {
		return mapToDTO(this.repo.save(toCreate));
	}
	
	// Update
	public RiderDTO updateRider(Long id, Rider updated) {
		Rider existing = this.repo.findById(id).orElseThrow();
		existing.setName(updated.getName());
		existing.setDateOfBirth(updated.getDateOfBirth());
		existing.setSex(updated.getSex());
		existing.setRaces(updated.getRaces());
		MyBeanUtils.mergeNotNull(updated, existing);
		return mapToDTO(this.repo.save(existing));
	}
	
	// Delete
	public Boolean deleteRider(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
	
}
