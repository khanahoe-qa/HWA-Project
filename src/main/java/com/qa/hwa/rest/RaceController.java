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

import com.qa.hwa.persistence.domain.Race;
import com.qa.hwa.persistence.dtos.RaceDTO;
import com.qa.hwa.services.RaceService;

@RestController
@RequestMapping("/race")
public class RaceController {
	
	private RaceService service;
	private static Long currentId = 0L;

	@Autowired
	public RaceController(RaceService service) {
		super();
		this.service = service;
	}
	
	// GET - READ
	@GetMapping("/readAll")
	public ResponseEntity<List<RaceDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAllRaces());
	}
	
	@GetMapping("/read/{id}")
	public ResponseEntity<RaceDTO> readCat(@PathVariable("id") Long id) {
		return new ResponseEntity<RaceDTO>(this.service.readRace(id), HttpStatus.OK);
	}
	
	// POST - CREATE
	@PostMapping("/create")
	public ResponseEntity<RaceDTO> create(@RequestBody Race race) {
		race.setId(currentId);
		++currentId;
		return new ResponseEntity<RaceDTO>(this.service.createRace(race), HttpStatus.CREATED);
	}
	
	// PUT - UPDATE
	@PutMapping("/update")
	public ResponseEntity<RaceDTO> update(@PathParam("id") Long id, @RequestBody Race race) {
		return new ResponseEntity<RaceDTO>(this.service.updateRace(id, race), HttpStatus.ACCEPTED);
	}
	
	// DELETE - DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<RaceDTO> delete(@PathVariable("id") Long id) {
		return this.service.deleteRace(id) ?
				new ResponseEntity<>(HttpStatus.NO_CONTENT) :
				new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
