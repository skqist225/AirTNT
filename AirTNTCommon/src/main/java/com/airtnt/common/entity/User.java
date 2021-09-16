package com.airtnt.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

	@Builder
	public User(int id, boolean status, Date createdDate, Date updatedDate, String firstName, String lastName, Sex sex,
			Date birthday, String email, String password, Role role, String phoneNumber, Room room, Address address,
			boolean isSupremeHost, boolean isVerified, String about) {
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
	}

	
	
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
	
	@OneToOne
	@JoinColumn(name = "role_id",referencedColumnName = "id")
	private Role role;
	
	@Column(length = 10, nullable = false)
	private String phoneNumber;
	
	@OneToOne(mappedBy = "host")
	private Room room;
	
	@OneToOne
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	
	@Column(name = "is_supreme_host",columnDefinition = "BOOLEAN default 0")
	private boolean isSupremeHost;
	
	@Column(name = "is_verified",columnDefinition = "BOOLEAN default 0")
	private boolean isVerified;
	
	@Column(length = 1024)
	private String about;

}
