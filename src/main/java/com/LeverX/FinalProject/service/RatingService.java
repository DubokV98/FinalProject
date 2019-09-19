package com.LeverX.FinalProject.service;

import com.LeverX.FinalProject.entity.Rating;
import com.LeverX.FinalProject.entity.User;

import java.util.List;

public interface RatingService {
    Rating addRating();

    void updateRating(Rating rating, double ratingsum);

    List<User> sortByRating();
}
