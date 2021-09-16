package com.airtnt.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;
    
    @OneToOne
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    private State state;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
