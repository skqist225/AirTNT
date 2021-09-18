package com.airtnt.common.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = { "room", "booking", "reviewByUser" })
@Entity
@Table(name = "reviews")
public class Review extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;

	@ManyToOne
	@JoinColumn(name = "booking_id")
	private Booking booking;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User reviewByUser;

	@Column(nullable = false, length = 1024)
	private String comment;

	@Embedded
	private SubRating subRating;

	@Column(columnDefinition = "TINYINT default 0, check(final_rating >= 0 and final_rating <= 5)")
	private int finalRating;
}
