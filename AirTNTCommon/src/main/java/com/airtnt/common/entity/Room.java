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

	@Column(nullable = false, length = 512)
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

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "state_id")
	private State state;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "city_id")
	private City city;

	@Column(columnDefinition = "VARCHAR(10) NOT NULL", name = "bedroom_count")
	private String bedroomCount;

	@Column(length = 10, nullable = false)
	private String bathroomCount;

	@Column(length = 10, nullable = false)
	private String accomodatesCount;

	@Column(length = 10, nullable = false)
	private String bedCount;

	@ManyToOne
	@JoinColumn(name = "room_group_id")
	private RoomGroup roomGroup;

	@ManyToOne
	@JoinColumn(name = "room_type_id")
	private RoomType roomType;

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

	@Column(columnDefinition = "DEFAULT 0")
	private float latitude;

	@Column(columnDefinition = "DEFAULT 0")
	private float longitude;

	@Column(nullable = false)
	private float price;

	private String privacyType;

	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private PriceType priceType;

	@Column(length = 5, nullable = false, columnDefinition = "INT DEFAULT 1")
	private int minimumStay;

	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private StayType stayType;

	@ManyToOne
	@JoinColumn(name = "host_id")
	private User host;

	@Builder.Default
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "rooms_rules", joinColumns = @JoinColumn(name = "room_id"), inverseJoinColumns = @JoinColumn(name = "rule_id"))
	private Set<Rule> rules = new HashSet<>();

	@Override
	public String toString() {
		return "Room [name=" + name + ", images=" + images + ", rating=" + rating + ", reviews=" + reviews
				+ ", country=" + country + ", state=" + state + ", city=" + city + ", bedRoomCount=" + bedroomCount
				+ ", bathRoomCount=" + bathroomCount + ", accomodatesCount=" + accomodatesCount + ", bedCount="
				+ bedCount + ", category=" + category + ", description=" + description + ", amentities=" + amentities
				+ ", latitude=" + latitude + ", longtitude=" + longitude + ", price=" + price + ", priceType="
				+ priceType + ", mininumStay=" + minimumStay + ", stayType=" + stayType + ", host=" + host + ", rules="
				+ rules + "]";
	}

	public Room(int id) {
		super(id);
	}

	@Transient
	public String getImageFirst() {
		if (!this.images.isEmpty())
			return "room_images/" + this.host.getEmail() + "/" + this.images.toArray()[0].toString();
		else
			return "images/airtntlogo.png";
	}
}
