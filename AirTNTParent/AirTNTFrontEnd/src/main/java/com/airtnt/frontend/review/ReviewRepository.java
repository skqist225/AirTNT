package com.airtnt.frontend.review;

import java.util.List;

import com.airtnt.common.entity.Review;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {

    @Query("SELECT r FROM Review r WHERE r.room.id IN (:roomIds) AND r.finalRating >= :numberOfStars")
    public List<Review> getReviewsByRoom(Integer[] roomIds, int numberOfStars);
}