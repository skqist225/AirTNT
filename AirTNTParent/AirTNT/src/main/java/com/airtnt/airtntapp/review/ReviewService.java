package com.airtnt.airtntapp.review;

import java.util.List;

import com.airtnt.common.entity.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getReviewsByBookings(Integer[] bookingIds) {
        return reviewRepository.getReviewsByBookings(bookingIds);
    }

    public List<Review> getReviewsByBookings(Integer[] bookingIds, Integer numberOfStars) {
        return reviewRepository.getReviewsByBookings(bookingIds, numberOfStars);
    }
}
