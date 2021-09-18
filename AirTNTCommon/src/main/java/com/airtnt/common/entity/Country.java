package com.airtnt.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"states", "cities"})
@Builder
@Entity
@Table(name = "countries")
public class Country {

    public Country(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition="VARCHAR(10) NOT NULL", unique = true)
    private String code;

    @OneToMany(mappedBy = "country")
    private Set<State> states = new HashSet<>();

    @OneToMany(mappedBy = "country")
    private Set<City> cities = new HashSet<>();
      
}
