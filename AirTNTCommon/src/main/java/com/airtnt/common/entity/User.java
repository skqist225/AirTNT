package com.airtnt.common.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity {

	private String avatar;

	@Column(nullable = false, length = 48)
	private String firstName;

	@Column(nullable = false, length = 48)
	private String lastName;

	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private Sex sex;

	private Date birthday;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false, length = 30)
	private String password;

	@ManyToOne
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "role_id")
	private Role role;

	@Column(length = 10, nullable = false)
	private String phoneNumber;

	@OneToOne(mappedBy = "host")
	private Room room;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

	@Builder.Default
	@Column(columnDefinition = "boolean default false")
	private boolean isSupremeHost = false;

	@Builder.Default
	@Column(columnDefinition = "boolean default false")
	private boolean isVerified = false;

	@Column(length = 1024)
	private String about;

	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<UserReview> userReviews = new ArrayList<>();

	@Builder.Default
	@ManyToMany
	@JoinTable(name = "users_favorite_rooms", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "room_id"))
	private Set<Room> rooms = new HashSet<>();

	public User(int id) {
		super(id);
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	@Transient
	public String getAvatarPath() {
		return "/room_images/" + this.avatar;
	}
}
