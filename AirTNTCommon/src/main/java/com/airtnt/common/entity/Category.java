package com.airtnt.common.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
	@Column(nullable = false)
	private String name;

	@Column(columnDefinition = "TEXT NOT NULL")
	private String icon;

	@OneToMany(mappedBy = "category")
	private Set<Room> room = new HashSet<>();

	@Override
	public String toString() {
		return "Category [name=" + name + "]";
	}

	@Builder
	public Category(String name, String icon) {
		super();
		this.name = name;
		this.icon = icon;
	}

	public Category(int id) {
		super(id);
	}
}
