package com.airtnt.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "booking_id")
	private Booking booking;

	@OneToOne
	@JoinColumn(name = "promo_code")
	private Promo promoCode;
	
	@Column(columnDefinition = "Decimal(10,2) default '0.00'")
	private float discountAmount;
	
	@Column(columnDefinition = "Decimal(10,2) default '0.00'")
	private float totalFee;
	
	private Date transferOn;

	private boolean isComplete;

}
