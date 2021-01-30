package com.qa.hwa.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.hwa.persistence.domain.Rider;
import com.qa.hwa.persistence.dtos.RiderDTO;
import com.qa.hwa.services.RiderService;

@RestController
@RequestMapping("/rider")
public class RiderController {
	
	private RiderService service;

	@Autowired
	public RiderController(RiderService service) {
		super();
		this.service = service;
	}
	
	// GET - READ
	@GetMapping("/readAll")
	public ResponseEntity<List<RiderDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAllRiders());
	}
	
	@GetMapping("/read/{id}")
	public ResponseEntity<RiderDTO> readRider(@PathVariable("id") Long id) {
		return new ResponseEntity<RiderDTO>(this.service.readRider(id), HttpStatus.OK);
	}
	
	// POST - CREATE
	@PostMapping("/create")
	public ResponseEntity<RiderDTO> create(@RequestBody Rider rider) {
		return new ResponseEntity<RiderDTO>(this.service.createRider(rider), HttpStatus.CREATED);
	}
	
	// PUT - UPDATE
	@PutMapping("/update")
	public ResponseEntity<RiderDTO> update(@PathParam("id") Long id, @RequestBody Rider rider) {
		return new ResponseEntity<RiderDTO>(this.service.updateRider(id, rider), HttpStatus.OK);
	}
	
	// DELETE - DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<RiderDTO> delete(@PathVariable("id") Long id) {
		return this.service.deleteRider(id) ?
				new ResponseEntity<>(HttpStatus.NO_CONTENT) :
				new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
