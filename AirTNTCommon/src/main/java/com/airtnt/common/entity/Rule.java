package com.airtnt.common.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rules")
public class Rule extends BaseEntity {

    @Builder
    public Rule(String title, String icon) {
        super();
        this.title = title;
        this.icon =icon;
    }

    @Column(nullable = false, unique = true)
    private String title;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String icon;

}
