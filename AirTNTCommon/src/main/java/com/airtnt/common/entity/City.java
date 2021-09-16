package com.airtnt.common.entity;

import javax.persistence.*;

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
}
