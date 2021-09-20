package com.airtnt.common.entity;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = { "room" })
@Builder
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
	@Column(nullable = false)
	private String name;

	@Column(columnDefinition = "TEXT NOT NULL")
	private String icon;

	@Builder.Default
	@OneToMany(mappedBy = "category")
	private Set<Room> room = new HashSet<>();

	public Category(int id) {
		super(id);
	}

	@Transient
	public String getIconPath() {
		return "/category_images/" + this.icon;
	}
}
