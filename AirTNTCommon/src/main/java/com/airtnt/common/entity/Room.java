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
@Builder
@Entity
@Table(name = "rooms")
public class Room extends BaseEntity {

	@Column(nullable = false, length = 512, unique = true)
	private String name;

	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "room_id")
	private Set<Image> images = new HashSet<>();

	@Column(columnDefinition = "smallint")
	private byte rating;

	@Builder.Default
	@OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
	private List<Review> reviews = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;

	@ManyToOne
	@JoinColumn(name = "state_id")
	private State state;

	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;

	@Column(columnDefinition = "VARCHAR(10) NOT NULL", name = "bedroom_count")
	private String bedRoomCount;

	@Column(length = 10, nullable = false)
	private String bathroomCount;

	@Column(length = 10, nullable = false)
	private String accomodatesCount;

	@Column(length = 10, nullable = false)
	private String bedCount;

	@OneToOne
	@JoinColumn(name = "currency_id")
	private Currency currency;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@Column(columnDefinition = "TEXT NOT NULL")
	private String description;

	@Builder.Default
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
	@JoinColumn(name = "host_id")
	private User host;

	@Builder.Default
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "rooms_rules", joinColumns = @JoinColumn(name = "room_id"), inverseJoinColumns = @JoinColumn(name = "rule_id"))
	private Set<Rule> rules = new HashSet<>();

	@Override
	public String toString() {
		return "Room [name=" + name + ", images=" + images + ", rating=" + rating + ", reviews=" + reviews
				+ ", country=" + country + ", state=" + state + ", city=" + city + ", bedRoomCount=" + bedRoomCount
				+ ", bathRoomCount=" + bathroomCount + ", accomodatesCount=" + accomodatesCount + ", bedCount="
				+ bedCount + ", category=" + category + ", description=" + description + ", amentities=" + amentities
				+ ", latitude=" + latitude + ", longtitude=" + longtitude + ", price=" + price + ", priceType="
				+ priceType + ", mininumStay=" + mininumStay + ", stayType=" + stayType + ", host=" + host + ", rules="
				+ rules + "]";
	}

	public Room(int id) {
		super(id);
	}
}
