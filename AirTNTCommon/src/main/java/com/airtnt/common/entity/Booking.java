package com.airtnt.common.entity;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bookings")
public class Booking extends BaseEntity {
	private Date checkinDate;

	private Date checkoutDate;

	private LocalDateTime bookingDate;

	private Date cancelDate;

	@Column(columnDefinition = "Decimal(10,2)", nullable = false)
	private float pricePerDay;

	@Column(columnDefinition = "SMALLINT default 0")
	private int numberOfDays;

	@Builder.Default
	@Column(columnDefinition = "boolean default false")
	private boolean isRefund = false;

	@Column(columnDefinition = "Decimal(10,2) default '0.00'")
	private float refundPaid;

	@Column(columnDefinition = "Decimal(10,2)", nullable = false)
	private float siteFee;

	@Column(columnDefinition = "Decimal(10,2) default '0.00'")
	private float totalFee;

	private boolean isComplete;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private User customer;

	@OneToOne
	@JoinColumn(name = "room_id", nullable = false)
	private Room room;
}
