package com.airtnt.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "promos")
public class Promo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(name = "code", columnDefinition = "VARCHAR(20) NOT NULL", unique = true)
	private String promoCode;
	
	@Column(nullable = false)
	private String description;
	
	@Column(name= "discount_percent",columnDefinition = "DECIMAL(3) NOT NULL DEFAULT 0, check(discount_percent >= 0 and discount_percent <= 100)")
	private byte discountPercent;
}
