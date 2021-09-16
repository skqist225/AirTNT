package com.airtnt.common.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_reviews")
public class UserReview extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @OneToOne
    @JoinColumn(name = "review_by", referencedColumnName = "id")
    private User reviewBy;

    @Column(columnDefinition = "VARCHAR(1024)")
    private String reviewContent;
}
