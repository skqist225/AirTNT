package com.airtnt.common.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bookings")
public class Booking extends BaseEntity {

	private Date checkinDay;
	
	private Date checkoutDay;
	
	private Date cancelDay;
	
	@Column(columnDefinition = "Decimal(10,2)", nullable = false)
	private float pricePerDay;
	
	@Column(columnDefinition = "Decimal(10,2) default '0.00'")
	private float priceForStay;
	
	@Builder.Default
	@Column(columnDefinition = "boolean default false")
	private boolean isRefund = false;
	
	@Column(columnDefinition = "Decimal(10,2) default '0.00'")
	private float refundPaid;
	
	private Date bookingDay;
	
	@Column(columnDefinition = "Decimal(10,2)", nullable = false)
	private float taxPaid;
	
	@Column(columnDefinition = "Decimal(10,2)", nullable = false)
	private float siteFee;
	
	
}
