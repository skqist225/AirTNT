package com.airtnt.common.entity;

import javax.persistence.*;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString(exclude = {"amentityCategory"})
@Entity
@Table(name = "amentities")
public class Amentity extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String name;

	@Column(name = "icon_image", columnDefinition = "TEXT NOT NULL")
	private String iconImage;

	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private AmentityCategory amentityCategory;

	@Builder
	public Amentity(int id, boolean status, Date createdDate, Date updatedDate, String name, String iconImage, AmentityCategory amentityCategory, String description) {
		super(id, status, createdDate, updatedDate);
		this.name = name;
		this.iconImage = iconImage;
		this.amentityCategory = amentityCategory;
		this.description = description;
	}

	public Amentity(int id) {
		super(id);
	}
}
