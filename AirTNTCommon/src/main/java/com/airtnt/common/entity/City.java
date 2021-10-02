package com.airtnt.common.entity;

import lombok.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cities")
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, unique = true)
	private String name;

	public City(int id) {
		this.id = id;
	}

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="state_id")
	private State state;
}
