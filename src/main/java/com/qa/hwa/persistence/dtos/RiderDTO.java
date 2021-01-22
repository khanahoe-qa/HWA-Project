package com.qa.hwa.persistence.dtos;

import java.util.Date;
import java.util.List;

import com.qa.hwa.persistence.domain.Race;

public class RiderDTO {

	private Long id;
	private String name;
	private Date dateOfBirth;
	private String sex;
	private List<Race> races;
	
	public RiderDTO() {
		super();
	}
	
	public RiderDTO(Long id, String name, Date dateOfBirth, String sex, List<Race> races) {
		super();
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.sex = sex;
		this.races = races;
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
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public List<Race> getRaces() {
		return races;
	}
	
	public void setRaces(List<Race> races) {
		this.races = races;
	}
	
	@Override
	public String toString() {
		return "RiderDTO [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", sex=" + sex + ", races="
				+ races + "]";
	}
	
}
