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
@Table(name = "states")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column(columnDefinition="VARCHAR(10)")
    private String code;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "country_id")
    private Country country;

    public State(int id) {
        this.id = id;
    }
}
