package com.qa.hwa.persistence.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
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
	
	@NotNull
	@Size(max = 200)
	private String name;
	
	@Size(max = 30)
	private String type;
	
	@ManyToMany
	@JoinTable(name="RIDER_RACES")
	private List<Rider> riders = new ArrayList<>();
}
