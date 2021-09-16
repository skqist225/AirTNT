package com.airtnt.common.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

	@Builder
	public User(int id, boolean status, Date createdDate, Date updatedDate, String firstName, String lastName, Sex sex,
			Date birthday, String email, String password, Role role, String phoneNumber, Room room, Address address,
			boolean isSupremeHost, boolean isVerified, String about, String avatar, List<UserReview> userReviews) {
		super(id, status, createdDate, updatedDate);
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.birthday = birthday;
		this.email = email;
		this.password = password;
		this.role = role;
		this.phoneNumber = phoneNumber;
		this.room = room;
		this.address = address;
		this.isSupremeHost = isSupremeHost;
		this.isVerified = isVerified;
		this.about = about;
		this.avatar = avatar;
		this.userReviews =userReviews;
	}

	public User(int id) {
		super(id);
	}

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
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "role_id",referencedColumnName = "id")
	private Role role;
	
	@Column(length = 10, nullable = false)
	private String phoneNumber;
	
	@OneToOne(mappedBy = "host")
	private Room room;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	
	@Column(name = "is_supreme_host",columnDefinition = "BOOLEAN default 0")
	private boolean isSupremeHost;
	
	@Column(name = "is_verified",columnDefinition = "BOOLEAN default 0")
	private boolean isVerified;
	
	@Column(length = 1024)
	private String about;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private List<UserReview> userReviews = new ArrayList<>();

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
	
}
