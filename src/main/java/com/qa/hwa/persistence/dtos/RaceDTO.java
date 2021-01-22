package com.qa.hwa.persistence.dtos;

import java.util.List;

import com.qa.hwa.persistence.domain.Rider;

public class RaceDTO {
	
	private Long id;
	private String name;
	private String type;
	private List<Rider> riders;
	
	public RaceDTO() {
		super();
	}

	public RaceDTO(Long id, String name, String type, List<Rider> riders) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.riders = riders;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Rider> getRiders() {
		return riders;
	}

	public void setRiders(List<Rider> riders) {
		this.riders = riders;
	}

	@Override
	public String toString() {
		return "RaceDTO [id=" + id + ", name=" + name + ", type=" + type + ", riders=" + riders + "]";
	}
}
