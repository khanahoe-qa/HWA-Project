package com.qa.hwa.persistence.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Race {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Exclude
	private Long id;
	
	@Column(name = "name")
	@NotNull
	@Size(max = 255)
	private String name;
	
	@Column(name = "type")
	@Size(max = 30)
	private String type;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="rider_race",
	joinColumns = @JoinColumn(name = "race_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "rider_id", referencedColumnName = "id"))
	private List<Rider> riders = new ArrayList<>();
}
