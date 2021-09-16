package com.airtnt.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rooms")
public class Room extends BaseEntity {

	@Column(nullable = false, length = 512, unique = true)
	private String name;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "room_id", referencedColumnName = "id")
	private Set<Image> images = new HashSet<>();

	@Column(columnDefinition = "smallint")
	private byte rating;

	@OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
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

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@Column(columnDefinition = "TEXT NOT NULL")
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

	@Column(length = 5, nullable = false, columnDefinition = "TINYINT")
	private int mininumStay;

	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private StayType stayType;

	@OneToOne
	@JoinColumn(name = "host_id", referencedColumnName = "id")
	private User host;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "rooms_rules", joinColumns = @JoinColumn(name = "room_id"), inverseJoinColumns = @JoinColumn(name = "rule_id"))
	private Set<Rule> rules = new HashSet<>();

	@Builder
	public Room(int id, boolean status, Date createdDate, Date updatedDate, Set<Image> images, byte rating,
			List<Review> reviews, Country country, State state, City city, String bedRoomCount, String bathRoomCount,
			String accomodatesCount, String bedCount, Category category, String description, Set<Amentity> amentities,
			float latitude, float longtitude, float price, PriceType priceType, int mininumStay, StayType stayType,
			User host, Set<Rule> rules, String name) {
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

	@Override
	public String toString() {
		return "Room [name=" + name + ", images=" + images + ", rating=" + rating + ", reviews=" + reviews
				+ ", country=" + country + ", state=" + state + ", city=" + city + ", bedRoomCount=" + bedRoomCount
				+ ", bathRoomCount=" + bathRoomCount + ", accomodatesCount=" + accomodatesCount + ", bedCount="
				+ bedCount + ", category=" + category + ", description=" + description + ", amentities=" + amentities
				+ ", latitude=" + latitude + ", longtitude=" + longtitude + ", price=" + price + ", priceType="
				+ priceType + ", mininumStay=" + mininumStay + ", stayType=" + stayType + ", host=" + host + ", rules="
				+ rules + "]";
	}

}
