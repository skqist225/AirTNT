package com.airtnt.common.entity;

import javax.persistence.*;

import lombok.*;

import java.util.*;


@Getter
@Setter
@Entity
@Table(name = "rooms")
public class Room extends BaseEntity{

	@Builder
	public Room(int id, boolean status, Date createdDate, Date updatedDate, Set<Image> images, byte rating, List<Review> reviews, Country country, State state,
			City city, String bedRoomCount, String bathRoomCount, String accomodatesCount, String bedCount,
			Category category, String description, Set<Amentity> amentities, float latitude, float longtitude,
			float price, PriceType priceType, String mininumStay, StayType stayType, User host, Set<Rule> rules) {
		super(id, status, createdDate, updatedDate);
		this.name = name;
		this.images = images;
		this.rating = rating;
		this.reviews = reviews;
		this.country = country;
		this.state = state;
		this.city = city;
		this.bedRoomCount = bedRoomCount;
		this.bathRoomCount = bathRoomCount;
		this.accomodatesCount = accomodatesCount;
		this.bedCount = bedCount;
		this.category = category;
		this.description = description;
		this.amentities = amentities;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.price = price;
		this.priceType = priceType;
		this.mininumStay = mininumStay;
		this.stayType = stayType;
		this.host = host;
		this.rules = rules;
	}

	@Column(nullable = false, length = 512)
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "room", orphanRemoval = true)
	private Set<Image> images = new HashSet<>();

	@Column(columnDefinition = "smallint")
	private byte rating;

	@OneToMany(mappedBy = "room")
	private List<Review> reviews = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "country_id", referencedColumnName = "id")
	private Country country;

	@OneToOne
	@JoinColumn(name = "state_id", referencedColumnName = "id")
	private State state;

	@OneToOne
	@JoinColumn(name = "city_id", referencedColumnName = "id")
	private City city;

	@Column(columnDefinition = "VARCHAR(10) NOT NULL", name = "bedroom_count")
	private String bedRoomCount;

	@Column(length = 10, nullable = false, name = "bathroom_count")
	private String bathRoomCount;

	@Column(length = 10, nullable = false, name = "accomodates_count")
	private String accomodatesCount;

	@Column(length = 10, nullable = false, name = "bed_count")
	private String bedCount;

	@OneToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;

	private String description;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "rooms_amentities", joinColumns = @JoinColumn(name = "room_id"), inverseJoinColumns = @JoinColumn(name = "amentity_id"))
	private Set<Amentity> amentities = new HashSet<>();

	private float latitude;

	private float longtitude;

	@Column(nullable = false)
	private float price;

	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private PriceType priceType;

	@Column(length = 5, nullable = false)
	private String mininumStay;

	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private StayType stayType;

	@OneToOne
	@JoinColumn(name = "host_id", referencedColumnName = "id")
	private User host;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "rooms_rules", joinColumns = @JoinColumn(name = "room_id"), inverseJoinColumns = @JoinColumn(name = "rule_id"))
	private Set<Rule> rules = new HashSet<>();

}
