package com.airtnt.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review extends BaseEntity{
	
	@Builder
	public Review(int id, boolean status, Date createdDate, Date updatedDate, Room room, Booking booking,
			User reviewByUser, String comment, SubRating subRating, int finalRating) {
		super(id, status, createdDate, updatedDate);
		this.room = room;
		this.booking = booking;
		this.reviewByUser = reviewByUser;
		this.comment = comment;
		this.subRating = subRating;
		this.finalRating = finalRating;
	}

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
	
	@Column(name = "final_rating", columnDefinition = "TINYINT default 0, check(final_rating >= 0 and final_rating <= 5)")
	private int finalRating;
	
}
