package com.airtnt.common.entity;

import javax.persistence.*;

@Entity
@Table(name = "states")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column(columnDefinition="VARCHAR(10)")
    private String code;

    @OneToOne
    @JoinColumn(referencedColumnName = "id", name = "country_id")
    private Country country;
}
