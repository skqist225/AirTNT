package com.airtnt.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {
	
	@Builder
	public Transaction(int id, boolean status, Date createdDate, Date updatedDate, Booking booking, Promo promoCode,
			float discountAmount, float totalFee, Date transferOn, boolean isComplete) {
		super(id, status, createdDate, updatedDate);
		this.booking = booking;
		this.promoCode = promoCode;
		this.discountAmount = discountAmount;
		this.totalFee = totalFee;
		this.transferOn = transferOn;
		this.isComplete = isComplete;
	}

	@ManyToOne
	@JoinColumn(name = "booking_id")
	private Booking booking;

	

	@OneToOne
	@JoinColumn(name = "promo_code", referencedColumnName = "id")
	private Promo promoCode;
	
	@Column(columnDefinition = "Decimal(10,2) default '0.00'")
	private float discountAmount;
	
	@Column(columnDefinition = "Decimal(10,2) default '0.00'")
	private float totalFee;
	
	private Date transferOn;

	private boolean isComplete;

}
