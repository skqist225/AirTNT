package com.airtnt.frontend.review;

import java.util.List;

import com.airtnt.common.entity.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getReviewsByRoom(Integer[] roomIds, int numberOfStars) {
        return reviewRepository.getReviewsByRoom(roomIds, numberOfStars);
    }
}
