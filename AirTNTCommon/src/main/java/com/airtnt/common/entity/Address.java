package com.airtnt.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "addresses")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name = "country_id", referencedColumnName = "id")
	private Country country;
	
	@OneToOne
	@JoinColumn(name = "state_id", referencedColumnName = "id")
	private State state;
	
	@OneToOne
	@JoinColumn(name = "city_id", referencedColumnName = "id")
	private City city;
	
	@Column(name = "apartmentNo_street")
	private String AprtNoAndStreet;
}
