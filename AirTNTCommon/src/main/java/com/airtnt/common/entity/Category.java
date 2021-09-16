package com.airtnt.common.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.persistence.*;

@Getter
@Setter
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

}
