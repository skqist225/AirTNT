package com.airtnt.common.entity;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "amentities")
public class Amentity extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String name;

	@Column(name = "icon_image", columnDefinition = "TEXT NOT NULL")
	private String iconImage;

	private String description;

	@ManyToOne
	@JoinColumn(name = "amtcategory_id")
	private AmentityCategory amentityCategory;

	public Amentity(int id) {
		super(id);
	}
}
