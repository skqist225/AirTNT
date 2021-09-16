package com.airtnt.common.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rules")
public class Rule extends BaseEntity {

    @Builder
    public Rule(int id, boolean status, Date createdDate, Date updatedDate, String title, String icon) {
        super(id, status, createdDate, updatedDate);
        this.title = title;
        this.icon =icon;
    }

    @Column(nullable = false, unique = true)
    private String title;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String icon;

}
