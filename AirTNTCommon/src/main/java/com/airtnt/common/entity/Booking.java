package com.airtnt.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bookings")
public class Booking extends BaseEntity {
	
	@Builder
	public Booking(int id, boolean status, Date createdDate, Date updatedDate, Date checkinDay, Date checkoutDay,
			Date cancelDay, float pricePerDay, float priceForStay, boolean isRefund, float refundPaid, Date bookingDay,
			float taxPaid, float siteFee) {
		super(id, status, createdDate, updatedDate);
		this.checkinDay = checkinDay;
		this.checkoutDay = checkoutDay;
		this.cancelDay = cancelDay;
		this.pricePerDay = pricePerDay;
		this.priceForStay = priceForStay;
		this.isRefund = isRefund;
		this.refundPaid = refundPaid;
		this.bookingDay = bookingDay;
		this.taxPaid = taxPaid;
		this.siteFee = siteFee;
	}

	private Date checkinDay;
	
	private Date checkoutDay;
	
	private Date cancelDay;
	
	@Column(name = "price_per_day", columnDefinition = "Decimal(10,2)", nullable = false)
	private float pricePerDay;
	
	@Column(name = "price_for_stay", columnDefinition = "Decimal(10,2) default '0.00'")
	private float priceForStay;
	
	@Column(name = "is_refund")
	private boolean isRefund = false;
	
	@Column(name = "refund_paid", columnDefinition = "Decimal(10,2) default '0.00'")
	private float refundPaid;
	
	private Date bookingDay;
	
	@Column(name = "tax_paid", columnDefinition = "Decimal(10,2)", nullable = false)
	private float taxPaid;
	
	@Column(name = "site_fee", columnDefinition = "Decimal(10,2)", nullable = false)
	private float siteFee;
	
	
}
