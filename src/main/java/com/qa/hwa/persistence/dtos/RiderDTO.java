package com.qa.hwa.persistence.dtos;

import java.util.List;

import com.qa.hwa.persistence.domain.Race;

public class RiderDTO {

	private Long id;
	private String name;
	private Short age;
	private Character sex;
	private List<Race> races;
	
	public RiderDTO() {
		super();
	}

	public RiderDTO(Long id, String name, Short age, Character sex, List<Race> races) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
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

	public Short getAge() {
		return age;
	}

	public void setAge(Short age) {
		this.age = age;
	}

	public Character getSex() {
		return sex;
	}

	public void setSex(Character sex) {
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
		return "RiderDTO [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + ", races=" + races + "]";
	}
}
