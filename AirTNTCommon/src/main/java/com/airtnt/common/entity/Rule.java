package com.airtnt.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "rules")
public class Rule extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String title;

	@Column(columnDefinition = "TEXT NOT NULL")
	private String icon;
}
