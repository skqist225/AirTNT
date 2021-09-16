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

	@Builder
	public Category(int id, boolean status, Date createdDate, Date updatedDate, String name) {
		super(id, status, createdDate, updatedDate);
		this.name = name;
	}

	@Column(nullable = false)
	private String name;

	@Column(columnDefinition = "TEXT NOT NULL")
	private String icon;
	
	@OneToMany(mappedBy = "category")
	private Set<Room> room = new HashSet<>();

	public Category(int id) {
		super(id);
	}

	@Override
	public String toString() {
		return "Category [name=" + name + "]";
	}
	
	
}
