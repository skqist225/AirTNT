package com.airtnt.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "cities" })
@Builder
@Entity
@Table(name = "states")
public class State {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, unique = true)
	private String name;

	@Column(columnDefinition = "VARCHAR(10)")
	private String code;

	@Builder.Default
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "state_id")
	private Set<City> cities = new HashSet<>();

	public State(int id) {
		this.id = id;
	}
}
