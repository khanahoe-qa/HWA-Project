package com.qa.hwa.persistence.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rider {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Exclude
	private Long id;
	
	@Column(name = "name")
	@NotNull
	@Size(max = 255)
	private String name;
	
	@Column(name = "date_of_birth")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dateOfBirth;
	
	@Column(name = "sex")
	@Size(max = 1)
	@Pattern(regexp = "^[mfMF]{1}$")
	private String sex;
	
	@JsonIgnoreProperties("races")
	@ManyToMany(mappedBy = "riders", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Race> races = new ArrayList<>();
}
